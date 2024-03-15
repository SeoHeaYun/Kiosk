package com.example.kiosk

class SpartaBurger : Burger() {
    val price = 8000

    override fun feature() {
        println("스파르타 버거의 시그니처 버거!")
    }
}