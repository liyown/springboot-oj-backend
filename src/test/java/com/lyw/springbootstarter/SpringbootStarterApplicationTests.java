package com.lyw.springbootstarter;

import com.lyw.springbootstarter.judge.dto.CodeExecuteRequest;
import com.lyw.springbootstarter.judge.judgeservice.IJudgeService;
import com.lyw.springbootstarter.openfeign.client.RemoteCodeCodeSandBoxService;
import com.lyw.springbootstarter.openfeign.dto.CodeRunResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Repeat;

import java.util.List;

@SpringBootTest
class SpringbootStarterApplicationTests {

    @Resource
    private RemoteCodeCodeSandBoxService remoteCodeCodeSandBoxService;

    @Test
    void contextLoads() {
    }

    @Resource
    private IJudgeService judgeService;

    @Test
    void testCodeSandBox() {
//        CodeExecuteRequest codeExecuteRequest = new CodeExecuteRequest();
//        codeExecuteRequest.setCode("public class Main { public static void main(String[] args) throws Exception { System.out.println(args[0] + args[1]); }}");
//        codeExecuteRequest.setInput(List.of("1 2","2 3"));
//
//
//        CodeRunResult<List<String>> execute = remoteCodeCodeSandBoxService.execute(codeExecuteRequest);
//        System.out.println(execute);
    }

}
