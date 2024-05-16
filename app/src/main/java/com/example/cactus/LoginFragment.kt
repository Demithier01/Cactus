package com.example.cactus

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.cactus.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var user: FirebaseAuth

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

        user = FirebaseAuth.getInstance()

        checkUserIsLogged()

        binding.btnLogin.setOnClickListener {
            loginUser()
        }
        binding.btnClick.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginUser() {
        val email = binding.username.editText?.text.toString()
        val password = binding.password.editText?.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            user.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->

                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Username Successful", Toast.LENGTH_SHORT).show()

                        findNavController().navigate(
                            R.id.action_loginFragment_to_retrofitFragment)
                    } else {
                        val alertDialogBuilder = AlertDialog.Builder(requireContext())
                        alertDialogBuilder.setTitle("Username or not correct!!")
                        alertDialogBuilder.setMessage("Please enter your username or password correctly.")
                        alertDialogBuilder.setNegativeButton("Close") { dialog, _ ->
                            dialog.dismiss()
                        }
                        val alertDialog = alertDialogBuilder.create()
                        alertDialog.show()
                        Toast.makeText(
                            requireContext(), task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle("Error message!!")
            alertDialogBuilder.setMessage("Please enter information")
            alertDialogBuilder.setNegativeButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun checkUserIsLogged(){
        if (user.currentUser !=null){
            findNavController().navigate(
                R.id.action_loginFragment_to_retrofitFragment)
        }
    }
}
