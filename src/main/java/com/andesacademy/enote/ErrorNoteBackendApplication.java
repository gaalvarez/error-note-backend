package com.andesacademy.enote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.andesacademy.enote.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class ErrorNoteBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorNoteBackendApplication.class, args);
	}
}
