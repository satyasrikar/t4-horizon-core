package com.horizon.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@SpringBootApplication
@OpenAPIDefinition(
	info = Info(
		title = "Horizon Core API",
		version = "1.1.6",
		description = "Information regarding Horizon Core Authentication APIs"
	)
)
class HorizonCoreApplication

fun main(args: Array<String>) {
	runApplication<HorizonCoreApplication>(*args)
}
