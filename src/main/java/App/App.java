package App;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class App {



    public static void main(String[] args) {
        SpringApplication.run(App.class);


    }
    @Configuration
    public class DatasourceConfig {
        @Bean
        public DataSource datasource() {
            return DataSourceBuilder.create()
                    .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                    .url("jdbc:sqlserver://projecttwoserver.database.windows.net:1433;database=p2db;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30")
                    .username("jacob367")
                    .password("Plut0nium")
                    .build();

        }
    }





}
