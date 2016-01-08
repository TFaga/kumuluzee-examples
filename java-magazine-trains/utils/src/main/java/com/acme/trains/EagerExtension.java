package com.acme.trains;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessBean;

/**
 * <p>Extention for CDI that implements and provides support for the @Eager annotation, which eager loads application scoped beans
 * when CDI initializes.</p>
 *
 * @author Tilen Faganel
 * @since 2.0.0
 *
 * @see com.acme.trains.Eager
 */
public class EagerExtension implements Extension {

    private List<Bean<?>> eagerBeansList = new ArrayList<>();

    /**
     * <p>Collect all beans that have the {@link com.acme.trains.Eager @Eager} and {@link javax.enterprise.context.ApplicationScoped @ApplicationScoped}
     * annotation and save them for startup.</p>
     *
     * @param event Event represents and contains the found CDI bean during initial scanning.
     * @param <T> Class of the found bean.
     *
     * @see com.acme.trains.Eager
     * @see javax.enterprise.context.ApplicationScoped
     */
    public <T> void collect(@Observes ProcessBean<T> event) {

        if (event.getAnnotated().isAnnotationPresent(Eager.class)
                && event.getAnnotated().isAnnotationPresent(ApplicationScoped.class)) {
            eagerBeansList.add(event.getBean());
        }
    }

    /**
     * <p>Initializes all beans found and saved in the {@link #collect(ProcessBean) collect} method. This enables eager loading of annotated beans on application (CDI) startup.</p>
     *
     * @param event CDI event that is triggered after CDI deployment. When this is triggered, the eager beans are instantiate.
     * @param beanManager The CDI bean manager that contains and manages all CDI beans.
     */
    public void load(@Observes AfterDeploymentValidation event, BeanManager beanManager) {

        for (Bean<?> bean : eagerBeansList) {
            // note: toString() is important to instantiate the bean
            beanManager.getReference(bean, bean.getBeanClass(), beanManager.createCreationalContext(bean)).toString();
        }
    }
}
