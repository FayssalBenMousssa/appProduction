package dev.fenix.application.configuration;

import dev.fenix.application.configuration.filters.DatabaseFilter;
import dev.fenix.application.security.repository.UserRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    UserRepository userRepository;

    public FilterConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public FilterRegistrationBean<DatabaseFilter> filterRegistrationBean() {
        FilterRegistrationBean<DatabaseFilter> registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new DatabaseFilter(userRepository));
        registrationBean.addUrlPatterns("/api/customer/*");
        registrationBean.addUrlPatterns("/api/application/*");
        registrationBean.addUrlPatterns("/api/enterprise/*");
        registrationBean.addUrlPatterns("/api/staff/*");
        registrationBean.addUrlPatterns("/api/customer/*");
        registrationBean.addUrlPatterns("/api/logistic/*");
        registrationBean.addUrlPatterns("/api/objective/*");
        registrationBean.addUrlPatterns("/api/product/*");
        registrationBean.addUrlPatterns("/api/stock/*");
        registrationBean.addUrlPatterns("/api/supplier/*");
        registrationBean.addUrlPatterns("/api/vendor/*");
        registrationBean.addUrlPatterns("/api/payment/*");
        registrationBean.addUrlPatterns("/api/document/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
