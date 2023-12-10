package com.bruva.apptp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AuthPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_pass)


        val userLogin: EditText = findViewById(R.id.loginp)
        val userPassword: EditText = findViewById(R.id.passwordp)
        val buttonreg: Button = findViewById(R.id.Pass_button)
        val RegLink: TextView = findViewById(R.id.reglink)

        RegLink.setOnClickListener{
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

        buttonreg.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (login == "" || password == "")
                Toast.makeText(this, "Проверьте заполненность полей", Toast.LENGTH_SHORT).show()
            else {
                val db = DbHelper(this,null)
                val isAuth = db.getUser(login, password)
                if(isAuth) {
                    Toast.makeText(this, "Пользователь $login авторизован", Toast.LENGTH_SHORT).show()
                    userLogin.text.clear()
                    userPassword.text.clear()

                    val intent = Intent(this, ContentActivityFirst::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this, "Пользователь $login не найден", Toast.LENGTH_SHORT).show()

            }
        }
    }

}