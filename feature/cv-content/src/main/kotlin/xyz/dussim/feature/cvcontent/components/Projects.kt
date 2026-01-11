package xyz.dussim.feature.cvcontent.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.dussim.data.ImmutableList
import xyz.dussim.data.projects.Project
import xyz.dussim.designsystem.LocalTextStyleProvider
import xyz.dussim.designsystem.core.ContentBox
import xyz.dussim.designsystem.core.CvChip
import xyz.dussim.designsystem.core.CvIcon
import xyz.dussim.feature.cvcontent.R

@Composable
internal fun ProjectsColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    projects: ImmutableList<Project>,
) {
    val style = LocalTextStyleProvider.current.forSectionTitle()
    val projectTitleStyle = LocalTextStyleProvider.current.forSkills()
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        BasicText(
            text = stringResource(R.string.section_name_projects),
            style = style,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            projects.forEach { project ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    CvChip(
                        modifier =
                            Modifier
                                .padding(vertical = 8.dp),
                    ) {
                        val githubUrl = stringResource(project.githubUrl)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            CvIcon(
                                vectorRes = R.drawable.github,
                                contentDescription = stringResource(R.string.button_view_on_github),
                            )

                            val annotatedString =
                                buildAnnotatedString {
                                    withStyle(
                                        style =
                                            SpanStyle(
                                                textDecoration = TextDecoration.Underline,
                                            ),
                                    ) {
                                        append(stringResource(project.name))
                                    }
                                }

                            ClickableText(
                                text = annotatedString,
                                style = projectTitleStyle,
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                                    context.startActivity(intent)
                                },
                            )
                        }
                    }

                    BasicText(
                        text = stringResource(project.description),
                        style = projectTitleStyle,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun PreviewProjectsContent(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    projects: ImmutableList<Project>,
) {
    val style = LocalTextStyleProvider.current.forSectionTitle()
    val projectTitleStyle = LocalTextStyleProvider.current.forSkills()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        BasicText(
            text = "Projects",
            style = style,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            projects.forEach { project ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    CvChip(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            CvIcon(
                                vectorRes = R.drawable.github,
                                contentDescription = stringResource(R.string.button_view_on_github),
                            )

                            val annotatedString =
                                buildAnnotatedString {
                                    withStyle(
                                        style =
                                            SpanStyle(
                                                textDecoration = TextDecoration.Underline,
                                            ),
                                    ) {
                                        append(stringResource(project.name))
                                    }
                                }

                            ClickableText(
                                text = annotatedString,
                                style = projectTitleStyle,
                                onClick = { },
                            )
                        }
                    }

                    BasicText(
                        text = stringResource(project.description),
                        style = projectTitleStyle,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }
        }
    }
}

@Preview(name = "Projects preview")
@Composable
private fun PreviewProjectsColumn() {
    val sampleProjects =
        listOf(
            Project(
                name = R.string.preview_project_cv_app_name,
                description = R.string.preview_project_cv_app_description,
                githubUrl = R.string.preview_project_cv_app_github_url,
                contentDescription = R.string.preview_project_cv_app_content_description,
            ),
            Project(
                name = R.string.preview_project_weather_app_name,
                description = R.string.preview_project_weather_app_description,
                githubUrl = R.string.preview_project_weather_app_github_url,
                contentDescription = R.string.preview_project_weather_app_content_description,
            ),
        )

    ContentBox {
        PreviewProjectsContent(
            projects = ImmutableList(sampleProjects),
        )
    }
}
