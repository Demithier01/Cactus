package com.example.cactus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.cactus.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var user: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = FirebaseAuth.getInstance()

        binding.btnRegis.setOnClickListener {
            registerUser()
        }

        binding.btnClick.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun registerUser() {
        val email = binding.usernameRe.editText?.text.toString()
        val password = binding.passwordRe.editText?.text.toString()
        val confirmPassword = binding.passwordConfirm.editText?.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password == confirmPassword) {
                user.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {

                            val alertDialogBuilder = AlertDialog.Builder(requireContext())
                            alertDialogBuilder.setTitle("Registration Successful!")
                            alertDialogBuilder.setMessage("Please log in")
                            alertDialogBuilder.setPositiveButton("Back to Login") { _, _ ->

                                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                            }
                            alertDialogBuilder.setNegativeButton("Close") { dialog, _ ->

                                binding.usernameRe.editText?.text = null
                                binding.passwordRe.editText?.text = null
                                binding.passwordConfirm.editText?.text = null
                                dialog.dismiss()
                            }
                            val alertDialog = alertDialogBuilder.create()
                            alertDialog.show()
                        } else {
                            Toast.makeText(
                                requireContext(), task.exception!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(requireContext(), "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Email, Password, and Confirm Password cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }
}
