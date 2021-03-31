package matrix

interface MyNumber<T : MyNumber<T>> {
    operator fun plus(other: T): T
    operator fun minus(other: T): T
    operator fun times(other: T): T
}