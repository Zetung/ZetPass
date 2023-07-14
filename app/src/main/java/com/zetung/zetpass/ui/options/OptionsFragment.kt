package com.zetung.zetpass.ui.options

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.ViewModelProvider
import com.zetung.zetpass.databinding.FragmentOptionsBinding


class OptionsFragment : Fragment() {

    private var _binding: FragmentOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(OptionsViewModel::class.java)
       // homeViewModel.setContext(requireContext())

        _binding = FragmentOptionsBinding.inflate(inflater, container, false)

        val editIp: EditText = binding.editIp
        homeViewModel.ip.observe(viewLifecycleOwner) {
            editIp.text = SpannableStringBuilder(it)
        }

        val editPort: EditText = binding.editPort
        homeViewModel.port.observe(viewLifecycleOwner) {
            editPort.text = SpannableStringBuilder(it.toString())
        }

        val onlineMode: SwitchCompat = binding.online
        homeViewModel.online.observe(viewLifecycleOwner) {
            onlineMode.isChecked = it
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}