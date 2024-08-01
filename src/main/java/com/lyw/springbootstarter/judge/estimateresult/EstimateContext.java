package com.lyw.springbootstarter.judge.estimateresult;

import com.lyw.springbootstarter.constant.QuestionSubmitConstant;
import com.lyw.springbootstarter.judge.dto.CodeExecuteReponse;
import com.lyw.springbootstarter.model.dto.question.JudgeConfig;
import com.lyw.springbootstarter.model.dto.questionsubmit.JudgeInfo;
import com.lyw.springbootstarter.model.entity.Question;
import com.lyw.springbootstarter.model.entity.QuestionSubmit;
import com.lyw.springbootstarter.openfeign.dto.CodeRunResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-24 20:52
 * @Description:
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstimateContext {

    private String language;

    private IEstimate estimate;

    private CodeRunResult<List<String>> codeRunResult;

    private JudgeConfig judgeConfig;

    private List<String> outputList;

    public JudgeInfo estimate() {
        if (Objects.equals(language, QuestionSubmitConstant.Language.JAVA.getValue())) {
            estimate = new JavaEstimate();
        }
        return estimate.estimate(codeRunResult, judgeConfig, outputList);
    }
}

