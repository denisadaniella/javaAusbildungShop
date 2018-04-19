package ro.msg.learning.shop.odata.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfiguration {

    @Bean
    public ServletRegistrationBean jpaODataServlet(JpaServlet servlet) {
        return new ServletRegistrationBean(servlet, "/odata/*");
    }

    @Bean
    public ServletRegistrationBean odataServlet(ODServlet servlet) {
        return new ServletRegistrationBean(servlet, "/odata/create/*");
    }
}
