package xyz.dussim.backend.plugins

import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.resources.Resources
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.intellij.lang.annotations.Language
import xyz.dussim.datamodel.language.dto.LanguageDto
import xyz.dussim.datamodel.language.dto.LanguageLevel.C1
import xyz.dussim.datamodel.language.dto.LanguageLevel.Native
import xyz.dussim.datamodel.language.dto.LanguageName.Predefined.English
import xyz.dussim.datamodel.language.dto.LanguageName.Predefined.Polish
import xyz.dussim.datamodel.skill.dto.SkillDto.Companion.advanced
import xyz.dussim.datamodel.skill.dto.SkillDto.Companion.competent
import xyz.dussim.datamodel.skill.dto.SkillDto.Companion.proficient
import xyz.dussim.datamodel.skill.dto.SkillName.Android
import xyz.dussim.datamodel.skill.dto.SkillName.Dagger2
import xyz.dussim.datamodel.skill.dto.SkillName.Git
import xyz.dussim.datamodel.skill.dto.SkillName.Java
import xyz.dussim.datamodel.skill.dto.SkillName.Kotlin
import xyz.dussim.datamodel.skill.dto.SkillName.Ktor

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

private val skills = listOf(
    proficient(Kotlin),
    proficient(Java),
    proficient(Android),
    advanced(Dagger2),
    advanced(Git),
    advanced("Gradle"),
    competent(Ktor),
    competent("Docker")
)

private val languages = listOf(
    LanguageDto(Polish, Native),
    LanguageDto(English, C1)
)

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
        route("cv") {
            get("skills") { call.respond(skills) }
            get("languages") { call.respond(languages) }
        }
        route("ee") {
            get("gym-stats") { call.respondText(Json) { gymStats } }
        }
    }
}
