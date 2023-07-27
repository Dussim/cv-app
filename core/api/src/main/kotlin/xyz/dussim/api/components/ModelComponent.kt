package xyz.dussim.api.components

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.CvData

public interface ModelComponent {
    public companion object;

    public val cvDataSource: DataSource<CvData>
}

