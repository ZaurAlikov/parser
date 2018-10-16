import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:settings.properties")
public class AppConfig {

    @Bean
    BuildMapCategory mapCategory(){
        return new BuildMapCategory();
    }
}
