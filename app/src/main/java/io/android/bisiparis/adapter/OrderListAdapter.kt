package io.android.bisiparis.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.android.bisiparis.R
import io.android.bisiparis.interfaces.CustomAdapterClickListener
import io.android.bisiparis.model.OrderInfo

/**
 * Created by sinan on 11.02.2018.
 */
class OrderListAdapter(private var orderList: ArrayList<OrderInfo>/*,
                       private val customAdapterClickListener: CustomAdapterClickListener*/)
    : RecyclerView.Adapter<OrderListViewHolder>() {

    fun setOrderList(orderList: ArrayList<OrderInfo>) {
        this.orderList = orderList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): OrderListViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_item_order_list, parent, false)
        return OrderListViewHolder(view)
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: OrderListViewHolder?, position: Int) {
        val orderInfo = orderList[position]
        holder?.txtOrderName?.text = orderInfo.orderName
        holder?.txtOrderSoup?.text = orderInfo.orderSoup
        holder?.txtOrderMainCourse?.text = orderInfo.orderMainCourse
        holder?.txtOrderDrink?.text = orderInfo.orderDrink
        holder?.txtOrderDesert?.text = orderInfo.orderDesert
        holder?.itemView?.tag = orderInfo.orderId
        /*holder?.itemView?.setOnClickListener {
            customAdapterClickListener.onCustomItemClickListener(orderInfo, position)
        }*/
    }
}