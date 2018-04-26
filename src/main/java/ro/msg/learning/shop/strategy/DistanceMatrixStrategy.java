package ro.msg.learning.shop.strategy;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.dto.ProductOrderDto;
import ro.msg.learning.shop.dto.RequiredProductDto;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.NegativeQuantityException;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.util.DistanceMatrixUtil;
import ro.msg.learning.shop.util.distancematrix.Distance;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DistanceMatrixStrategy implements LocationStrategy {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final DistanceMatrixUtil distanceMatrixUtil;

    @Override
    public List<RequiredProductDto> findRequiredProductsByLocation(OrderCreationDto orderCreationDto) {

        List<Location> commonLocations = findCommonLocations(orderCreationDto);

        if (commonLocations == null) {
            throw new LocationNotFoundException();
        }

        Location closestLocation = getClosestLocation(orderCreationDto, commonLocations);

        return orderCreationDto.getProducts().stream().map(p -> new RequiredProductDto(closestLocation, productRepository.findOne(p.getProductId()), p.getQuantity())).collect(Collectors.toList());
    }


    private Location getClosestLocation(OrderCreationDto orderCreationDto, List<Location> locations) {

        Comparator<Location> comparator = (Location location1, Location location2) -> {
            Distance distance1 = distanceMatrixUtil.getDistance(location1.getAddress(), orderCreationDto.getDeliveryAddress());
            Distance distance2 = distanceMatrixUtil.getDistance(location2.getAddress(), orderCreationDto.getDeliveryAddress());
            return Long.compare(distance1.getValue(), distance2.getValue());
        };

        return locations.stream().min(comparator)
                .orElseThrow(NoSuchElementException::new);

    }


    private List<Location> findCommonLocations(OrderCreationDto orderCreationDto) {

        List<Location> commonLocations = null;

        //For each product in the orderCreationDto, get the stocks and the corresponding locations
        for (ProductOrderDto po : orderCreationDto.getProducts()) {
            if (po.getQuantity() < 0) {
                throw new NegativeQuantityException(po.getQuantity(), po.getProductId());
            }

            List<Location> locations = stockRepository.findLocationByProductIdAndQuantityGreaterThanEqual(po.getProductId(), po.getQuantity());

            //Find the common locations for all the products
            if (commonLocations == null) commonLocations = locations;
            commonLocations.retainAll(locations);

            if (commonLocations.isEmpty()) {
                //No specific location for the products, so far => exception
                throw new LocationNotFoundException();
            }
        }

        return commonLocations;
    }
}
