package com.liangzc.mall.mallaggregate.health;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;

/**
 * 实现ribbon的心跳检查，不借助第三方，ribbon的ping接口实现了对服务的心跳检测
 */
@Slf4j
public class HealthCheck implements IPing {

    @Override
    public boolean isAlive(Server server) {
        boolean isResult;
        log.info("健康检查：{}",server);
        String url = "http://"+server.getId()+"/healthCheck";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpUriRequest httpUriRequest = new HttpGet(url);
        CloseableHttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httpUriRequest);
            isResult = httpResponse.getStatusLine().getStatusCode() == 200;
        } catch (IOException e) {
            isResult = false;
            e.printStackTrace();
        }finally {
            httpUriRequest.abort();
        }
        return isResult;
    }
}
