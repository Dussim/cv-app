package xyz.dussim.backend

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("cv")
class CvDataController(
    @Value("classpath:internal/skills.json") resource: Resource
) {
    private val skills = resource.readAsString()

    @GetMapping("/skills", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun skills(): String = skills
}
