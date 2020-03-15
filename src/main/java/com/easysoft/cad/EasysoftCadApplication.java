package com.easysoft.cad;

import javax.validation.Validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.easysoft.core.data.jpa.repository.impl.BaseRepositoryImpl;

@ComponentScan("com.easysoft")
@EnableJpaAuditing
@EnableAsync
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
public class EasysoftCadApplication {

	private ResourceBundleMessageSource getMessageSource() throws Exception {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setBasenames("message/messages");
		return messageSource;
	}

	@Bean
	public Validator getValidator() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(getMessageSource());
		return validator;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EasysoftCadApplication.class, args);
	}

}
