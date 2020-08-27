package com.example.exercise_sharedpref

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(),View.OnClickListener {

    var sharedPreferences: SharedPreferences? = null
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences(getString(R.string.shared_pref_name),Context.MODE_PRIVATE)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        if (sharedPreferences?.contains(getString(R.string.username_key))!!) {
            view?.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        login_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            login_button -> {
                with(sharedPreferences?.edit()) {
                    this?.putString("USERNAME",username_input.text.toString())
                    this?.commit()
                }
                navController.navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

    }
}