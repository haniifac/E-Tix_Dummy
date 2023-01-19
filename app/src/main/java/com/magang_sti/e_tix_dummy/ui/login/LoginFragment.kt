package com.magang_sti.e_tix_dummy.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.magang_sti.e_tix_dummy.R
import com.magang_sti.e_tix_dummy.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val userVM : UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin(){
        var isFound = false
        var isLocationValid = false

        val inpUsername = binding.edtUsername.text.toString()
        val inpPassword = binding.edtPassword.text.toString()

        binding.progressLogin.visibility = View.VISIBLE

        userVM.getAllUsers().observe(viewLifecycleOwner){
            var username = "none"
            val userLocationNotValid = arrayOf(11,50)
            val userLocationValid = arrayOf(1,5)

            if (it != null) {
                for (i in it) {
                    if (i.username == inpUsername && i.password == inpPassword) {
                        binding.progressLogin.visibility = View.INVISIBLE
                        isFound = true
                        username = i.username
                    }
                }
            }

            binding.progressLogin.visibility = View.INVISIBLE

            if (!isFound) {
                showDialog("Login Failed", "Error Code : -2")
            }else if(!userVM.isUserInEventLocation(userLocationValid)) {
                showDialog("Login Failed", "Error Code : -3")
            }else{
                showDialog("Login Success", "Welcome $username")
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

    private fun showDialog(title : String,msg : String) {
        //create dialog
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(title)
        dialog.setMessage(msg)
        val closeDialog = dialog.create()
        closeDialog.show()

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                closeDialog.dismiss()
                timer.cancel()
            }
        }, 2000)
    }

}