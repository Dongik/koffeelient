package site.dongik.goffee.view

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import site.dongik.goffee.R

class LoginActivity : AppCompatActivity() {

    lateinit var idInput: EditText
    lateinit var passwordInput: EditText
    lateinit var autoLogin: CheckBox
    var loginChecked: Boolean = false
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        idInput = findViewById(R.id.emailInput) as EditText
        passwordInput = findViewById(R.id.passwordInput) as EditText
        autoLogin = findViewById(R.id.checkBox) as CheckBox
        pref = applicationContext.getSharedPreferences("good", 0)


        // if autoLogin checked, get input
        if (pref.getBoolean("autoLogin", false)) {
            idInput.setText(pref.getString("id", ""))
            passwordInput.setText(pref.getString("pw", ""))
            autoLogin.isChecked = true
            // goto mainActivity
        } else {
            // if autoLogin unChecked
            val id = idInput.text.toString()
            val password = passwordInput.text.toString()
            val validation = loginValidation(id, password)

            if (validation) {
                Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_LONG).show()
                if (loginChecked) {
                    // if autoLogin Checked, save values
                    editor.putString("id", id)
                    editor.putString("pw", password)
                    editor.putBoolean("autoLogin", true)
                    editor.commit()
                }
                // goto mainActivity

            } else {
                Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_LONG).show()
                // goto LoginActivity
            }
        }

        // set checkBoxListener
        autoLogin.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                loginChecked = true
            } else {
                // if unChecked, removeAll
                loginChecked = false
                editor.clear()
                editor.commit()
            }
        }
    }

    private fun loginValidation(id: String, password: String): Boolean {
        if (pref.getString("id", "") == id && pref.getString("pw", "") == password) {
            // login success
            return true
        } else if (pref.getString("id", "") == null) {
            // sign in first
            Toast.makeText(this@LoginActivity, "Please Sign in first", Toast.LENGTH_LONG).show()
            return false
        } else {
            // login failed
            return false
        }
    }
}