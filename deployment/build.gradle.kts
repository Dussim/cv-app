import org.hidetake.groovy.ssh.core.Remote
import org.hidetake.groovy.ssh.core.RunHandler
import org.hidetake.groovy.ssh.core.Service
import org.hidetake.groovy.ssh.session.SessionHandler

plugins {
    id("xyz.dussim.build-parameters")
    id("org.hidetake.ssh") version "2.11.2"
}

val remote: Remote? by lazy {
    val ssh = buildParameters.deployment.ssh

    ssh.idRsaPath.orNull?.let { idRsaPath ->
        Remote(
            mapOf(
                "name" to "${ssh.user}@${ssh.host}",
                "host" to ssh.host,
                "user" to ssh.user,
                "identity" to file(idRsaPath)
            )
        )
    } ?: run {
        logger.warn("No idRsaPath found, skipping remote registration")
        null
    }
}

val checkDocker by tasks.registering {
    val remote = remote ?: run {
        logger.warn("No remote found, skipping task")
        return@registering
    }
    notCompatibleWithConfigurationCache("This task is not compatible with the configuration cache.")
    doNotTrackState("This is script that deploys to a remote server. It is a side effect by design.")
    group = "utils"

    doLast {
        ssh.withSession(remote) {
            execute("docker ps")
        }
    }
}

val pushDockerFile by tasks.registering {
    val remote = remote ?: run {
        logger.warn("No remote found, skipping task")
        return@registering
    }
    notCompatibleWithConfigurationCache("This task is not compatible with the configuration cache.")
    doNotTrackState("This is script that deploys to a remote server. It is a side effect by design.")
    group = "utils"

    doLast {
        ssh.withSession(remote) {
            execute("mkdir -p ~/backend_docker")
            put(
                from = layout.projectDirectory.file("templates/backend-docker-compose.yml").asFile,
                into = "backend_docker/backend-docker-compose.yml"
            )
            execute("cat ./backend_docker/backend-docker-compose.yml")
        }
    }
}

val pushEnvFile by tasks.registering {
    val remote = remote ?: run {
        logger.warn("No remote found, skipping task")
        return@registering
    }
    notCompatibleWithConfigurationCache("This task is not compatible with the configuration cache.")
    doNotTrackState("This is script that deploys to a remote server. It is a side effect by design.")
    group = "utils"

    doLast {
        ssh.withSession(remote) {
            execute("mkdir -p ~/backend_docker")
            put(
                text = """
                    EMAIL=artur@tuzim.xyz
                    DOMAIN=tuzim.xyz
                    LIVE_VERSION=1.4.0
                    STAGING_VERSION=latest
                """.trimIndent(),
                into = "backend_docker/.env"
            )
            execute("cat ./backend_docker/.env")
        }
    }
}

val deploy by tasks.registering {
    val remote = remote ?: run {
        logger.warn("No remote found, skipping task")
        return@registering
    }
    notCompatibleWithConfigurationCache("This task is not compatible with the configuration cache.")
    doNotTrackState("This is script that deploys to a remote server. It is a side effect by design.")
    group = "deployment"

    dependsOn(pushDockerFile)
    dependsOn(pushEnvFile)
    dependsOn(gradle.includedBuild("backend").task(":dockerPushImage"))

    doLast {
        ssh.withSession(remote) {
            execute("cd ~/backend_docker && docker compose -f backend-docker-compose.yml down")
            execute("cd ~/backend_docker && docker compose -f backend-docker-compose.yml pull")
            execute("cd ~/backend_docker && docker compose -f backend-docker-compose.yml up -d")
        }
    }
}

fun Service.runSessions(action: RunHandler.() -> Unit) =
    run(delegateClosureOf(action))

fun Service.withSession(remote: Remote, action: SessionHandler.() -> Unit) =
    runSessions { session(remote) { action() } }

fun RunHandler.session(vararg remotes: Remote, action: SessionHandler.() -> Unit) =
    session(*remotes, delegateClosureOf(action))

fun SessionHandler.put(from: Any, into: Any) =
    put(hashMapOf("from" to from, "into" to into))

fun SessionHandler.put(text: String, into: Any) =
    put(hashMapOf("text" to text, "into" to into))

