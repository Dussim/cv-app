package xyz.dussim.backend

import org.intellij.lang.annotations.Language
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("ee")
class EasterEggsController {
    @Language("JSON")
    @GetMapping("/gym-stats", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun gymStats(): String = """
        [
          {
            "name": "Bench Press",
            "reps": 2,
            "weight": 100
          },
          {
            "name": "Squat",
            "reps": 4,
            "weight": 135
          },
          {
            "name": "Pull up",
            "reps": 18
          },
          {
            "name": "Pull up with weight",
            "reps": 2,
            "weight": 25
          }
        ]
    """.trimIndent()
}
