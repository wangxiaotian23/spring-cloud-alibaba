package ribbonconfig;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该文件需要放在springboot包外层
 * 否则会出现父子上下文扫描类重叠
 * 随机负载均衡
 * @Author lawhen
 * @Date 2020/12/29
 */
@Configuration
public class RibbonConfig {

    //随机配置
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }

//    //配置ping的策略
//    @Bean
//    public IPing ping(){
//        return new PingUrl();
//    }
    @Bean
    public IPing ping(){
        return new PingUrl();
    }

}
