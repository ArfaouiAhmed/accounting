package com.accounting.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@SpringBootApplication
public class AccountingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountingBackendApplication.class, args);
	}

	/**
	 * Keeps the session open until the end of a request. Allows us to use
	 * lazy-loading with Hibernate.
	 */
	@Bean
	@ConditionalOnProperty(value="petclinic.open-session-in-view", havingValue="true", matchIfMissing = true)
	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
		return new OpenEntityManagerInViewFilter();
	}

}
