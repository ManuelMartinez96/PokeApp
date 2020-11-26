package com.example.pokeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.databinding.ActivityHomeBinding


enum class ProviderType {
    GOOGLE,
    FACEBOOK
}

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle = intent.extras
        val username = bundle?.getString("username")
        val provider = bundle?.getString("provider")
        setup(username ?: "", provider ?: "")

        binding.regionesRecycler.layoutManager = LinearLayoutManager(this)

        val rList = mutableListOf<Regiones>()
        rList.add(Regiones("Kanto"))
        rList.add(Regiones("Johto"))
        rList.add(Regiones("Hoenn"))
        rList.add(Regiones("Sinnoh"))
        rList.add(Regiones("Unova"))
        rList.add(Regiones("Kalos"))
        rList.add(Regiones("Alola"))
        rList.add(Regiones("Galar"))

        val adapter = RegionesAdapter()
        binding.regionesRecycler.adapter = adapter
        adapter.submitList(rList)

        adapter.onItemClickListener = {
            Toast.makeText(this, it.regionname, Toast.LENGTH_LONG).show()
        }

        if (rList.isEmpty()){
            binding.emptyView.visibility = View.VISIBLE
        } else {
            binding.emptyView.visibility = View.GONE
        }
    }

    private fun setup(username: String?, provider: String){
        title = "Inicio"
        binding.txtUsername.text = "Hola, $username"
//        binding.btnLogout.setOnClickListener {

//            if (provider == ProviderType.FACEBOOK.name){
//                LoginManager.getInstance().logOut()
//            } else if (provider == ProviderType.GOOGLE.name) {
//                signOutFromApp()
//            }


        }
    }

//    private fun signOutFromApp() {
//        GoogleSignIn.getClient(
//            this,
//            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
//        ).signOut()
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
