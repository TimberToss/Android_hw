package matrix

import kotlinx.coroutines.runBlocking
import matrix.words.WordGame


fun main() = runBlocking {

    calculateMatrices()

    WordGame.play()

}

private fun RealNumber.createComplex() = ComplexNumber(this.value, this.value + 1)

private fun calculateMatrices() {
    val firstData = listOf(
        listOf(1, 2),
        listOf(1, 4),
        listOf(1, 3)
    ).map { list ->
        list.map { it.toReal() }
    }

    val secondData = listOf(
        listOf(2, 3, 1),
        listOf(3, 1, 2)
    ).map { list ->
        list.map { it.toReal() }
    }

    val firstMatrix = Matrix(firstData).also { println(it) }
    val secondMatrix = Matrix(secondData).also { println(it) }

    firstMatrix.transpose().also { println(it) }
    (firstMatrix + firstMatrix).also { println(it) }
    (firstMatrix * secondMatrix).also { println(it) }

    val firstComplexData = firstData.map { list ->
        list.map { it.createComplex() }
    }

    val secondComplexData = secondData.map { list ->
        list.map { it.createComplex() }
    }

    val firstComplexMatrix = Matrix(firstComplexData).also { println(it) }
    val secondComplexMatrix = Matrix(secondComplexData).also { println(it) }

    firstComplexMatrix.transpose().also { println(it) }
    (firstComplexMatrix + firstComplexMatrix).also { println(it) }
    (firstComplexMatrix * secondComplexMatrix).also { println(it) }
}

