package com.lyw.springbootstarter.openfeign.client;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lyw.springbootstarter.judge.dto.CodeExecuteRequest;
import com.lyw.springbootstarter.openfeign.client.fallback.RemoteCodeCodeSandBoxServiceFallbackFactory;
import com.lyw.springbootstarter.openfeign.dto.CodeRunResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: liuyaowen
 * @poject: springboot-OJ-backend
 * @create: 2024-07-31 19:15
 * @Description:
 */
@FeignClient(name = "code-sandbox", url = "http://106.53.217.225:8088", fallbackFactory = RemoteCodeCodeSandBoxServiceFallbackFactory.class, path = "codesandbox")
public interface RemoteCodeCodeSandBoxService {

    @PostMapping("/execute")
    CodeRunResult<List<String>> execute(@RequestBody CodeExecuteRequest request);

}
