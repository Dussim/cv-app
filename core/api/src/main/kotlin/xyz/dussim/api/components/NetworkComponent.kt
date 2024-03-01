package xyz.dussim.api.components

import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.data.gym.GymStats
import xyz.dussim.datamodel.language.Language
import xyz.dussim.datamodel.skill.Skill

interface NetworkComponent {
    companion object;

    val skillsDataSource: DataSource<State<List<Skill>>>

    val leanguagesDataSource: DataSource<State<List<Language>>>

    val gymStatsDataSource: DataSource<State<List<GymStats>>>
}
