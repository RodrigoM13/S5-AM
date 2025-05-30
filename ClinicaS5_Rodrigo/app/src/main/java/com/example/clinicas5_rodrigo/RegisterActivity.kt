package com.example.clinicas5_rodrigo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clinicas5_rodrigo.R
import com.example.clinicas5_rodrigo.User

class RegisterActivity : AppCompatActivity() {

    // Campo de texto para ingresar el nombre de usuario
    private lateinit var etUsername: EditText
    // Campo de texto para ingresar la contraseña
    private lateinit var etPassword: EditText
    // Botón para registrar un nuevo usuario    
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)
        
        // Listener para el botón de registrar usuario
        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            
            // Validar que los campos no estén vacíos
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // Verificar si el usuario ya existe en el almacenamiento
            if (UserStorage.userExists(username)) {
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // Agregar el nuevo usuario al almacenamiento (clase UserStorage externa)
            UserStorage.addUser(User(username, password))
            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
            finish() // Vuelve a LoginActivity
        }
    }
}
