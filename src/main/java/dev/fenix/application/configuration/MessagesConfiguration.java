package dev.fenix.application.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessagesConfiguration {

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasenames(
        "classpath:/messages/application",
        "classpath:/messages/buttons",
        "classpath:/messages/info",
        "classpath:/messages/placeholder",
        "classpath:/messages/api_error_messages",
        "classpath:/messages/api_response_messages",
        "classpath:/messages/validationMessages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
}
