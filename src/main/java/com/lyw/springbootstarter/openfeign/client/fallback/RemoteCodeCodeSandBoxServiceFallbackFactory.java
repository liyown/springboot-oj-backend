package com.lyw.springbootstarter.openfeign.client.fallback;
import com.lyw.springbootstarter.judge.dto.CodeExecuteRequest;
import com.lyw.springbootstarter.openfeign.client.RemoteCodeCodeSandBoxService;
import com.lyw.springbootstarter.openfeign.dto.CodeRunResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import java.util.List;


/**
 * @author: liuyaowen
 * @poject: hmall
 * @create: 2024-07-11 13:39
 * @Description: fallback factory example
 */
@Slf4j
public class RemoteCodeCodeSandBoxServiceFallbackFactory implements FallbackFactory<RemoteCodeCodeSandBoxService> {

    @Override
    public RemoteCodeCodeSandBoxService create(Throwable throwable) {
        System.out.println("fallbackFactory");
        return new RemoteCodeCodeSandBoxService() {
            @Override
            public CodeRunResult<List<String>> execute(CodeExecuteRequest request) {
                log.error("远程调用失败", throwable);
                throw new RuntimeException("openfeign调用失败");
            }
        };
    }
}
