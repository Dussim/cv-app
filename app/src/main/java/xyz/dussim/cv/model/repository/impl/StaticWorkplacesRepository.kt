package xyz.dussim.cv.model.repository.impl

import xyz.dussim.cv.R
import xyz.dussim.cv.model.external.workplace.WorkplaceData
import xyz.dussim.cv.model.repository.WorkplacesRepository
import java.time.Month
import java.time.YearMonth

internal object StaticWorkplacesRepository : WorkplacesRepository {
    private val ANDROID_DEVELOPER = R.string.workplace_title_android_developer

    private val STATIC_DATA = listOf(
        WorkplaceData(
            startDate = YearMonth.of(2022, Month.APRIL),
            endDate = null,
            workTitle = ANDROID_DEVELOPER,
            location = R.string.workplace_location_viessmann,
            description = R.string.workplace_description_viessmann
        ),
        WorkplaceData(
            startDate = YearMonth.of(2020, Month.SEPTEMBER),
            endDate = YearMonth.of(2022, Month.APRIL),
            workTitle = ANDROID_DEVELOPER,
            location = R.string.workplace_location_paramount,
            description = R.string.workplace_description_paramount
        ),
        WorkplaceData(
            startDate = YearMonth.of(2019, Month.AUGUST),
            endDate = YearMonth.of(2022, Month.AUGUST),
            workTitle = ANDROID_DEVELOPER,
            location = R.string.workplace_location_spyrosoft,
            description = R.string.workplace_description_spyrosoft
        ),
        WorkplaceData(
            startDate = YearMonth.of(2018, Month.JANUARY),
            endDate = YearMonth.of(2019, Month.JULY),
            workTitle = R.string.workplace_title_junior_software_developer,
            location = R.string.workplace_location_samsung,
            description = R.string.workplace_description_samsung_junior
        ),
        WorkplaceData(
            startDate = YearMonth.of(2017, Month.APRIL),
            endDate = YearMonth.of(2017, Month.DECEMBER),
            workTitle = R.string.workplace_title_intern,
            location = R.string.workplace_location_samsung,
            description = R.string.workplace_description_samsung_intern
        )
    )

    override suspend fun fetchWorkplaces() = STATIC_DATA
}