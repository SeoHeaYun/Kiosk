package com.example.kiosk

object ShoppingBasket {  // 장바구니 값은 언제든지 접근 가능하도록 object로 뺐음
    val shoppingbasket = mutableListOf<Int>() // 장바구니에 추가한 항목들을 add하는 리스트! 나중에 결제할 때, 이 값을 총액(for문 돌리기)으로 하여 return 할 예정
    val spartaBurgerPrice = spartaBurger.price
    val hotSpicyBurgerPrice = hotSpicyBurger.price
    val frenchFriesPrice = frenchFries.price
    val cheeseStickPrice = cheeseStick.price
    val cocaColaPrice = cocaCola.price
    val spritePrice = sprite.price

}