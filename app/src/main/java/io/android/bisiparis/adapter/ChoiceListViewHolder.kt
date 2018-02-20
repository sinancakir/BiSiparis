package io.android.bisiparis.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.android.bisiparis.R

/**
 * Created by sinan on 13.02.2018.
 */
class ChoiceListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imgFood by lazy { view.findViewById<ImageView>(R.id.adapter_item_choice_list_imgFood) }
    val txtName by lazy { view.findViewById<TextView>(R.id.adapter_item_choice_list_txtName) }
}