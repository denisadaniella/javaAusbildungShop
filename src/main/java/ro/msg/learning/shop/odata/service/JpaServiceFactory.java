package ro.msg.learning.shop.odata.service;

import lombok.AllArgsConstructor;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST)
@AllArgsConstructor
public class JpaServiceFactory extends ODataJPAServiceFactory {

    private final LocalContainerEntityManagerFactoryBean factory;
    private final JpaSchemaExtension extension;

    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        ODataJPAContext context = this.getODataJPAContext();
        context.setEntityManagerFactory(factory.getObject());
        context.setPersistenceUnitName("shop");
        context.setJPAEdmExtension(extension);
        context.setDefaultNaming(false);
        return context;
    }

}
