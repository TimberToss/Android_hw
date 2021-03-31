package matrix

fun Int.toReal() = RealNumber(this.toDouble())
fun Long.toReal() = RealNumber(this.toDouble())
fun Float.toReal() = RealNumber(this.toDouble())
fun Double.toReal() = RealNumber(this)