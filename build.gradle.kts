plugins {
    id("xyz.dussim.module-utilities")
}

val printProjects by tasks.registering {

    val dependencies = hashMapOf<String, List<Pair<String, String>>>()
    project.allprojects.forEach { sourceProject ->
        sourceProject.configurations.forEach { config ->
            config.dependencies.withType(ProjectDependency::class.java)
                .map { it.dependencyProject }
                .forEach { targetProject ->
                    dependencies[sourceProject.path] =
                        dependencies.getOrDefault(sourceProject.path, emptyList())
                            .plus(targetProject.path to config.name)
                }
        }
    }

    dependencies.forEach {
        println(it)
    }
}
