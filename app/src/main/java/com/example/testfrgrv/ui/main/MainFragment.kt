package com.example.testfrgrv.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.testfrgrv.R


class MainFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var btnMain: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.main_fragment, container, false)
        btnMain = rootView.findViewById<View>(R.id.btn_main) as Button
        btnMain.setOnClickListener(this)
        return rootView
    }
    


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onClick(view: View) {
        var fragment: Fragment? = null
        when (view.id) {
            R.id.btn_main -> {
                fragment = RegisterFragment()
                replaceFragment(fragment)
            }
           /* R.id.phbookButton -> {
                fragment = PhoneBookFragment()
                replaceFragment(fragment)
            }*/
        }
    }

    fun replaceFragment(someFragment: Fragment?) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        someFragment?.let { transaction.replace(R.id.container, it) }
        transaction.addToBackStack(null)
        transaction.commit()
    }

}