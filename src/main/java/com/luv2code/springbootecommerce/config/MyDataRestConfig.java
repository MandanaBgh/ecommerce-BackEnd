package com.luv2code.springbootecommerce.config;

import com.luv2code.springbootecommerce.entity.Product;
import com.luv2code.springbootecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnSuppHttpMethods = {HttpMethod.POST,HttpMethod.DELETE,HttpMethod.PUT};
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSuppHttpMethods)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSuppHttpMethods)));

        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSuppHttpMethods)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSuppHttpMethods)));
    }
}
