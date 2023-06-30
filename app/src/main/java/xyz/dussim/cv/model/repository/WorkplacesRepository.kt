package xyz.dussim.cv.model.repository

import xyz.dussim.cv.model.external.workplace.WorkplaceData

fun interface WorkplacesRepository {
    suspend fun fetchWorkplaces(): List<WorkplaceData>
}