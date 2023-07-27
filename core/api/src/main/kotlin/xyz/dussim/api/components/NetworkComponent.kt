package xyz.dussim.api.components

import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.data.skills.Skill

public interface NetworkComponent {
    public companion object;

    public val skillsDataSource: DataSource<State<List<Skill>>>
}