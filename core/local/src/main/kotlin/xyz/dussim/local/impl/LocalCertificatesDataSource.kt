package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.local.R
import java.time.YearMonth
import java.util.Calendar.APRIL
import java.util.Calendar.MAY

internal class LocalCertificatesDataSource : DataSource<List<Certificate>> by LocalDataSource(STATIC_DATA) {
    companion object {
        private val STATIC_DATA = listOf(
            Certificate(
                name = R.string.certificate_java,
                date = YearMonth.of(2021, APRIL)
            ),
            Certificate(
                name = R.string.certificate_samsung,
                date = YearMonth.of(2019, MAY)
            )
        )
    }
}