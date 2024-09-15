package xyz.dussim.backend.plugins

import io.ktor.http.ContentType
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.intellij.lang.annotations.Language

@Language("JSON")
private val assetLinks =
    """
        [
          {
            "relation": [
              "delegate_permission/common.handle_all_urls"
            ],
            "target": {
              "namespace": "android_app",
              "package_name": "xyz.dussim.cv",
              "sha256_cert_fingerprints": [
                "AC:C7:FD:20:8F:DD:BC:40:57:5E:28:23:6C:2D:A0:B3:0D:66:27:0A:66:DA:F5:EB:A3:45:56:F5:BA:11:F7:E3",
                "7A:C9:F8:47:22:EB:12:2F:34:6B:6F:8D:F9:FC:F5:C0:C8:5F:7E:3B:5D:52:B7:AB:B0:84:E4:29:0D:70:3F:53"
              ]
            }
          }
        ]
    """.trimIndent()

fun Application.configureAssetLinks() {
    routing {
        get("/.well-known/assetlinks.json") {
            call.respondText(ContentType.Application.Json) { assetLinks }
        }
    }
}