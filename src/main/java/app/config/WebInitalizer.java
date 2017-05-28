package app.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * This class initializes Spring Framework
 */
public class WebInitalizer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * This method returns the custom filters for Spring
     * @return The custom filters
     */
    @Override
    protected Filter[] getServletFilters() {
        Filter[] filters = {
                new OptionsFilter()
        };
        return filters;
    }

    /**
     * This method returns the root configuration classes for Spring
     * @return The configuration classes
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            MNGRConfiguration.class
        };
    }

    /**
     * This method returns the servlet configuration classes for Spring
     * @return the servlet configuration classes
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    /**
     * This method returns the application's route mappings for Spring
     * @return The route mappings
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
