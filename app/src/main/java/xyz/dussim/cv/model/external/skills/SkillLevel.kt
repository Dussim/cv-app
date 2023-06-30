package xyz.dussim.cv.model.external.skills

import androidx.annotation.FloatRange

enum class SkillLevel(
    @FloatRange(from = 0.0, to = 1.0)
    val fraction: Float
) {
    Beginner(0.2f),
    Competent(0.4f),
    Advanced(0.6f),
    Proficient(0.8f),
    Expert(1.0f)
}