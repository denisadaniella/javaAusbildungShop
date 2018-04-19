package ro.msg.learning.shop.odata.service;

import lombok.AllArgsConstructor;
import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.service.OrderCreationService;

@Component
@AllArgsConstructor
public class ODServiceFactory extends ODataServiceFactory {

    private final EdmProvider edmProvider;
    private final OrderCreationService service;

    @Override
    public ODataService createService(ODataContext oDataContext) {
        return createODataSingleProcessorService(edmProvider, new ODProcessor(service));
    }

}
