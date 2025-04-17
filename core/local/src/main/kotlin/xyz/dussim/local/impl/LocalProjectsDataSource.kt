package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.projects.Project
import xyz.dussim.local.R

internal class LocalProjectsDataSource : DataSource<List<Project>> by LocalDataSource(staticData()) {
    companion object {
        private fun staticData() =
            listOf(
                Project(
                    name = R.string.project_gradle_ssh_name,
                    description = R.string.project_gradle_ssh_description,
                    githubUrl = R.string.project_gradle_ssh_github_url,
                    contentDescription = R.string.project_gradle_ssh_content_description,
                ),
                Project(
                    name = R.string.project_cv_app_name,
                    description = R.string.project_cv_app_description,
                    githubUrl = R.string.project_cv_app_github_url,
                    contentDescription = R.string.project_cv_app_content_description,
                ),
            )
    }
}
