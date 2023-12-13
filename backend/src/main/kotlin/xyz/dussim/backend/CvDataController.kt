package xyz.dussim.backend

import org.intellij.lang.annotations.Language
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("cv")
class CvDataController {
    @Language("JSON")
    @GetMapping("/skills", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun skills(): String = """
        [
          {
            "name": "Kotlin",
            "level": "Proficient"
          },
          {
            "name": "Java",
            "level": "Proficient"
          },
          {
            "name": "Android",
            "level": "Proficient"
          },
          {
            "name": "Dagger2",
            "level": "Advanced"
          },
          {
            "name": "Git",
            "level": "Advanced"
          },
          {
            "name": "Spring",
            "level": "Beginner"
          }
        ]
    """.trimIndent()
}
