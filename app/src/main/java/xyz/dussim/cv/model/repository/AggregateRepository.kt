package xyz.dussim.cv.model.repository

import xyz.dussim.cv.model.external.CvData

fun interface AggregateRepository {
    suspend fun fetchAggregatedData(): CvData
}