package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;

@Configuration
public class StrategyConfiguration {

    @Bean
    public LocationStrategy getLocationStrategy(@Value("${ro.msg.learning.shop.strategy.strategytype}") StrategyType strategyType, StockRepository stockRepository, ProductRepository productRepository) {
        switch (strategyType) {
            case SINGLE:
                return new SingleLocationStrategy(stockRepository, productRepository);
        }

        return null;
    }

    public enum StrategyType {
        SINGLE
    }

}
