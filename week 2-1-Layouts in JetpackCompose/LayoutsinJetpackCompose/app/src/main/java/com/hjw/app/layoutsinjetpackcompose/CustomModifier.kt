package com.hjw.app.layoutsinjetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hjw.app.layoutsinjetpackcompose.ui.theme.LayoutsInJetpackComposeTheme

fun Modifier.firstBaselineToTop(
    firstBaseLineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseLine = placeable[FirstBaseline]

        val placeableY = firstBaseLineToTop.roundToPx() - firstBaseLine
        val height = placeable.height + placeableY

        layout(width = placeable.width, height = height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    LayoutsInJetpackComposeTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    LayoutsInJetpackComposeTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}