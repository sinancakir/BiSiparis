package io.android.bisiparis.model

/**
 * Created by sinan on 10.02.2018.
 */
data class OrderInfo(val orderId: Long = 0L, val orderName: String, val orderSoup: String, val orderMainCourse: String, val orderDrink: String, val orderDesert: String)