package com.itranswarp.learnjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class AppService {

	@Value("1")
	private int version;

	@Value("classpath:/logo.txt")
	private Resource resource;
	@Value("classpath:/app.properties")
	private Resource resourcePropResource;

	private String logo;
	private String prop;

	@PostConstruct
	public void init() throws IOException {
		try (var reader = new BufferedReader(
				new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
			this.logo = reader.lines().collect(Collectors.joining("\n"));
		}
		try (var reader = new BufferedReader(
				new InputStreamReader(resourcePropResource.getInputStream(), StandardCharsets.UTF_8))) {
			this.prop = reader.lines().collect(Collectors.joining("\n"));
		}

	}

	public void printLogo() {
		System.out.println(logo);
		System.out.println(prop);
		System.out.println("app.version: " + version);
	}
}
