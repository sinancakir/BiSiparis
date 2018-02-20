package io.android.bisiparis.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.android.bisiparis.R
import io.android.bisiparis.interfaces.CustomAdapterClickListener

/**
 * Created by sinan on 13.02.2018.
 */
class ChoiceListAdapter(private var foodList: ArrayList<String>, private var images: Array<Int>, private var foodType: String, private val customAdapterClickListener: CustomAdapterClickListener) : RecyclerView.Adapter<ChoiceListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChoiceListViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_item_choice_list, parent, false)
        return ChoiceListViewHolder(view)
    }

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: ChoiceListViewHolder?, position: Int) {

        val foodStr = foodList[position]
        holder?.imgFood!!.setImageResource(images[position])
        holder.txtName.text = foodStr
        holder.itemView?.setOnClickListener { customAdapterClickListener.onCustomItemClickListener(foodType, foodStr) }
    }

    fun changeList(foodList: ArrayList<String>, images: Array<Int>, foodType: String) {
        this.foodList = foodList
        this.foodType = foodType
        this.images = images
    }
}