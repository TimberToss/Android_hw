package matrix.words

import kotlinx.coroutines.*
import java.io.File
import java.io.IOException
import kotlin.random.Random

object WordGame {

    private const val path = "src/main/resources/words.txt"
    private const val acceptableWordLength = 8

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    suspend fun play() {

        val word = findWord()

        println("Origin word for build new ones: $word\n")

        val words = readLine()?.ifBlank { throw IllegalArgumentException("No words were entered") }
            ?.split(" ")
            ?.distinct()
            ?.also {
                withContext(scope.coroutineContext) {
                    File("src/main/resources/result.txt").writeText(it.joinToString(separator = "\n"))
                }
            }
            ?: throw IOException("End of file")

        val originWordMap = word.toMap()

        withContext(Dispatchers.Default) {
            words
                .map { originWordMap.estimate(it.toMap()) }
                .reduce(Int::plus)
        }.also { println("Your score: $it") }
    }


    private suspend fun findWord() = withContext(scope.coroutineContext) {
        val file = File(path)

        var size = 0
        file.forEachLine { size++ }

        var word = ""

        while (word.length < acceptableWordLength) {
            word = file.useLines {
                it.elementAt(Random.nextInt(0, size - 1))
            }.toLowerCase()
        }

        word.ifBlank { throw IllegalStateException("No words") }
    }

    private fun String.toMap() = this.toLowerCase().groupingBy { it }.eachCount()

    private fun Map<Char, Int>.estimate(other: Map<Char, Int>) = other.entries
        .all { it.value <= this.getOrDefault(it.key, 0) }
        .let { if (it) other.size else 0 }

}



