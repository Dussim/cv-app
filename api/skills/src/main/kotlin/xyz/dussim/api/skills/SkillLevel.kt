package xyz.dussim.api.skills


public enum class SkillLevel(public val fraction: Float) {
    Beginner(0.2f),
    Competent(0.4f),
    Advanced(0.6f),
    Proficient(0.8f),
    Expert(1.0f);

    init {
        require(fraction in (0f..1f)) {
            "Fraction must be in range of 0f..1f"
        }
    }
}