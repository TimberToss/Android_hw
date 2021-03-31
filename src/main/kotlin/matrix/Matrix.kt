package matrix

class Matrix<T : MyNumber<T>>(
    private val values: List<List<T>>
) {
    private val height = values.size
    private val width = values[0].size

    operator fun plus(other: Matrix<T>) = if (isAppendableWith(other)) {
        values.mapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, value ->
                value + other.values[rowIndex][columnIndex]
            }
        }.let { Matrix(it) }
    } else throw IllegalArgumentException("These matrices are not appendable")

    operator fun times(other: Matrix<T>) = if (isMultipleWith(other)) {
        values.mapIndexed { _, row ->
            other.width.toExclusiveRange()
                .map { otherColumnIndex ->
                    row.mapIndexed { otherRowIndex, value ->
                        value * other.values[otherRowIndex][otherColumnIndex]
                    }.reduce(MyNumber<T>::plus)
                }
        }.let { Matrix(it) }
    } else throw IllegalArgumentException("These matrices are not multiple")

    fun transpose() =
        width.toExclusiveRange().map { columnIndex ->
            height.toExclusiveRange().map { rowIndex ->
                values[rowIndex][columnIndex]
            }
        }.let { Matrix(it) }

    private fun isAppendableWith(other: Matrix<T>) =
        height == other.height && width == other.width

    private fun isMultipleWith(other: Matrix<T>) =
        width == other.height

    override fun toString() =
        values.joinToString(separator = "\n", postfix = "\n") {
            it.joinToString(separator = " ")
        }

    private fun Int.toExclusiveRange() = 0 until this
    private fun Int.toInclusiveRange() = 0..this

}