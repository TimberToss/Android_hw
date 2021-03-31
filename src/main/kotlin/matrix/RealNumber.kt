package matrix

class RealNumber(
    val value: Double
) : MyNumber<RealNumber> {

    override fun plus(other: RealNumber) = RealNumber(value + other.value)

    override fun minus(other: RealNumber) = RealNumber(value - other.value)

    override fun times(other: RealNumber) = RealNumber(value * other.value)

    override fun toString() = value.toString()

}