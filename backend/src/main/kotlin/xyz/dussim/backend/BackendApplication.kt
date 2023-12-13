package xyz.dussim.backend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
    SpringApplication(BackendApplication::class.java).apply {
        setDefaultProperties(mapOf("server.port" to "80"))
        run(*args)
    }
}
