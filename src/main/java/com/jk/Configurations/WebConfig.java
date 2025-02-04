package com.jk.Configurations;

import javax.sql.DataSource;

import com.jk.repository.UserRepository;
import com.jk.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebConfig {
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource){
        return new JdbcTemplate(datasource);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserService();
    }
}
