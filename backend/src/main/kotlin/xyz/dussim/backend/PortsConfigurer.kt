package xyz.dussim.backend

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PortsConfigurer : WebMvcConfigurer {
    internal inner class PortsInterceptor : HandlerInterceptor {
        override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
            val isWellKnown = request.requestURI.contains(".well-known")
            val isCvApi = request.requestURI.contains("cv")
            return if (request.localPort == 80 && !isCvApi) {
                true
            } else if (request.localPort == 90 && !isWellKnown) {
                true
            } else {
                response.status = 404
                false
            }
        }
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(PortsInterceptor())
    }
}
