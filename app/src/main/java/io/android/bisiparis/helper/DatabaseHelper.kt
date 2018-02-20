package io.android.bisiparis.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.TextUtils
import io.android.bisiparis.enums.GeneralInfo
import io.android.bisiparis.model.OrderInfo

/**
 * Created by sinan on 10.02.2018.
 */
class DatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, GeneralInfo.DatabaseName.toString(), null, GeneralInfo.DatabaseVersion.toString().toInt()) {

    private val TABLE_ORDERS = "orders"
    private val KEY_ID = "id"
    private val KEY_NAME = "name"
    private val KEY_SOUP = "soup"
    private val KEY_MAINCOURSE = "mainCourse"
    private val KEY_DRINK = "drink"
    private val KEY_DESERT = "desert"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSQL = "CREATE TABLE $TABLE_ORDERS " +
                "($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$KEY_NAME nvarchar(30)," +
                "$KEY_SOUP nvarchar(30)," +
                "$KEY_MAINCOURSE nvarchar(30)," +
                "$KEY_DRINK nvarchar(30)," +
                "$KEY_DESERT nvarchar(30))"

        db!!.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        onCreate(db)
    }

    fun addOrder(orderInfo: OrderInfo) {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, orderInfo.orderName)
        contentValues.put(KEY_SOUP, orderInfo.orderSoup)
        contentValues.put(KEY_MAINCOURSE, orderInfo.orderMainCourse)
        contentValues.put(KEY_DRINK, orderInfo.orderDrink)
        contentValues.put(KEY_DESERT, orderInfo.orderDesert)
        db.insert(TABLE_ORDERS, null, contentValues)
        db.close()
    }

    fun updateOrder(orderInfo: OrderInfo): Int {
        val db = writableDatabase
        val contentValues = ContentValues()
        if (!TextUtils.isEmpty(orderInfo.orderName.trim()))
            contentValues.put(KEY_NAME, orderInfo.orderName)
        if (!TextUtils.isEmpty(orderInfo.orderSoup.trim()))
            contentValues.put(KEY_SOUP, orderInfo.orderSoup)
        if (!TextUtils.isEmpty(orderInfo.orderMainCourse.trim()))
            contentValues.put(KEY_MAINCOURSE, orderInfo.orderMainCourse)
        if (!TextUtils.isEmpty(orderInfo.orderDrink.trim()))
            contentValues.put(KEY_DRINK, orderInfo.orderDrink)
        if (!TextUtils.isEmpty(orderInfo.orderDesert.trim()))
            contentValues.put(KEY_DESERT, orderInfo.orderDesert)

        val effectedRows = db.update(TABLE_ORDERS, contentValues, "$KEY_ID = ?", arrayOf(orderInfo.orderId.toString()))
        db.close()
        return effectedRows
    }

    fun deleteOrder(orderInfo: OrderInfo) {
        val db = writableDatabase
        db.delete(TABLE_ORDERS, "$KEY_ID = ?", arrayOf(orderInfo.orderId.toString()))
        db.close()
    }

    fun getAllOrders(): ArrayList<OrderInfo> {

        val db = readableDatabase

        val orderInfoList = ArrayList<OrderInfo>()

        val selectQuery = "SELECT * FROM $TABLE_ORDERS"

        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {

            do {
                val orderInfo = OrderInfo(cursor.getString(0).toLong(),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5))

                orderInfoList.add(orderInfo)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return orderInfoList
    }

    fun getOrder(orderID: Long): OrderInfo {

        val db = this.readableDatabase

        val cursor = db.query(TABLE_ORDERS, arrayOf(KEY_ID, KEY_NAME, KEY_SOUP, KEY_MAINCOURSE, KEY_DRINK, KEY_DESERT),
                "$KEY_ID = ?", arrayOf(orderID.toString()), null, null, null, null)

        cursor?.moveToFirst()

        val orderInfo = OrderInfo(cursor.getString(0).toLong(),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5))

        cursor.close()
        db.close()

        return orderInfo
    }
}