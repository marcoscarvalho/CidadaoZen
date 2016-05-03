package br.com.cidadaozen.cloud;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.cidadaozen.cloud.repositorio.Constantes;

@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableCaching
//@EnableJpaRepositories(entityManagerFactoryRef = "myEntityManager")
public class CidadaoZenCore {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CidadaoZenCore.class);
		app.setShowBanner(false);
		app.run(args);
	}

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager(Constantes.CACHEABLE_PARAMETRO,
				Constantes.CACHEABLE_PARAMETRO_NOME,
				Constantes.CACHEABLE_SATISFACAO,
				Constantes.CACHEABLE_SATISFACAO_NOME);
	}

	@Value("${URL}")
	public String url;

	@Value("${USERNAME}")
	public String username;

	@Value("${PASSWORD}")
	public String password;

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = DataSourceBuilder.create()
				.driverClassName("org.postgresql.Driver").url(url)
				.username(username).password(password).build();
		return dataSource;
	}

	// @Bean(name = "myEntityManager")
	// public LocalContainerEntityManagerFactoryBean order(
	// EntityManagerFactoryBuilder builder) {
	// return builder.dataSource(dataSource())
	// .packages("br.com.cidadaozen.cloud").build();
	// }
}
