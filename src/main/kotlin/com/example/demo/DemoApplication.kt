package com.example.demo

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@SpringBootApplication
class DemoApplication {
	@Bean
	fun runner(service: DemoService) = ApplicationRunner {
		try {
			println(service.results())
		} catch (e: DemoException) {
			println("exception: ${e.message}")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

class DemoException(message: String?) : Exception(message)

interface DemoService {
	fun results(): Map<String, Any>
}

@Service
class DemoServiceImpl : DemoService {
	@Transactional(readOnly = true)
	override fun results(): Map<String, Any> {
		throw DemoException("sww")
	}
}