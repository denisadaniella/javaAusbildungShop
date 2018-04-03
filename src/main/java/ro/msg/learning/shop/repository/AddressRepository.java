package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.model.Address;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    List<Address> findByCountryAndCityAndCountyAndStreet(String country, String city, String county, String street);

}
