package com.hyun.learn_spring_security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import com.nimbusds.jose.jwk.RSAKey;

@Configuration
public class JwtSecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();
        });
        // 세션 사용 하지 않도록 설정
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.httpBasic();

        // csrf 설정 해제
        http.csrf().disable();

        // h2-console 사용때만 비활성화
        http.headers().frameOptions().disable();

        http.oauth2ResourceServer((resourceServer) ->
                resourceServer.jwt(Customizer.withDefaults()));

        return http.build();
    }

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

    /**
     * RSA 키쌍(공개키 + 개인키)을 생성하는 Bean.
     * 이 키를 사용하여 JWT의 서명을 생성하고 검증함.
     */
    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * RSAKey 객체를 생성하는 Bean.
     * Nimbus 라이브러리를 사용하여 JWT 서명을 위한 키로 변환.
     */
    @Bean
    public RSAKey rsaKey() throws NoSuchAlgorithmException {
        return new RSAKey.Builder((
                RSAPublicKey) keyPair().getPublic()) // 공개키 설정
                .privateKey(keyPair().getPrivate()) // 개인키 설정 (서명에 사용)
                .keyID(UUID.randomUUID().toString()) // 랜덤한 키 ID 생성 (JWT 헤더에 포함됨)
                .build();
    }

    /**
     * JWK(JSON Web Key) 소스를 설정하는 Bean.
     * JWT 발급 시 사용할 키 정보를 제공하는 역할을 함.
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        var jwkSet = new JWKSet(rsaKey);

        // JWKSelector를 사용해 JWKSet에서 키를 선택하도록 설정
        return ((jwkSelector, securityContext) -> jwkSelector.select(jwkSet));
    }

    /**
     * JWT 디코더를 설정하는 Bean.
     * 클라이언트가 보낸 JWT 토큰을 검증하고 해석할 때 사용됨.
     */
    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        // 공개키를 사용하여 디코더 생성
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }

    /**
     * JWT 인코더를 설정하는 Bean.
     * JWT를 생성하고 서명하는 역할을 함.
     */
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }
}
