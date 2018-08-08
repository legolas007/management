package com.primeton.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**负载均衡策略
 * @Author: Usher
 * @Description:
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule() {
        //轮询
        return new RoundRobinRule();
    }
}
