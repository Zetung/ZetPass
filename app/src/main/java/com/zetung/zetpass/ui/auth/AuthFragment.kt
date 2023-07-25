package com.zetung.zetpass.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.zetung.zetpass.R
import com.zetung.zetpass.databinding.FragmentAuthBinding
import com.zetung.zetpass.utils.AuthState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater,container,false)

        val state = Observer<AuthState> { msgState ->
            Toast.makeText(context,msgState.msg,Toast.LENGTH_SHORT).show()
            if (msgState is AuthState.Done)
                findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }
        authViewModel.msgState.observe(viewLifecycleOwner, state)

        binding.enterButton.setOnClickListener {
            authViewModel.enter(binding.editLogin.text.toString(),binding.editPassword.text.toString())
        }

        binding.regButton.setOnClickListener{
            authViewModel.reg(binding.editLogin.text.toString(),binding.editPassword.text.toString())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}