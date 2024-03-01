package xyz.dussim.model.impl

import xyz.dussim.api.components.LocalComponent
import xyz.dussim.api.components.ModelComponent
import xyz.dussim.api.components.NetworkComponent
import xyz.dussim.api.coroutines.DispatchersComponent
import xyz.dussim.api.data.DataSource
import xyz.dussim.data.CvData

internal class ModelComponentImpl(
    private val dispatchersComponent: DispatchersComponent,
    private val localComponent: LocalComponent,
    private val networkComponent: NetworkComponent
) : ModelComponent {

    override val cvDataSource: DataSource<CvData> by lazy {
        CvDataSource(
            skillsDataSource = NetworkFirstSkillsDataSource(
                network = networkComponent.skillsDataSource,
                local = localComponent.skillsDataSource
            ),
            languagesDataSource = NetworkFirstLanguagesDataSource(
                network = networkComponent.leanguagesDataSource,
                local = localComponent.languagesDataSource
            ),
            workplacesDataSource = localComponent.workplacesDataSource,
            socialsDataSource = localComponent.socialMediaDataSource,
            aboutMeDataSource = localComponent.aboutMeDataSource,
            certificatesDataSource = localComponent.certificatesDataSource,
            dispatcher = dispatchersComponent.io
        )
    }
}

fun ModelComponent.Companion.create(
    dispatchersComponent: DispatchersComponent,
    localComponent: LocalComponent,
    networkComponent: NetworkComponent
): ModelComponent = ModelComponentImpl(
    dispatchersComponent = dispatchersComponent,
    localComponent = localComponent,
    networkComponent = networkComponent
)
