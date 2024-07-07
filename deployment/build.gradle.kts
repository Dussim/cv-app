import xyz.dussim.gradlessh.tasks.exec.SshRemoteExecutionTask

plugins {
    id("xyz.dussim.build-parameters")
    id("xyz.dussim.gradle-ssh").version("0.0.2")
}

val backend by remotes.publicKeyAuthenticated {
    host = buildParameters.deployment.ssh.host
    user = buildParameters.deployment.ssh.user
}

val launchNewerVersionOfBackendDockerImage by remoteExecCommands.command {
    commands = listOf(
        "docker container stop backend",
        "docker container rm backend",
        "docker image rm dussim/cv-api",
        "docker run -d --restart always -p 9090:80 --name backend dussim/cv-api:latest"
    )
}

val pushDockerImage by tasks.registering {
    group = "docker"
    dependsOn(gradle.includedBuild("backend").task(":dockerPushImage"))
}

val redeployBackend by tasks.registering(SshRemoteExecutionTask::class) {
    mustRunAfter(pushDockerImage)

    command = launchNewerVersionOfBackendDockerImage
    remote = backend
    appendRemoteNameToLines = true
}

val pushDockerImageAndRedeployBackend by tasks.registering {
    group = "ssh"
    dependsOn(pushDockerImage)
    dependsOn(redeployBackend)
}
