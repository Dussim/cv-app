package xyz.dussim.feature.cvcontent.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.dussim.designsystem.LocalTextStyleProvider
import xyz.dussim.resources.R
import xyz.dussim.ui.language.DateChip
import java.time.Month.APRIL
import java.time.Month.MAY
import java.time.YearMonth

@Immutable
data class Certificate(
    val name: String,
    val date: YearMonth,
    val link: Uri? = null,
)

@Composable
fun CertificatesVertical(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    certificates: List<Certificate> = listOf(
        Certificate(
            name = "Oracle Certified Professional: Java SE 11 Developer",
            date = YearMonth.of(2021, APRIL)
        ),
        Certificate(
            name = "Samsung Software Test Level Professional (Internal)",
            date = YearMonth.of(2019, MAY)
        )
    ),
) {
    CertificatesBase(
        modifier,
        verticalArrangement,
        horizontalAlignment
    ) {
        certificates.forEach {
            CertificateColumn(certificate = it)
        }
    }
}

@Composable
fun CertificatesHorizontal(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    certificates: List<Certificate> = listOf(
        Certificate(
            name = "Oracle Certified Professional: Java SE 11 Developer",
            date = YearMonth.of(2021, APRIL)
        ),
        Certificate(
            name = "Samsung Software Test Level Professional (Internal)",
            date = YearMonth.of(2019, MAY)
        )
    ),
) {
    CertificatesBase(
        modifier,
        verticalArrangement,
        horizontalAlignment
    ) {
        certificates.forEach {
            CertificateRow(certificate = it)
        }
    }
}

@Composable
fun CertificatesBase(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        val style = LocalTextStyleProvider.current.forSectionTitle()
        BasicText(text = stringResource(R.string.section_name_certificates), style = style)
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            content = content
        )
    }
}

@Composable
private fun CertificateColumn(certificate: Certificate) {
    val style = LocalTextStyleProvider.current.forCertificate()
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        DateChip(date = certificate.date)
        BasicText(text = certificate.name, style = style)
    }
}

@Composable
private fun CertificateRow(certificate: Certificate) {
    val style = LocalTextStyleProvider.current.forCertificate()
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        BasicText(text = certificate.name, style = style)
        DateChip(date = certificate.date)
    }
}