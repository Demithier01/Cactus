package com.example.cactus.ui.loginAccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.cactus.R
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
                            SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Registration Successful!")
                                .setContentText("Please log in")
                                .setConfirmClickListener {
                                    it.dismissWithAnimation()
                                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                                }
                                .show()
                        } else {
                            SweetAlertDialog(requireContext(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Error")
                                .setContentText(task.exception!!.message)
                                .show()
                        }
                    }
            } else {
                SweetAlertDialog(requireContext(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error")
                    .setContentText("Password and Confirm Password do not match")
                    .show()
            }
        } else {
            SweetAlertDialog(requireContext(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error")
                .setContentText("Email, Password, and Confirm Password cannot be empty")
                .show()
        }
    }

}
