package com.zetung.zetpass.ui.redact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zetung.zetpass.R
import com.zetung.zetpass.databinding.FragmentHomeBinding
import com.zetung.zetpass.databinding.FragmentRedactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RedactFragment : Fragment() {

    private var _binding: FragmentRedactBinding? = null
    private val binding get() = _binding!!

    private val redactViewModel: RedactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRedactBinding.inflate(inflater,container,false)

        loadStandardView()
        return binding.root
    }

    private fun loadStandardView(){
        binding.standardSet.root.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}