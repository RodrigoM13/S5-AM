package com.example.clinicas5_rodrigo

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicas5_rodrigo.R

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        btnLogin.setOnClickListener {
            showLoginDialog()
        }

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    
    //Muestra un diálogo personalizado para que el usuario ingrese su nombre de usuario y contraseña.
    private fun showLoginDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_login, null)
        val usernameEditText = dialogView.findViewById<EditText>(R.id.etUsernameDialog)
        val passwordEditText = dialogView.findViewById<EditText>(R.id.etPasswordDialog)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Iniciar Sesión")
            .setView(dialogView)
            .setPositiveButton("Entrar", null)
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.setOnShowListener {
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                val username = usernameEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                } 
                // Validar credenciales usando UserStorage (clase externa que gestiona usuarios)
                else if (UserStorage.validateUser(username, password)) {
                    startActivity(Intent(this, IndexActivity::class.java).apply {
                        putExtra("username", username)
                    })
                    dialog.dismiss()
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        dialog.show()
    }
}
