package com.jaime.peligo.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.android.material.button.MaterialButton
import com.jaime.peligo.R
import com.jaime.peligo.databinding.ActivityMainBinding
import com.jaime.peligo.viewmodels.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapterMovies: AdapterMovies
    private lateinit var btnGetAll: Button
    private lateinit var btnGetPopular: Button
    private lateinit var btnGetTopRated: Button
    private lateinit var btnGetUpcoming: Button
    private lateinit var btnGoToComments: MaterialButton  // Nuevo botón para comentarios
    private lateinit var tvCategory: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Enlaces de controles
        btnGetAll = findViewById(R.id.btnGetAll)
        btnGetPopular = findViewById(R.id.btnGetPopular)
        btnGetTopRated = findViewById(R.id.btnGetTopRated)
        btnGetUpcoming = findViewById(R.id.btnGetUpcoming)
        btnGoToComments = findViewById(R.id.btnGoToComments)  // Inicialización del botón de comentarios
        tvCategory = findViewById(R.id.tvCategory)

        // Inicialización del ViewModel
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        setupRecyclerView()

        // Método por defecto
        tvCategory.text = "ESTRENOS"
        viewModel.getAllMovies()

        // Observadores y eventos
        viewModel.moviesList.observe(this) {
            adapterMovies.moviesList = it
            adapterMovies.notifyDataSetChanged()
        }
        btnGetAll.setOnClickListener {
            tvCategory.text = "ESTRENOS"
            viewModel.getAllMovies()
        }
        btnGetPopular.setOnClickListener {
            tvCategory.text = "POPULARES"
            viewModel.getPopular()
        }
        btnGetTopRated.setOnClickListener {
            tvCategory.text = "MEJOR CALIFICADO"
            viewModel.getTopRated()
        }
        btnGetUpcoming.setOnClickListener {
            tvCategory.text = "PRÓXIMAMENTE"
            viewModel.getUpcoming()
        }

        // Evento para ir a la pantalla de comentarios
        btnGoToComments.setOnClickListener {
            val intent = Intent(this, CommentsActivity::class.java)
            startActivity(intent)
        }
    }

    // Verifica la autenticación del usuario en onStart()
    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            // Si no hay usuario autenticado, redirige a LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvMovies.layoutManager = layoutManager
        adapterMovies = AdapterMovies(this, arrayListOf())
        binding.rvMovies.adapter = adapterMovies
    }
}
