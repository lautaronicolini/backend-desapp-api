package ar.edu.unq.desapp.grupoK.backenddesappapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class BackendDesappApiApplication

fun main(args: Array<String>) {
	runApplication<BackendDesappApiApplication>(*args)
}
