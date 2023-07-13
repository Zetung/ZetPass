package com.zetung.zetpass.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.zetung.zetpass.R
import com.zetung.zetpass.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.enterButton.setOnClickListener {
            //Navigation.findNavController(requireActivity().parent,R.layout.activity_main).navigate(R.id.homeFragment)
        }
    }

}