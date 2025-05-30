package com.example.clinicas5_rodrigo

import com.example.clinicas5_rodrigo.User

object UserStorage {

    private val users = mutableListOf<User>()
    
    // Método para agregar un nuevo usuario a la lista
    fun addUser(user: User) {
        users.add(user)
    }
    
    // Método para verificar si un usuario con el nombre dado ya existe en la lista
    fun userExists(username: String): Boolean {
        return users.any { it.username == username }
    }
    
    // Método para validar las credenciales de usuario (nombre y contraseña)
    fun validateUser(username: String, password: String): Boolean {
        return users.any { it.username == username && it.password == password }
    }
}
