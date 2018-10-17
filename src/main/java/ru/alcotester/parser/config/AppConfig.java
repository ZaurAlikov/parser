package ru.alcotester.parser.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.alcotester.parser.BuildMapCategory;

@Configuration
@ComponentScan("ru.alcotester.parser")
@PropertySource("classpath:settings.properties")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    BuildMapCategory mapCategory(){
        return new BuildMapCategory();
    }

    @Bean
    BasicDataSource dataSource(@Value("${driverClassName}") String drvClassName,
                               @Value("${url}") String url,
                               @Value("${username}") String username,
                               @Value("${password}") String password,
                               @Value("${initialSize}") int initialSize,
                               @Value("${minIdle}") int minIdle,
                               @Value("${poolPreparedStatements}") boolean poolPreparedStatements){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(drvClassName);
        dataSource.setUrl(url);
        dataSource.setUsername("sa");
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        return dataSource;
    }
}
