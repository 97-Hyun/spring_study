package com.hyun.learn_spring_security.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

//@Configuration
public class BasicAuthSecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();
        });
        // 세션 사용 하지 않도록 설정
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 주석 처리 시 템플릿 로그인/로그아웃 페이지가 아닌 팝업으로 나온다
//        http.formLogin();

        http.httpBasic();

        // csrf 설정 해제
        http.csrf().disable();

        // h2-console 사용때만 비활성화
        http.headers().frameOptions().disable();

        return http.build();
    }

    // security - inmemory 연결
//    @Bean
//    public UserDetailsService userDetailsService() {
//        var user = User.withUsername("hyun")
//                .password("{noop}1234")
//                .roles("USERS")
//                .build();
//
//        var admin = User.withUsername("admin")
//                .password("{noop}1234")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    // security - h2 연결
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        var user = User.withUsername("hyun")
//                .password("{noop}1234")
                .password("1234")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("USERS")
                .build();

        var admin = User.withUsername("admin")
//                .password("{noop}1234")
                .password("1234")
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles("ADMIN")
                .build();

        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.createUser(user);
        jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
