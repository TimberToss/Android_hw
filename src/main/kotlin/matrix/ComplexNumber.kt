package matrix

class ComplexNumber(
    val realPart: Double,
    val virtualPart: Double
) : MyNumber<ComplexNumber> {

    override fun plus(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(
            realPart = realPart + other.realPart,
            virtualPart = virtualPart + other.virtualPart
        )
    }

    override fun minus(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(
            realPart = realPart - other.realPart,
            virtualPart = virtualPart - other.virtualPart
        )
    }

    override fun times(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(
            realPart = realPart * other.realPart - virtualPart * other.virtualPart,
            virtualPart = other.realPart * virtualPart + realPart * other.virtualPart
        )
    }

    override fun toString(): String {
        return "$realPart + ${virtualPart}i"
    }

}