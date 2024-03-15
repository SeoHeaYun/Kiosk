package com.example.kiosk

open class Drink : Food() {

    open fun feature() {   // 나중에 자식 클래스에서 overriding 해줄 것!
        println("맛있는 음료수입니다.")
    }
}
