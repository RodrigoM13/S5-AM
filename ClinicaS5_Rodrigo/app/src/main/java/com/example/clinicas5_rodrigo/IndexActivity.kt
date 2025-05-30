package com.example.clinicas5_rodrigo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class IndexActivity : AppCompatActivity() {
    
    // RecyclerView que mostrará la lista de doctores
    private lateinit var recyclerView: RecyclerView
    // Adaptador personalizado para la lista de doctores
    private lateinit var doctorAdapter: DoctorAdapter
    // Campo de texto para búsqueda y filtrado de doctores
    private lateinit var editTextBuscar: EditText
    // Lista original completa de doctores sin filtrar    
    private lateinit var listaDoctoresOriginal: List<Doctor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        recyclerView = findViewById(R.id.recyclerViewDoctors)
        recyclerView.layoutManager = LinearLayoutManager(this)
        editTextBuscar = findViewById(R.id.editTextBuscar)
        
        // Inicializa la lista original con todos los doctores (hardcodeados)
        listaDoctoresOriginal = listOf(
                Doctor("Traumotología", "Dr. Alberto Garcia Cerna", "987654321", "08:00 - 14:00"),
                Doctor("Traumotología", "Dr. Cruz Medina Martinez", "976543218", "08:00 - 14:00"),
                Doctor("Traumotología", "Dr. Edison Abanto Arroyo", "965432187", "08:00 - 14:00"),

                Doctor("Cardiología", "Dr. Roberto Chavesta Bernal", "954321876", "09:00 - 15:00"),

                Doctor("Cirugía Cardiovascular", "Dr. Romel Zamudio Silva", "943218765", "10:00 - 16:00"),

                Doctor("Cirugia General", "Dr. Beto Miranda Sevillano", "932187654", "07:30 - 13:30"),
                Doctor("Cirugia General", "Dr. Pool Jara Pesantes", "921876543", "07:30 - 13:30"),
                Doctor("Cirugia General", "Dr. Jonnatan Uribe", "910987654", "07:30 - 13:30"),

                Doctor("Dermatología", "Dr. Jaime Vega Sanchez", "909876543", "08:00 - 17:00"),
                Doctor("Dermatología", "Dr. Italo Vegas Jaramillo", "908765432", "08:00 - 17:00"),

                Doctor("Endocriniología", "Dr. Juan Pinto Sanchez", "907654321", "08:00 - 17:00"),

                Doctor("Gastroenterología", "Dra. Kelly Casanova Lau", "906543210", "08:00 - 17:00"),

                Doctor("Ginecología", "Dr. Jose Luis Espinoza Decena", "905432109", "08:00 - 17:00"),
                Doctor("Ginecología", "Dra. Yulissa Paredes Padilla", "904321098", "08:00 - 17:00"),
                Doctor("Ginecología", "Dr. Julio Ostolaza Rodriguez", "903210987", "08:00 - 17:00"),

                Doctor("Medicina Física", "Dr. Luis Vasquez", "902109876", "08:00 - 17:00"),

                Doctor("Medicina General", "Dr. Elwis Laveriano Hoyos", "901098765", "08:00 - 17:00"),
                Doctor("Medicina General", "Dra. Maria Cristina Saavedra", "900987654", "08:00 - 17:00"),

                Doctor("Medicina Interna", "Dr. Luis Cabrera Cipran", "999876543", "08:00 - 17:00"),
                Doctor("Medicina Interna", "Dra. Rosa Casimiro Lau", "998765432", "08:00 - 17:00"),
                Doctor("Medicina Interna", "Dra. Yuriko Zuñiga Lavado", "997654321", "08:00 - 17:00"),

                Doctor("Neumología", "Dra. Yessica Montoya Caldas", "996543210", "08:00 - 17:00"),
                Doctor("Neumología", "Dr. Walter Centurion Jaramillo", "995432109", "08:00 - 17:00"),

                Doctor("Neurocirugía", "Dr. Willy Caballero Grados", "994321098", "08:00 - 17:00"),

                Doctor("Neurología", "Dr. Rosnel Valdivieso Velarde", "993210987", "08:00 - 17:00"),

                Doctor("Nutrición", "Lic. Carmela Carbajal", "992109876", "08:00 - 17:00"),

                Doctor("Odontología", "Dr. Pedro Cipriano Alegre", "991098765", "08:00 - 17:00"),
                Doctor("Odontología", "Dra. Carolina Acuña Calderón", "990987654", "08:00 - 17:00"),

                Doctor("Cirugía Maxilofacial", "Dr. Julio Robles Zanelli", "989876543", "08:00 - 17:00"),

                Doctor("Otorrino", "Dr. Jorge Bonilla Vargas", "988765432", "08:00 - 17:00"),

                Doctor("Oftalmología", "Dr. Jorge Bonilla Vargas", "987654320", "08:00 - 17:00"),
                Doctor("Oftalmología", "Dra. Anita Vigo Arroyo", "986543219", "08:00 - 17:00"),

                Doctor("Pediatría", "Dra. Rosa Angeldonis Hurtado", "985432198", "08:00 - 17:00"),
                Doctor("Pediatría", "Dra. Ana Lopez", "984321987", "08:00 - 17:00"),
                Doctor("Pediatría", "Dr. Jose Lozano", "983210976", "08:00 - 17:00"),

                Doctor("Psicología", "Lic. Astrid Manrique Marron", "982109865", "08:00 - 17:00"),
                Doctor("Psicología", "Lic. Luz Vasquez Burgos", "981098754", "08:00 - 17:00"),

                Doctor("Psiquiatría", "Dra. Celmira Lazaro Loyola", "980987643", "08:00 - 17:00"),

                Doctor("Reumatología", "Dr. Frank Ocaña Vasquez", "979876532", "08:00 - 17:00"),

                Doctor("Urología", "Dr. Carlos Morales Flores", "978765421", "08:00 - 17:00"),
                Doctor("Urología", "Dr. Luis Pascual Plasencia", "977654310", "08:00 - 17:00"),
                Doctor("Urología", "Dr. Jose Acosta Fuentes", "976543219", "08:00 - 17:00"),
            )

        doctorAdapter = DoctorAdapter(listaDoctoresOriginal.toMutableList())
        recyclerView.adapter = doctorAdapter

        // Se agrega un listener para detectar cambios en el EditText de búsqueda
        editTextBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val texto = s.toString().lowercase()
                val filtrados = listaDoctoresOriginal.filter {
                    it.especialidad.lowercase().contains(texto) || it.nombre.lowercase().contains(texto)
                }
                doctorAdapter.actualizarLista(filtrados)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
