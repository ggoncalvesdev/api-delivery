package com.api.delivery.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.delivery.domain.service.EnvioEmailService;
import com.api.delivery.infrastructure.service.email.FakeEnvioEmailService;
import com.api.delivery.infrastructure.service.email.SandboxEnvioEmailService;
import com.api.delivery.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService envioEmailService() {
		switch (emailProperties.getImpl()) {
			case FAKE:
				return new FakeEnvioEmailService();
			case SMTP:
				return new SmtpEnvioEmailService();
			case SANDBOX:
				return new SandboxEnvioEmailService();
			default:
				return null;
		}
	}

}
