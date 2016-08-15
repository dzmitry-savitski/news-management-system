package by_.dsavitski.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Application context configuration.
 */
@Configuration
public class AppContext {
    /**
     * Configures message source.
     */
    @Bean(name = {"i18n", "messageSource"})
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    /**
     * Configures application properties bean.
     */
    @Bean(name = "appConfig")
    public Properties AppConfig() throws IOException {
        return PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
    }

    /**
     * Required for @Value annotations support
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
