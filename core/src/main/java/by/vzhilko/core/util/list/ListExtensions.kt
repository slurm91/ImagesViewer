package by.vzhilko.core.util.list

fun <T> List<T>.isEqual(
    list: List<T>,
    compareBlock: ((first: T, second: T) -> Int)? = null,
    equalBlock: ((first: T, second: T) -> Boolean)? = null
): Boolean {
    if (this.size != list.size) return false

    compareBlock?.let { list.sortedWith { first, second -> it(first, second) } }

    this.forEachIndexed { index, value ->
        if (equalBlock != null) {
            if (!equalBlock(value, list[index])) return false
        } else {
            if (value != list[index]) return false
        }
    }

    return true
}