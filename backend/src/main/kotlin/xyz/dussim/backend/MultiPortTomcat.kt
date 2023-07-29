package xyz.dussim.backend

import org.apache.catalina.connector.Connector
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class MultiPortTomcat {

    @Bean
    fun servletContainer(): ServletWebServerFactory {
        return TomcatServletWebServerFactory().apply {
            listOf(80, 90).forEach { port ->
                val connector = Connector("org.apache.coyote.http11.Http11NioProtocol")
                connector.port = port
                addAdditionalTomcatConnectors(connector)
            }
        }
    }
}
