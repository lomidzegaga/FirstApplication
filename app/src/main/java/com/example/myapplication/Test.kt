package com.example.myapplication

fun main() {
    val (name, age) = User()

    println("name: $name, age: $age")
}

data class User(
    val name: String = "gaga",
    val age: Int = 22
)

sealed class NamesSealed() {
    object Gaga : NamesSealed()
    object Soso : NamesSealed()
    object Sandro : NamesSealed()
    object Beka : NamesSealed()
}

sealed interface NameInterface {
    object Gaga : NameInterface
    object Soso : NameInterface
    object Sandro : NameInterface
    object Beka : NameInterface
}

enum class Names {
    GAGA, SOSO, SANDRO, BEKA
}