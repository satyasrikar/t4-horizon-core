package com.horizon.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@SpringBootApplication
@OpenAPIDefinition(
	info = Info(
		title = "Horizon Core API",
		version = "1.1.6",
		description = "Information regarding Horizon Core Authentication APIs"
	)
)
class HorizonCoreApplication
var log: Logger = LoggerFactory.getLogger(HorizonCoreApplication::class.java)

fun main(args: Array<String>) {
	runApplication<HorizonCoreApplication>(*args)
	log.info("HorizonCore Application Started")
}
