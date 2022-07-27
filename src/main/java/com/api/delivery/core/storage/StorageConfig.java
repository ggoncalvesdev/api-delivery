package com.api.delivery.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.delivery.core.storage.StorageProperties.TipoStorage;
import com.api.delivery.domain.service.FotoStorageService;
import com.api.delivery.infrastructure.service.storage.LocalFotoStorageService;

@Configuration
public class StorageConfig {

	@Autowired
	private StorageProperties storageProperties;
	
//	@Bean
//	@ConditionalOnProperty(name = "apidelivery.storage.tipo", havingValue = "s3")
//	public AmazonS3 amazonS3() {
//		var credentials = new BasicAWSCredentials(
//				storageProperties.getS3().getIdChaveAcesso(), 
//				storageProperties.getS3().getChaveAcessoSecreta());
//		
//		return AmazonS3ClientBuilder.standard()
//				.withCredentials(new AWSStaticCredentialsProvider(credentials))
//				.withRegion(storageProperties.getS3().getRegiao())
//				.build();
//	}
	
	@Bean
	public FotoStorageService fotoStorageService() {
		if (TipoStorage.S3.equals(storageProperties.getTipo())) {
//			return new S3FotoStorageService();
		} else {
			return new LocalFotoStorageService();
		}
		return null;
	}
	
}