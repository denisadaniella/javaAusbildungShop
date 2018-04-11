package ro.msg.learning.shop.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.exception.AddressNotFoundException;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.util.distancematrix.Distance;
import ro.msg.learning.shop.util.distancematrix.DistanceMatrix;
import ro.msg.learning.shop.util.distancematrix.DistanceMatrixElementStatus;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Component
public class DistanceMatrixUtil {

    @Value("${generic.proxyHost}")
    private String proxyHost;

    @Value("${generic.proxyPort}")
    private Integer proxyPort;

    @Value("${google.distance.matrix.url}")
    private String url;

    @Value("${google.distance.matrix.key}")
    private String key;


    public Distance getDistance(Address origin, Address destination) {

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)));
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        ResponseEntity<DistanceMatrix> responseEntity = restTemplate.getForEntity(url, DistanceMatrix.class, origin.toString(), destination.toString(), key);

        if (responseEntity.getBody().getRows().get(0).getElements().get(0).getStatus() == DistanceMatrixElementStatus.OK) {

            return responseEntity.getBody().getRows().get(0).getElements().get(0).getDistance();

        } else {
            if (responseEntity.getBody().getDestinationAddresses().get(0).isEmpty()) {
                throw new AddressNotFoundException(destination.toString());
            }
            throw new AddressNotFoundException();
        }
    }

}
