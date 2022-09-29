package App;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


import javax.sql.DataSource;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class App {



    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }



}
