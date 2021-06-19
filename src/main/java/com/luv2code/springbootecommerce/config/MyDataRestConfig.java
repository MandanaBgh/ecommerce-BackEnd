package com.luv2code.springbootecommerce.config;

import com.luv2code.springbootecommerce.entity.Product;
import com.luv2code.springbootecommerce.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager enityManager;

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
        exposeId(config);
    }

    private void exposeId(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = enityManager.getMetamodel().getEntities();

        List<Class> classEntities = new ArrayList<>();

        for(EntityType theEntityTypes:entities){
            classEntities.add(theEntityTypes.getJavaType());
        }
        Class[] domainTypes =classEntities.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }


}
