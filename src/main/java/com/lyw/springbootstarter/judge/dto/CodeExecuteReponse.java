package com.lyw.springbootstarter.judge.dto;

import com.lyw.springbootstarter.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: liuyaowen
 * @poject: springboot-starter
 * @create: 2024-07-24 19:31
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeExecuteReponse {

    private List<String> output;

    private String error;

    private JudgeInfo judgeInfo;
}
