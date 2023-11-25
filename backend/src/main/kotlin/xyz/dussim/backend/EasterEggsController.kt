package xyz.dussim.backend

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("ee")
class EasterEggsController(
    @Value("classpath:internal/gym-stats.json") gymStatsResource: Resource
) {
    private val gymStats = gymStatsResource.readAsString()

    @GetMapping("/gym-stats", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun gymStats(): String = gymStats
}