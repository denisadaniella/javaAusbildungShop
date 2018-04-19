package ro.msg.learning.shop.odata.servlet;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.odata.service.ODServiceFactory;

import javax.servlet.http.HttpServletRequest;

@Component
public class ODServlet extends ODataServlet {

    private final transient ODServiceFactory factory;

    @Autowired
    public ODServlet(ODServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    protected ODataServiceFactory getServiceFactory(HttpServletRequest request) {
        return factory;
    }

}
