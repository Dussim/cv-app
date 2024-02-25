package xyz.dussim.backend.plugins

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.resources.Resources
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.intellij.lang.annotations.Language

@Language("JSON")
private val gymStats =
    """
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

@Language("JSON")
private val skills =
    """
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
            "name": "Ktor",
            "level": "Beginner"
          }
        ]
    """.trimIndent()

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    install(Resources)
    routing {
        get("/") {
            call.respondText("Property of Artur Tuzim aka Dussim. All rights reserved. artur@tuzim.xyz")
        }
        get("cv/skills") { call.respondText(Json) { skills } }
        get("ee/gym-stats") { call.respondText(Json) { gymStats } }
    }
}
