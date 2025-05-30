package com.example.clinicas5_rodrigo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DoctorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor)

        // Inicializa las vistas (ImageView y TextViews) del layout
        val imgDoctorProfile = findViewById<ImageView>(R.id.imgDoctorProfile)
        val txtDoctorNombre = findViewById<TextView>(R.id.txtDoctorNombre)
        val txtDoctorEspecialidad = findViewById<TextView>(R.id.txtDoctorEspecialidad)
        val txtDoctorCelular = findViewById<TextView>(R.id.txtDoctorCelular)
        val txtDoctorHorario = findViewById<TextView>(R.id.txtDoctorHorario)

        // Obtiene los datos del intent que fueron enviados desde otra actividad
        val nombre = intent.getStringExtra("doctorNombre") ?: "Desconocido"
        val especialidad = intent.getStringExtra("doctorEspecialidad") ?: "Especialidad desconocida"
        val celular = intent.getStringExtra("doctorCelular") ?: "No disponible"
        val horario = intent.getStringExtra("doctorHorario") ?: "No disponible"

        // Asigna los datos obtenidos a las vistas correspondientes
        txtDoctorNombre.text = nombre
        txtDoctorEspecialidad.text = especialidad
        txtDoctorCelular.text = "Celular: $celular"
        txtDoctorHorario.text = "Horario: $horario"

        // Selecciona la imagen de perfil a mostrar según el prefijo del nombre
        val nombreLower = nombre.trim().lowercase()

        when {
            nombreLower.startsWith("dr.") -> imgDoctorProfile.setImageResource(R.drawable.doctor)
            nombreLower.startsWith("dra.") || nombreLower.startsWith("lic.") -> imgDoctorProfile.setImageResource(R.drawable.doctora)
            else -> imgDoctorProfile.setImageResource(R.drawable.doctor)
        }
    }
}
