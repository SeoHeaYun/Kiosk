package com.example.kiosk

import com.example.kiosk.ShoppingBasket.cheeseStickPrice  // Object Class import
import com.example.kiosk.ShoppingBasket.cocaColaPrice
import com.example.kiosk.ShoppingBasket.frenchFriesPrice
import com.example.kiosk.ShoppingBasket.hotSpicyBurgerPrice
import com.example.kiosk.ShoppingBasket.shoppingbasket
import com.example.kiosk.ShoppingBasket.spartaBurgerPrice
import com.example.kiosk.ShoppingBasket.spritePrice
import java.time.LocalDateTime
import kotlin.concurrent.thread

// 수정해야할 사항: 함수들끼리 파라미터 받으면서 잘 넘어가게! 뒤로가기도 다시 구현해야 함..

val spartaBurger = SpartaBurger()  // 모든 메뉴 생성자 호출
val hotSpicyBurger = HotSpicyBurger()
val frenchFries = FrenchFries()
val cheeseStick = CheeseStick()
val cocaCola = CocaCola()
val sprite = Sprite()

fun main() {
    while (true) {                      // 여기서부터 KIOSK 시작!
        println("■■■SPARTA BURGER■■■")
        println("최고의 맛과 품질을 자랑하는 스파르타 버거")
        println("스파르타 버거 30주년 기념")
        println("전품목 10% 할인 쿠폰 지급!")
        println("아무 키나 눌러서 주문시작!")

        var start = readLine()!!
        if (start is String) {  // readLine은 입력받은 모든 값을 String으로 반환하므로 아무키나 눌렀을 때, print됨. !isEmpty의 경우는 enter 값을 넣었을 때 공백으로 반환해서 여기선 사용하면 안됨.
            println("■■■MENU■■■")
            println("1.햄버거")
            println("2.사이드 메뉴")
            println("3.음료수")
            println("4.결제하기")
            println("5.종료하기")
        }


        // Chapter1. 메뉴 고르고 장바구니에 넣기
        while (true) {
            try {                   // 1~4 이외의 값 혹은 잘못눌렀을 때 예외처리
                var pick1 = readLine()!!.toInt()
                if (pick1 >= 1 && pick1 <= 5) {
                    when (pick1) {               // 선택항목 가독성을 위해 enum class(FirstPick.kt)에서 값뽑아옴
                        FirstPick.hamburger.pick -> {
                            println("1.시그니처 스파르타 버거 ┃8000원┃")
                            println("2.Hot&Spicy 버거 ┃7000원┃")
                            println("3.뒤로가기")
                            inputMyInfo1("pick1_1") // 두번째 선택 호출
                        }

                        FirstPick.sidemenu.pick -> {
                            println("1.감자튀김 ┃2000원┃")
                            println("2.치즈스틱 ┃2000원┃")
                            println("3.뒤로가기")
                            inputMyInfo1("pick1_2") // 두번째 선택 호출
                        }

                        FirstPick.drink.pick -> {
                            println("1.스프라이트 제로 ┃1500원┃")
                            println("2.코카콜라 제로 ┃1500원┃")
                            println("3. 뒤로가기")
                            inputMyInfo1("pick1_3") // 두번째 선택 호출
                        }

                        FirstPick.pay.pick -> {    // 결제창 호출
                            if (shoppingbasket.size < 1) {  // 장바구니에 아무것도 없으면 결제창 안띄어준다.
                                println("장바구니에 메뉴를 담아주세요.")
                                println("■■■MENU■■■")
                                println("1.햄버거")
                                println("2.사이드 메뉴")
                                println("3.음료수")
                                println("4.결제하기")
                                println("5.종료하기")
                                continue // 빠져나가면 처음부터 시작되니까 안되고, 다시 반복
                            } else {
                                pay1()
                            }
                        }

                        FirstPick.exit.pick -> {
                            println("첫 화면으로 이동됩니다.")
                            break
                        }
                    }
                } else {
                    println("1부터 4까지의 숫자만 입력가능합니다.")
                }
            } catch (e: Exception) {   // 예외처리
                println("1부터 4까지의 숫자만 입력가능합니다.")
            }
        }
    }
}

        fun inputMyInfo1(type: String): Any? { // 두 번째 선택
            fun myPick1(pick: String, feature: String) {  // 각 메뉴 항목(총3가지)에 대한 공통된 예외처리 부분을 함수로 추출해서 간소화하였음.
                println("${feature}")
                println("1.장바구니에 담기")
                println("2.장바구니에서 빼기")
                println("3.뒤로가기")
                inputMyInfo2(type2 = pick)  // 세번째 선택 호출   // 여기에서 인자받아서 함수호출하는게 왜 안되지?!
            }

            return when (type) {
                "pick1_1", "pick1_2", "pick1_3" -> {
                    while (true) {
                        try {      // 예외처리
                            var pick = readLine()!!.toInt()
                            if (pick >= 1 && pick <= 3) {
                                when (type) {
                                    "pick1_1" -> {  // 버거
                                        when (pick) {
                                            1 -> {
                                                 myPick1("a", "${spartaBurger.feature()}")
                                                 break
                                                 }

                                            2 -> {
                                                 myPick1("b", "${hotSpicyBurger.feature()}")
                                                 break
                                                 }
                                            3 -> {
                                                println("■■■MENU■■■")
                                                println("1.햄버거")
                                                println("2.사이드 메뉴")
                                                println("3.음료수")
                                                println("4.결제하기")
                                                println("5.종료하기")
                                                break
                                            }
                                        }
                                    }
                                    "pick1_2" -> { // 사이드메뉴
                                        when (pick) {
                                            1 -> {
                                                 myPick1("c", "${frenchFries.feature()}")
                                                 break
                                                  }
                                            2 -> {
                                                myPick1("d", "${cheeseStick.feature()}")
                                                break
                                                }
                                            3 -> {
                                                println("■■■MENU■■■")
                                                println("1.햄버거")
                                                println("2.사이드 메뉴")
                                                println("3.음료수")
                                                println("4.결제하기")
                                                println("5.종료하기")
                                                break
                                            }
                                        }
                                    }
                                    "pick1_3" -> { // 음료
                                        when (pick) {
                                            1 -> {
                                                myPick1("e", "${cocaCola.feature()}")
                                                break}
                                            2 -> {
                                                myPick1("f", "${sprite.feature()}")
                                                break}
                                            3 -> {
                                                println("■■■MENU■■■")
                                                println("1.햄버거")
                                                println("2.사이드 메뉴")
                                                println("3.음료수")
                                                println("4.결제하기")
                                                println("5.종료하기")
                                                break
                                            }
                                        }
                                    }
                                }
                            } else {
                                println("1부터 3까지의 숫자만 입력가능합니다~")
                            }
                        } catch (e: Exception) {
                            println("1부터 3까지의 숫자만 입력가능합니다~")
                        }
                    }
                }
                else -> {}
            }
        }


fun inputMyInfo2(type2:String) {  // 세번째 선택

        fun myPick2(menuPrice: Int) {  // ★장바구니에 담는 로직★: 각 음식 클래스에 정의된 가격 int 값을 ShoopingBasktet 리스트에 쌓아둔다.
            println("장바구니에 추가되었습니다!")
            shoppingbasket.add(menuPrice)

        fun removeMenu(pickedMenu: Int, shoppingbasket: MutableList<Int>) { // ★장바구니에서 제거하는 로직★
            if (pickedMenu in shoppingbasket) {   // 장바구니에 있으면 가장 최근 요소제거 하고 (여러개 담았을 수도 있으므로)
                var lastPickedMenu = 0 // 가장 최근 요소[리스트 기준 가장 마지막]의 인덱스를 저장할 변수
                for (i in shoppingbasket.indices) {   // 리스트 순회 후, 가장 마지막 요소의 인덱스 변수에 저장
                    if (shoppingbasket[i] == pickedMenu) {
                        lastPickedMenu = i
                        shoppingbasket.removeAt(lastPickedMenu)    // 해당 값 제거
                    }
                }
            } else {
                println("취소할 상품이 없습니다.") // 아무것도 없으면 담고오기
            }

        }

        while (true) {
            try {               // 예외처리
                var pick = readLine()!!.toInt()
                if (pick !in 1..3) {
                    println("1부터 3까지의 숫자만 입력가능합니다!")
                    continue   // while 반복문 내에 있으므로 사용가능한 것
                }
                when (type2) {
                    "a" -> {
                        when (pick) {
                            1 -> myPick2(spartaBurgerPrice)
                            2 -> removeMenu(spartaBurgerPrice, shoppingbasket)
                            3 -> break
                        }
                    }

                    "b" -> {
                        when (pick) {
                            1 -> myPick2(hotSpicyBurgerPrice)
                            2 -> removeMenu(hotSpicyBurgerPrice, shoppingbasket)
                            3 -> break
                        }
                    }

                    "c" -> {
                        when (pick) {
                            1 -> myPick2(frenchFriesPrice)
                            2 -> removeMenu(frenchFriesPrice, shoppingbasket)
                            3 -> break
                        }
                    }

                    "d" -> {
                        when (pick) {
                            1 -> myPick2(cheeseStickPrice)
                            2 -> removeMenu(cheeseStickPrice, shoppingbasket)
                            3 -> break
                        }
                    }

                    "e" -> {
                        when (pick) {
                            1 -> myPick2(cocaColaPrice)
                            2 -> removeMenu(cocaColaPrice, shoppingbasket)
                            3 -> break
                        }
                    }

                    "f" -> {
                        when (pick) {
                            1 -> myPick2(spritePrice)
                            2 -> removeMenu(spritePrice, shoppingbasket)
                            3 -> break
                        }
                    }
                }
            } catch (e: Exception) {
                println("1부터 3까지의 숫자만 입력가능합니다!")
            }
        }
    }
}

fun pay1 () {   // 결제창1
    while (true) {
        try {
            println("1.결제하기")
            println("2.뒤로가기")
            var totalPrice = 0  // 총 결제금액 = 장바구니 값들의 합
            var pick2 = readLine()!!.toInt()
            if (pick2 !in 1..2) {  // else 안쓰고 continue로 예외처리.
                println("1부터 2까지의 숫자만 입력가능합니다.")
                continue
            }
            when (pick2) {
                1 -> {
                    shoppingbasket.forEach { i -> totalPrice += i }  // get으로 또 빼서 더해줄 필요X 이미 i가 각 인덱스 역할을 하기 때문.
                    if (totalPrice > 0) {
                        println("총 결제 금액은 ${totalPrice}입니다.")
                        println("1.결제하기")
                        println("2.뒤로가기")
                        pay2("pay")  // 가장 아래에 빼놨음
                    } else {
                        println("결제할 메뉴가 없습니다. 장바구니에 메뉴를 넣어주세요!")
                    }
                }

                2 -> {
                    println("이전 페이지로 이동합니다.")
                }

                else -> {}
            }
        } catch (e: Exception) {
            println("1부터 2까지의 숫자만 입력가능합니다.")
        }
    }
}

fun pay2(pay: String) {  // 결제창2
    while (true) {
        try {
            var pick = readLine()!!.toInt()
            when (pick) {
                1 -> {
                    println("결제가 완료되었습니다. (결제는 해당 연결계좌에서 자동으로 이체됩니다.)")
                    println("감사합니다. 다음에 또 이용해주세요.")
                    println("결제완료 시간: ${LocalDateTime.now()}}")
                    break
                }

                2 -> {
                    println("이전 페이지로 이동합니다.")
                    break
                }

                else -> {
                    println("1부터 2까지의 숫자만 입력가능합니다.")
                }
            }
        } catch (e: Exception) {
            println("1부터 2까지의 숫자만 입력가능합니다.")
        }
    }
}





//fun thread (value:String) {
//
//
//}




