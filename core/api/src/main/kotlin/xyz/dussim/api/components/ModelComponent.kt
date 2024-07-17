package xyz.dussim.api.components

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.CvData

interface ModelComponent {
    companion object;

    val cvDataSource: DataSource<CvData>
}
