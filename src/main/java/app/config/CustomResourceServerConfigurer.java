package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * This class configures the ResourceServer with the authentication rights.
 */
@Configuration
@EnableResourceServer
public class CustomResourceServerConfigurer extends ResourceServerConfigurerAdapter {
    /**
     * This method makes the resource id fro the application.
     * @param resources The r
     * @throws Exception when no {@code ResourceServerSecurityConfigurer} given
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("mngr_resource_id").stateless(true);
    }

    /**
     * This method configures the http routes with access rights.
     * @param http The {@code HttpSecurity} object
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().disable()
                .requestMatchers().antMatchers("/user/**","/users/current","/projects/**", "/tasks/**", "/dashboard/**")
                .and().authorizeRequests()
                .antMatchers("/user/**","/users/current","/projects/**", "/tasks/**", "/dashboard/**").access("hasRole('USER')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
