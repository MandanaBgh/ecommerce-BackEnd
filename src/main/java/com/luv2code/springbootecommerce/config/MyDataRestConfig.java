package com.luv2code.springbootecommerce.config;

import com.luv2code.springbootecommerce.entity.Country;
import com.luv2code.springbootecommerce.entity.Product;
import com.luv2code.springbootecommerce.entity.ProductCategory;
import com.luv2code.springbootecommerce.entity.State;
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

        disableHttpMethods(Product.class,config, theUnSuppHttpMethods);
        disableHttpMethods(ProductCategory.class,config, theUnSuppHttpMethods);
        disableHttpMethods(Country.class,config, theUnSuppHttpMethods);
        disableHttpMethods(State.class,config, theUnSuppHttpMethods);

        exposeId(config);
    }

    private void disableHttpMethods(Class theClass,RepositoryRestConfiguration config, HttpMethod[] theUnSuppHttpMethods) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSuppHttpMethods)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnSuppHttpMethods)));
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
