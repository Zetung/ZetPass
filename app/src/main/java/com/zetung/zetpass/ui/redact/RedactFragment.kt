package com.zetung.zetpass.ui.redact

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zetung.zetpass.R
import com.zetung.zetpass.databinding.FragmentHomeBinding
import com.zetung.zetpass.databinding.FragmentRedactBinding
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.ui.ParserView
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

        redactViewModel.loadRedactRecord()
        val recordObserver = Observer<RecordModel> {
            loadView(it)
        }
        redactViewModel.redactRecord.observe(viewLifecycleOwner,recordObserver)



        return binding.root
    }


    private fun loadView(recordModel: RecordModel){
        binding.mainSet.removeAllViews()
        if (recordModel.id == null){
            binding.deleteButton.visibility = View.GONE
            binding.doneButton.visibility = View.GONE
            binding.mainSet.addView(binding.standardSet.root)
        } else
            when(recordModel.type){
                "note" -> {
                    binding.mainSet.addView(binding.noteSet.root)
                    binding.noteSet.nameRedact.editText!!.text = SpannableStringBuilder(recordModel.name)
                    binding.description.editText!!.text = SpannableStringBuilder(recordModel.description)

                    binding.noteSet.note.editText!!.text = SpannableStringBuilder(
                        ParserView.parseStringToMap(recordModel.data,'!',';')["note"]
                    )
                }
                else -> {
                    binding.mainSet.addView(binding.standardSet.root)
                    binding.standardSet.nameRedact.editText!!.text = SpannableStringBuilder(recordModel.name)
                    binding.description.editText!!.text = SpannableStringBuilder(recordModel.description)

                    binding.standardSet.loginRedact.editText!!.text = SpannableStringBuilder(
                        ParserView.parseStringToMap(recordModel.data,'!',';')["login"]
                    )
                    binding.standardSet.passwordRedact.editText!!.text = SpannableStringBuilder(
                        ParserView.parseStringToMap(recordModel.data,'!',';')["password"]
                    )
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}