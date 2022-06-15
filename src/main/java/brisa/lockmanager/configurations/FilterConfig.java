package brisa.lockmanager.configurations;

import static brisa.lockmanager.Routes.ADMIN;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brisa.lockmanager.filters.SubscriptionNotifierFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SubscriptionNotifierFilter> loggingFilter() {

        final FilterRegistrationBean<SubscriptionNotifierFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SubscriptionNotifierFilter());
        registrationBean.addUrlPatterns(ADMIN + "/*");
        return registrationBean;
    }
}