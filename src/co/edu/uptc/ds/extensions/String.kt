package co.edu.uptc.ds.extensions

fun String.hasContent() = isNotBlank() && isNotEmpty()

fun CharSequence.hasContent() = isNotBlank() && isNotEmpty()
