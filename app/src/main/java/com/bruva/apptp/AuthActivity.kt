package com.bruva.apptp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val userName: EditText = findViewById(R.id.user_name)
        val userLogin: EditText = findViewById(R.id.user_login)
        val userPassword: EditText = findViewById(R.id.user_password)
        val buttonreg: Button = findViewById(R.id.button_reg)
        val authLink: TextView = findViewById(R.id.auth_link)
        authLink.setOnClickListener{
            val intent = Intent(this, AuthPassActivity::class.java)
            startActivity(intent)
        }
        buttonreg.setOnClickListener{
            val name = userName.text.toString().trim()
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (name == "" || login == "" || password == "")
                Toast.makeText(this, "Проверьте заполненность полей", Toast.LENGTH_SHORT).show()
            else {
                val user = User(name, login, password)

                val db = DbHelper(this,null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $login добавлен", Toast.LENGTH_SHORT).show()

                userName.text.clear()
                userLogin.text.clear()
                userPassword.text.clear()

            }
        }
    }
}