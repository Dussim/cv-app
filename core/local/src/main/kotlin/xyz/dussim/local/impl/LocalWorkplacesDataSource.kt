package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.local.R
import java.time.Month
import java.time.YearMonth

internal class LocalWorkplacesDataSource : DataSource<List<Workplace>> by LocalDataSource(STATIC_DATA) {
    companion object {
        private val STATIC_DATA =
            listOf(
                Workplace(
                    startDate = YearMonth.of(2022, Month.APRIL),
                    endDate = null,
                    workTitle = R.string.workplace_title_android_developer,
                    location = R.string.workplace_location_viessmann,
                    description = R.string.workplace_description_viessmann,
                ),
                Workplace(
                    startDate = YearMonth.of(2020, Month.SEPTEMBER),
                    endDate = YearMonth.of(2022, Month.APRIL),
                    workTitle = R.string.workplace_title_android_developer,
                    location = R.string.workplace_location_paramount,
                    description = R.string.workplace_description_paramount,
                ),
                Workplace(
                    startDate = YearMonth.of(2019, Month.AUGUST),
                    endDate = YearMonth.of(2022, Month.AUGUST),
                    workTitle = R.string.workplace_title_android_developer,
                    location = R.string.workplace_location_spyrosoft,
                    description = R.string.workplace_description_spyrosoft,
                ),
                Workplace(
                    startDate = YearMonth.of(2018, Month.JANUARY),
                    endDate = YearMonth.of(2019, Month.JULY),
                    workTitle = R.string.workplace_title_junior_software_developer,
                    location = R.string.workplace_location_samsung,
                    description = R.string.workplace_description_samsung_junior,
                ),
                Workplace(
                    startDate = YearMonth.of(2017, Month.APRIL),
                    endDate = YearMonth.of(2017, Month.DECEMBER),
                    workTitle = R.string.workplace_title_intern,
                    location = R.string.workplace_location_samsung,
                    description = R.string.workplace_description_samsung_intern,
                ),
            )
    }
}
