package com.lyw.springbootstarter.judge.estimateresult;

import cn.hutool.json.JSONUtil;
import com.lyw.springbootstarter.constant.QuestionSubmitConstant;
import com.lyw.springbootstarter.judge.dto.CodeExecuteReponse;
import com.lyw.springbootstarter.model.dto.question.JudgeCase;
import com.lyw.springbootstarter.model.dto.question.JudgeConfig;
import com.lyw.springbootstarter.model.dto.questionsubmit.JudgeInfo;
import com.lyw.springbootstarter.model.entity.Question;
import com.lyw.springbootstarter.model.entity.QuestionSubmit;
import com.lyw.springbootstarter.openfeign.dto.CodeRunResult;

import java.util.List;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-24 20:46
 * @Description:
 */
public class JavaEstimate implements IEstimate {

    @Override
    public JudgeInfo estimate(CodeRunResult<List<String>> codeRunResult, JudgeConfig judgeConfig, List<String> outputList) {
        // 判断是否符合题目约束
        JudgeInfo judgeInfo = new JudgeInfo();
        if (codeRunResult.getTimeCost() > judgeConfig.getTimeLimit()) {
            judgeInfo.setMessage(QuestionSubmitConstant.JUDGE_RESULT_TIME_LIMIT_EXCEEDED);
        } else if (codeRunResult.getMemoryCost() > judgeConfig.getMemoryLimit()) {
            judgeInfo.setMessage(QuestionSubmitConstant.JUDGE_RESULT_MEMORY_LIMIT_EXCEEDED);
        } else {
            // 判断输出是否符合预期
            if (outputList.size() != codeRunResult.getData().size()) {
                judgeInfo.setMessage(QuestionSubmitConstant.JUDGE_RESULT_WRONG_ANSWER);
            } else {
                for (int i = 0; i < outputList.size(); i++) {
                    if (!outputList.get(i).equals(codeRunResult.getData().get(i))) {
                        judgeInfo.setMessage(QuestionSubmitConstant.JUDGE_RESULT_WRONG_ANSWER);
                        break;
                    }
                }
            }
            judgeInfo.setMessage(QuestionSubmitConstant.JUDGE_RESULT_ACCEPTED);
            judgeInfo.setMemory(codeRunResult.getMemoryCost());
            judgeInfo.setTime(codeRunResult.getTimeCost());
        }
        return judgeInfo;
    }
}
