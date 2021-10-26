package com.smile.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WSConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/soapws/*");
	}

	@Bean(name = "merchants")
	public DefaultWsdl11Definition merchantDefaultWsdl11Definition(XsdSchema merchantsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("MerchantsPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.smileshop.com/shop-ws");
		wsdl11Definition.setSchema(merchantsSchema);
		return wsdl11Definition;
	}

	@Bean(name = "products")
	public DefaultWsdl11Definition productDefaultWsdl11Definition(XsdSchema productsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ProductsPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.smileshop.com/shop-ws");
		wsdl11Definition.setSchema(productsSchema);
		return wsdl11Definition;
	}

	@Bean(name = "associations")
	public DefaultWsdl11Definition associationDefaultWsdl11Definition(XsdSchema associationSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AssociationsPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.smileshop.com/shop-ws");
		wsdl11Definition.setSchema(associationSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema merchantsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsds/merchants.xsd"));
	}

	@Bean
	public XsdSchema productsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsds/products.xsd"));
	}

	@Bean
	public XsdSchema associationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsds/associations.xsd"));
	}
}
