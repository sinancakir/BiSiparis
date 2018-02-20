package io.android.bisiparis.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.android.bisiparis.R
import kotlinx.android.synthetic.main.adapter_item_order_list.view.*

/**
 * Created by sinan on 11.02.2018.
 */
class OrderListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtOrderName by lazy { view.findViewById<TextView>(R.id.adapter_item_order_list_txtOrderName) }
    val txtOrderSoup by lazy { view.findViewById<TextView>(R.id.adapter_item_order_list_txtOrderSoup) }
    val txtOrderMainCourse by lazy { view.findViewById<TextView>(R.id.adapter_item_order_list_txtOrderMainCourse) }
    val txtOrderDrink by lazy { view.findViewById<TextView>(R.id.adapter_item_order_list_txtOrderDrink) }
    val txtOrderDesert by lazy { view.findViewById<TextView>(R.id.adapter_item_order_list_txtOrderDesert) }
}