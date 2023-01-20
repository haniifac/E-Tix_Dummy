package com.magang_sti.e_tix_dummy.ui.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
import androidx.navigation.fragment.findNavController
import com.magang_sti.e_tix_dummy.R
import com.magang_sti.e_tix_dummy.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.btnLogout.setOnClickListener {
            showDialogLogout()
        }
    }

    private fun gotoLogin() {
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }

    private fun showDialogLogout(){
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Logout")
        dialog.setMessage("Are you sure want to logout?")
        dialog.setPositiveButton("Yes"){dialog, which ->
            gotoLogin()
        }
        dialog.setNegativeButton("No"){dialog, which ->
            dialog.dismiss()
        }
        dialog.create().show()
    }
}