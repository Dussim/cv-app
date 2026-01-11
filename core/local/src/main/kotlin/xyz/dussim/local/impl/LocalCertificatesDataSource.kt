package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.YearMonth
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.local.R
import java.time.Month.APRIL
import java.time.Month.MARCH
import java.time.Month.MAY

internal class LocalCertificatesDataSource : DataSource<List<Certificate>> by LocalDataSource(STATIC_DATA) {
    companion object {
        private val STATIC_DATA =
            listOf(
                Certificate(
                    name = R.string.certificate_pricefx,
                    date = YearMonth.of(2024, MARCH),
                ),
                Certificate(
                    name = R.string.certificate_samsung,
                    date = YearMonth.of(2021, APRIL),
                ),
                Certificate(
                    name = R.string.certificate_java,
                    date = YearMonth.of(2019, MAY),
                ),
            )
    }
}
