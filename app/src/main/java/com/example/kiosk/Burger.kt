package com.example.kiosk

import com.example.kiosk.ShoppingBasket.shoppingbasket

open class Burger : Food() {

    open fun feature() {   // 나중에 자식 클래스에서 overriding 해줄 것!
        println("맛있는 햄버거입니다.")
    }
}



