package ro.msg.learning.shop.odata.service;

import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmExtension;
import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmSchemaView;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class JpaSchemaExtension implements JPAEdmExtension {

    @Override
    public void extendWithOperation(JPAEdmSchemaView jpaEdmSchemaView) {
        //Do Nothing
    }

    @Override
    public void extendJPAEdmSchema(JPAEdmSchemaView jpaEdmSchemaView) {
        //Do Nothing
    }

    @Override
    public InputStream getJPAEdmMappingModelStream() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream("JpaEdmMappingModel.xml");
    }
}
