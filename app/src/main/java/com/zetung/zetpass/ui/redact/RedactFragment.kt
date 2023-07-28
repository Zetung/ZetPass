package com.zetung.zetpass.ui.redact

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.zetung.zetpass.R
import com.zetung.zetpass.databinding.FragmentHomeBinding
import com.zetung.zetpass.databinding.FragmentRedactBinding
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.ui.ParserView
import com.zetung.zetpass.utils.TypeRecord
import dagger.hilt.android.AndroidEntryPoint
import hilt_aggregated_deps._com_zetung_zetpass_ui_home_HomeViewModel_HiltModules_BindsModule

@AndroidEntryPoint
class RedactFragment : Fragment() {

    private var _binding: FragmentRedactBinding? = null
    private val binding get() = _binding!!

    private val redactViewModel: RedactViewModel by viewModels()

    private var typeRecord: TypeRecord = TypeRecord.Standard()

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

        val adapter =
            ArrayAdapter.createFromResource(requireContext(), R.array.records,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRedact.adapter = adapter

        binding.submitButton.setOnClickListener {
            when(typeRecord.number){
                1 -> {
                    redactViewModel.addLocalRecord(typeRecord.type,
                        binding.noteSet.nameRedact.editText!!.text.toString(),
                        binding.noteSet.note.editText!!.text.toString(),
                        binding.descriptionRedact.text.toString())

                }
                else -> {

                }
            }
        }

        return binding.root
    }


    private fun loadView(recordModel: RecordModel){
        binding.mainSet.removeAllViews()
        if (recordModel.id == null){
            binding.deleteButton.visibility = View.GONE
            binding.doneButton.visibility = View.GONE
            binding.submitButton.visibility = View.VISIBLE
            binding.mainSet.addView(binding.standardSet.root)
            typeRecord = TypeRecord.Standard()
        } else
            loadContent(recordModel)
    }

    private fun loadContent(recordModel: RecordModel){
        when(recordModel.type){
            "note" -> {
                typeRecord = TypeRecord.Note()
                binding.spinnerRedact.setSelection(typeRecord.number)
                binding.spinnerImage.setImageResource(R.drawable.note_record24)

                binding.mainSet.addView(binding.noteSet.root)
                binding.noteSet.nameRedact.editText!!.text = SpannableStringBuilder(recordModel.name)
                binding.description.editText!!.text = SpannableStringBuilder(recordModel.description)

                binding.noteSet.note.editText!!.text = SpannableStringBuilder(
                    ParserView.parseStringToMap(recordModel.data,'!',';')["note"]
                )
            }
            else -> {
                typeRecord = TypeRecord.Standard()
                binding.spinnerRedact.setSelection(typeRecord.number)
                binding.spinnerImage.setImageResource(R.drawable.standard_record24)

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