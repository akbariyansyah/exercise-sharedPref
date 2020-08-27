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
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),View.OnClickListener {
    lateinit var navController: NavController
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences(getString(R.string.shared_pref_name),Context.MODE_PRIVATE)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = sharedPreferences?.getString("USERNAME","Default")
        val say_hi = getString(R.string.say_hello)
        home_text_view.text = say_hi + " " +  username
        navController = Navigation.findNavController(view)
        logout_button.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v) {
            logout_button -> {
                with(sharedPreferences?.edit()) {
                    this?.remove(getString(R.string.username_key))
                    this?.commit()
                }
                navController.navigate(R.id.action_homeFragment_to_loginFragment)


            }
        }
    }
}