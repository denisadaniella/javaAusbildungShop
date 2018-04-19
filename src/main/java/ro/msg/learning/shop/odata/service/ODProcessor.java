package ro.msg.learning.shop.odata.service;

import lombok.AllArgsConstructor;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderReadProperties;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.info.PostUriInfo;
import ro.msg.learning.shop.dto.OrderCreationDto;
import ro.msg.learning.shop.mapper.OrderCreationMapper;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderCreationService;

import java.io.InputStream;
import java.util.Map;

@AllArgsConstructor
public class ODProcessor extends ODataSingleProcessor {

    private final OrderCreationService service;

    @Override
    public ODataResponse createEntity(PostUriInfo uriInfo, InputStream content, String requestContentType, String contentType) throws ODataException {
        EntityProviderReadProperties properties = EntityProviderReadProperties.init().mergeSemantic(false).build();
        ODataEntry entry = EntityProvider.readEntry(requestContentType, uriInfo.getStartEntitySet(), content, properties);
        Map<String, Object> data = entry.getProperties();

        OrderCreationDto orderCreationDto = new OrderCreationMapper().toOrderCreationDto(data);
        Order order = service.createOrder(orderCreationDto);

        data.put("orderId", order.getId());
        return EntityProvider.writeEntry("application/xml", uriInfo.getStartEntitySet(), entry.getProperties(), EntityProviderWriteProperties.serviceRoot(getContext().getPathInfo().getServiceRoot()).build());

    }
}
