package com.lyw.springbootstarter.judge.estimateresult;

import com.lyw.springbootstarter.judge.dto.CodeExecuteReponse;
import com.lyw.springbootstarter.model.dto.question.JudgeConfig;
import com.lyw.springbootstarter.model.dto.questionsubmit.JudgeInfo;
import com.lyw.springbootstarter.model.entity.Question;
import com.lyw.springbootstarter.model.entity.QuestionSubmit;
import com.lyw.springbootstarter.openfeign.dto.CodeRunResult;

import java.util.List;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-24 20:43
 * @Description:
 */
public interface IEstimate {

    JudgeInfo estimate(CodeRunResult<List<String>> codeRunResult, JudgeConfig judgeConfig, List<String> outputList);
}
