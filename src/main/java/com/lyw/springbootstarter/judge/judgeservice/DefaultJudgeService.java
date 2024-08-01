package com.lyw.springbootstarter.judge.judgeservice;

import cn.hutool.json.JSONUtil;
import com.lyw.springbootstarter.common.ErrorCode;
import com.lyw.springbootstarter.constant.QuestionSubmitConstant;
import com.lyw.springbootstarter.exception.BusinessException;
import com.lyw.springbootstarter.judge.dto.CodeExecuteRequest;
import com.lyw.springbootstarter.judge.estimateresult.EstimateContext;
import com.lyw.springbootstarter.model.dto.question.JudgeCase;
import com.lyw.springbootstarter.model.dto.question.JudgeConfig;
import com.lyw.springbootstarter.model.dto.questionsubmit.JudgeInfo;
import com.lyw.springbootstarter.model.entity.Question;
import com.lyw.springbootstarter.model.entity.QuestionSubmit;
import com.lyw.springbootstarter.openfeign.client.RemoteCodeCodeSandBoxService;
import com.lyw.springbootstarter.openfeign.dto.CodeRunResult;
import com.lyw.springbootstarter.service.QuestionService;
import com.lyw.springbootstarter.service.QuestionSubmitService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-24 19:39
 * @Description:
 */
@Service
public class DefaultJudgeService implements IJudgeService {

    private static final Logger log = LoggerFactory.getLogger(DefaultJudgeService.class);
    @Resource
    private RemoteCodeCodeSandBoxService remoteCodeCodeSandBoxService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private QuestionService questionService;


    @Override
    public void judge(Long submitId) {

        // 1. get submit
        QuestionSubmit questionSubmit = questionSubmitService.getById(submitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "submit not found");
        }

        if (!Objects.equals(questionSubmit.getStatus(), QuestionSubmitConstant.QUESTION_STATUS_SUBMIT)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "submit has been judged");
        }
        // 2. get question
        Question question = questionService.getById(questionSubmit.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "question not found");
        }


        List<String> judgeCasesInput = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class)
                                               .stream()
                                               .map(JudgeCase::getInput).toList();

        CodeExecuteRequest codeExecuteRequest = CodeExecuteRequest.builder()
                                                                  .input(judgeCasesInput)
                                                                  .code(questionSubmit.getCode())
                                                                  .build();

        // 4. execute code
        CodeRunResult<List<String>> codeRunResult = remoteCodeCodeSandBoxService.execute(codeExecuteRequest);
        // 5. estimate result
        List<String> judgeCasesOuput = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class)
                                               .stream()
                                               .map(JudgeCase::getOutput).toList();

        // 判断是否运行错误
        if (codeRunResult.getStatus() != 0) {
            log.error("code run error:{}", codeRunResult);
            JudgeInfo judgeInfo = new JudgeInfo();
            judgeInfo.setMessage(codeRunResult.getMessage());
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
            questionSubmit.setStatus(QuestionSubmitConstant.QUESTION_STATUS_SUCCESS);
        } else {
            log.info("code run success:{}", codeRunResult);
            EstimateContext estimateContext = EstimateContext.builder()
                                                             .language(questionSubmit.getLanguage())
                                                             .codeRunResult(codeRunResult)
                                                             .outputList(judgeCasesOuput)
                                                             .judgeConfig(JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class))
                                                             .build();
            JudgeInfo estimate = estimateContext.estimate();
            questionSubmit.setStatus(QuestionSubmitConstant.QUESTION_STATUS_SUCCESS);
            questionSubmit.setJudgeInfo(JSONUtil.toJsonStr(estimate));
        }
        // 6. save result
        questionSubmitService.updateById(questionSubmit);
    }
}
