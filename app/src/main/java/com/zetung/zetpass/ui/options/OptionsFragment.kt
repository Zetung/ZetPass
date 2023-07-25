package com.zetung.zetpass.ui.options

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zetung.zetpass.databinding.FragmentOptionsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OptionsFragment : Fragment() {

    private var _binding: FragmentOptionsBinding? = null
    private val binding get() = _binding!!

    private val optionsViewModel: OptionsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//         =
//            ViewModelProvider(this).get(OptionsViewModel::class.java)

        _binding = FragmentOptionsBinding.inflate(inflater, container, false)

        val editIp: EditText = binding.editIp
        optionsViewModel.ip.observe(viewLifecycleOwner) {
            editIp.text = SpannableStringBuilder(it)
        }

        val editPort: EditText = binding.editPort
        optionsViewModel.port.observe(viewLifecycleOwner) {
            editPort.text = SpannableStringBuilder(it.toString())
        }

        val onlineMode: SwitchCompat = binding.online
        optionsViewModel.online.observe(viewLifecycleOwner) {
            onlineMode.isChecked = it
        }

        binding.acceptButton.setOnClickListener {
            optionsViewModel.ip.value = binding.editIp.text.toString()
            optionsViewModel.port.value = binding.editPort.text.toString().toInt()
            optionsViewModel.online.value = binding.online.isChecked
            optionsViewModel.saveSettings()
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}