package app.config;

import app.manager.CustomUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * This configuration class configures the authentication part of the application
 */
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientDetailsService clientDetailsService;

    /**
     * This method builds the authentication manager with the custom UserDetailsManager
     * @param authenticationManagerBuilder The builder class for authentication manager
     * @throws Exception when the builder cant build the manager
     */
    @Autowired
    private void globalUserDetails(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailsManager());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("oauth/token").permitAll();
    }

    /**
     * This class makes a Bean for the authentication manager
     * @return an authenticationManager
     * @throws Exception when no authenticationManager was built
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Returns a {@code TokenStore} for storing authorization tokens.
     * @return a {@code TokenStore}
     */
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    /**
     * Returns a configured {@code TokenStoreUserApprovalHandler} using the given {@code TokenStore}.
     * @param tokenStore the {@code TokenStore} for the {@code TokenStoreUserApprovalHandler}
     * @return the configured {@code TokenStoreUserApprovalHandler}
     */
    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    /**
     * Configures the <code>ApprovalStore</code> with the created <code>TokenStore</code>
     *
     * @param tokenStore the {@code TokenStore} for the {@code ApprovalStore}
     * @return the configured {@code ApprovalStore}
     */
    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    /**
     * Configures and returns a <code>customUserDetailsManager</code>
     *
     * @return the configured <code>customUserDetailsManager</code>
     */
    @Bean
    @Qualifier("userDetailsServiceBean")
    CustomUserDetailsManager customUserDetailsManager() {
        CustomUserDetailsManager customUserDetailsManager = new CustomUserDetailsManager();
        return customUserDetailsManager;
    }
}
