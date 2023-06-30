package xyz.dussim.cv.ui.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

private const val GOLDEN_RATION = 0.618033988749895
private val random = Random(1337)

fun randomColorSequence(
    s: Float = 0.5f,
    v: Float = 0.95f
): Sequence<Color> {
    var hue = random.nextDouble(360.0)
    return generateSequence {
        hue += GOLDEN_RATION * 360f
        hue %= 360f
        Color.hsv(hue.toFloat(), s, v)
    }
}

val RandomColorIterator = randomColorSequence().iterator()

val randomColor: Color get() = RandomColorIterator.next()