package xyz.dussim.api.components

import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.data.skills.Skill

interface NetworkComponent {
    companion object;

    val skillsDataSource: DataSource<State<List<Skill>>>
}