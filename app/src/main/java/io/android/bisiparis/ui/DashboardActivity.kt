package io.android.bisiparis.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.android.bisiparis.R
import io.android.bisiparis.adapter.OrderListAdapter
import io.android.bisiparis.enums.GeneralInfo
import io.android.bisiparis.helper.DatabaseHelper
import io.android.bisiparis.model.OrderInfo
import io.android.bisiparis.singleton.DataHolder
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity(), View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private val rcyOrder by lazy { findViewById<RecyclerView>(R.id.activity_dashboard_rcyOrders) }
    private val swipeRefreshLayout by lazy { findViewById<SwipeRefreshLayout>(R.id.activity_dashboard_swipe) }
    private val databaseHelper by lazy { DatabaseHelper(this) }
    private val addDialog by lazy { AlertDialog.Builder(this).create() }
    private val btnTakeOrder by lazy { addDialog.findViewById<Button>(R.id.custom_alert_add_order_name_btnTakeOrder) }
    private val edtCustomerName by lazy { addDialog.findViewById<EditText>(R.id.custom_alert_add_order_name_edtCustomerName) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        title = intent.getStringExtra(GeneralInfo.UserName.toString()).toUpperCase() + GeneralInfo.Welcome.toString()

        initEvent()
    }

    private fun initEvent() {
        val result : ArrayList<OrderInfo> = databaseHelper.getAllOrders()
        swipeRefreshLayout.setOnRefreshListener(this)
        /*if (result.size == 0) {
            activity_dashboard_txtNoOrder.visibility = View.VISIBLE
            activity_dashboard_txtNoOrder.text = GeneralInfo.NoOrderValues.toString()
            swipeRefreshLayout.isRefreshing = false
        }*/
        val linearLayoutManager = LinearLayoutManager(this)
        val orderListAdapter = OrderListAdapter(result)

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                val deletedOrderInfo = OrderInfo(viewHolder!!.itemView.tag.toString().toLong(),
                        "", "", "", "", "")

                databaseHelper.deleteOrder(deletedOrderInfo)
                (rcyOrder.adapter as OrderListAdapter).setOrderList(databaseHelper.getAllOrders())
                rcyOrder.adapter.notifyDataSetChanged()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)

        rcyOrder.layoutManager = linearLayoutManager
        rcyOrder.adapter = orderListAdapter
        itemTouchHelper.attachToRecyclerView(rcyOrder)
    }

    private fun createAlertDialog() {
        val layoutInflater = LayoutInflater.from(this)
        val alertView = layoutInflater.inflate(R.layout.custom_alert_add_order_name, null, false)

        addDialog.setView(alertView)
        addDialog.show()

        btnTakeOrder?.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_dashboard, menu)
        return true
    }

    override fun onRefresh() {
        val result : ArrayList<OrderInfo> = databaseHelper.getAllOrders()
        /*if (result.size == 0) {
            activity_dashboard_txtNoOrder.visibility = View.VISIBLE
            activity_dashboard_txtNoOrder.text = GeneralInfo.NoOrderValues.toString()
            swipeRefreshLayout.isRefreshing = false
        }*/
        (rcyOrder.adapter as OrderListAdapter).setOrderList(result)
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(applicationContext, "Harikayım ben!", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_activity_dashboard_add_order -> {
                createAlertDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View?) {
        when ((view as Button).id) {
            R.id.custom_alert_add_order_name_btnTakeOrder -> {
                addDialog.dismiss()
                DataHolder.userName = edtCustomerName?.text.toString()
                edtCustomerName?.text = null
                val intent = Intent(this, ChoiceActivity::class.java)
                intent.putExtra(GeneralInfo.CustomerName.toString(), DataHolder.userName)
                startActivity(intent)
            }
        }
    }


}
