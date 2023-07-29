package xyz.dussim.designsystem.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.dussim.designsystem.*

@Preview(name = "App typography preview")
@Composable
private fun PreviewTextStyles() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicText(text = "H1", style = H1)
        BasicText(text = "H2", style = H2)
        BasicText(text = "H3", style = H3)
        BasicText(text = "Body 1", style = Body1)
        BasicText(text = "Body 2", style = Body2)
        BasicText(text = "Caption", style = Caption)
        BasicText(text = "Label", style = Label)
    }
}