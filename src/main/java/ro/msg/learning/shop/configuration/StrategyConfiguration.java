package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@Configuration
public class StrategyConfiguration {

    @Value("${ro.msg.learning.shop.strategy.strategytype}")
    protected StrategyType strategyType;

    @Bean
    public LocationStrategy getLocationStrategy() {
        switch (strategyType) {
            case SINGLE:
                return new SingleLocationStrategy();
        }

        return null;
    }

    public enum StrategyType {
        SINGLE
    }

}
