package io.android.bisiparis.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import io.android.bisiparis.R
import io.android.bisiparis.adapter.ChoiceListAdapter
import io.android.bisiparis.enums.GeneralInfo
import io.android.bisiparis.helper.DatabaseHelper
import io.android.bisiparis.interfaces.CustomAdapterClickListener
import io.android.bisiparis.model.OrderInfo
import io.android.bisiparis.singleton.DataHolder
import kotlinx.android.synthetic.main.activity_choice.*
import kotlinx.android.synthetic.main.adapter_item_choice_list.*

class ChoiceActivity : AppCompatActivity(), CustomAdapterClickListener {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.activity_choice_rcyChoice) }
    private val databaseHelper by lazy { DatabaseHelper(this) }

    private lateinit var mSoup: String
    private lateinit var mMainCourse: String
    private lateinit var mDesert: String
    private lateinit var mDrink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = ChoiceListAdapter(DataHolder.getSoup(), DataHolder.imagesSoup, "soup", this)
    }

    override fun onCustomItemClickListener(orderedFoodType: String, foodName: String) {
        when (orderedFoodType) {

            "soup" -> {
                activity_choice_txtHeader.text = "Ana Yemek Seçimi"
                mSoup = foodName
                (recyclerView.adapter as ChoiceListAdapter).changeList(DataHolder.getMainMeal(), DataHolder.imagesMainCourse, "mainCourse")
            }

            "mainCourse" -> {
                activity_choice_txtHeader.text = "İçecek Seçimi"
                mMainCourse = foodName
                (recyclerView.adapter as ChoiceListAdapter).changeList(DataHolder.getDrink(), DataHolder.imagesDrink, "drink")
            }

            "drink" -> {
                activity_choice_txtHeader.text = "Tatlı Seçimi"
                mDrink = foodName
                (recyclerView.adapter as ChoiceListAdapter).changeList(DataHolder.getDesert(), DataHolder.imagesDesert, "desert")
            }

            "desert" -> {
                mDesert = foodName
                val clientName = intent.getStringExtra(GeneralInfo.CustomerName.toString())
                val orderInfo = OrderInfo(orderName = clientName, orderSoup = mSoup, orderMainCourse = mMainCourse, orderDrink = mDrink, orderDesert = mDesert)
                databaseHelper.addOrder(orderInfo)
                finish()
            }
        }
        (recyclerView.adapter as ChoiceListAdapter).notifyDataSetChanged()
    }
}
