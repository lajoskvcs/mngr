package app.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * This class initializes Spring Framework.
 */
public class WebInitalizer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Filter[] getServletFilters() {
        Filter[] filters = {
                new OptionsFilter()
        };
        return filters;
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            MNGRConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
