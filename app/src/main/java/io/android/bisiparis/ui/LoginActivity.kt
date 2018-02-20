package io.android.bisiparis.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import io.android.bisiparis.R
import io.android.bisiparis.enums.GeneralInfo
import io.android.bisiparis.model.GeneralSingleton

class LoginActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private val edtUserName by lazy { findViewById<EditText>(R.id.activity_login_edtUserName) }
    private val edtPassword by lazy { findViewById<EditText>(R.id.activity_login_edtPassword) }
    private val chkRememberMe by lazy { findViewById<CheckBox>(R.id.activity_login_chkRememberMe) }
    private val btnLogin by lazy { findViewById<Button>(R.id.activity_login_btnLogin) }
    private lateinit var customSharedPreference: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        customSharedPreference = getSharedPreferences(GeneralInfo.SharedPrefName.toString(), Context.MODE_PRIVATE)

        if (customSharedPreference.getString(GeneralInfo.RememberMe.toString(), "") != "") {
            edtUserName.setText(customSharedPreference.getString(GeneralInfo.RememberMe.toString(), ""))
            chkRememberMe.isChecked = customSharedPreference.getBoolean(GeneralInfo.CheckBoxIsChecked.toString(), false)
        } else {
            chkRememberMe.isChecked = customSharedPreference.getBoolean(GeneralInfo.CheckBoxIsChecked.toString(), false)
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (edtUserName.text.trim().toString() != "" && edtPassword.text.trim().toString() != "") {
                    btnLogin.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (chkRememberMe.isChecked == customSharedPreference.getBoolean(GeneralInfo.CheckBoxIsChecked.toString(), false)) {
                    customSharedPreference = getSharedPreferences(GeneralInfo.SharedPrefName.toString(), Context.MODE_PRIVATE)
                    val editor = customSharedPreference.edit()
                    editor.putString(GeneralInfo.RememberMe.toString(), edtUserName.text.toString())
                    editor.apply()
                }
            }
        }

        edtUserName.addTextChangedListener(textWatcher)
        edtPassword.addTextChangedListener(textWatcher)

        chkRememberMe.setOnCheckedChangeListener(this)

        btnLogin.setOnClickListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        customSharedPreference = getSharedPreferences(GeneralInfo.SharedPrefName.toString(), Context.MODE_PRIVATE)
        val editor = customSharedPreference.edit()
        if (isChecked) {
            editor.putString(GeneralInfo.RememberMe.toString(), edtUserName.text.toString())
            editor.putBoolean(GeneralInfo.CheckBoxIsChecked.toString(), chkRememberMe.isChecked)
        } else {
            editor.putString(GeneralInfo.RememberMe.toString(), "")
            editor.putBoolean(GeneralInfo.CheckBoxIsChecked.toString(), chkRememberMe.isChecked)
        }
        editor.apply()
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, DashboardActivity::class.java)
        GeneralSingleton.userName = edtUserName.text.toString().trim()
        intent.putExtra(GeneralInfo.UserName.toString(), GeneralSingleton.userName)
        startActivity(intent)
        finish()
    }
}
