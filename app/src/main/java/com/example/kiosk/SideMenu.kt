package com.example.kiosk

open class SideMenu : Food() {

    open fun feature() {   // 나중에 자식 클래스에서 overriding 해줄 것!
        println("맛있는 사이드 메뉴입니다.")
    }
}
