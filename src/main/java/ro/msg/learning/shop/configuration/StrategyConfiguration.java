package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.strategy.DistanceMatrixStrategy;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.util.DistanceMatrixUtil;

@Configuration
public class StrategyConfiguration {

    @Bean
    public LocationStrategy getLocationStrategy(@Value("${ro.msg.learning.shop.strategy.strategytype}") StrategyType strategyType, StockRepository stockRepository, ProductRepository productRepository, DistanceMatrixUtil distanceMatrixUtil) {
        switch (strategyType) {
            case SINGLE:
                return new SingleLocationStrategy(stockRepository, productRepository);
            case DISTANCE:
                return new DistanceMatrixStrategy(stockRepository, productRepository, distanceMatrixUtil);
        }

        return null;
    }

    public enum StrategyType {
        SINGLE,
        DISTANCE
    }

}
