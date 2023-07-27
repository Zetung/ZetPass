package com.zetung.zetpass.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zetung.zetpass.R
import com.zetung.zetpass.databinding.FragmentHomeBinding
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.ui.OnRecordClickListener
import com.zetung.zetpass.utils.LoadState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), OnRecordClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel : HomeViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        homeViewModel.setupLocalRecords()
        val adapter = HomeAdapter(homeViewModel.records.value.orEmpty().toMutableList())
        val recordObserver = Observer<MutableList<RecordModel>> {
            adapter.records = it
            adapter.notifyDataSetChanged()
        }
        homeViewModel.records.observe(viewLifecycleOwner,recordObserver)

        val state = Observer<LoadState> { msgState ->
            if(msgState.msg != "Done!")
                Toast.makeText(context,msgState.msg, Toast.LENGTH_SHORT).show()
        }
        homeViewModel.msgState.observe(viewLifecycleOwner, state)


        adapter.setOnButtonClickListener(this)
        binding.homeView.layoutManager = LinearLayoutManager(requireContext())
        binding.homeView.adapter = adapter

        binding.addButton.setOnClickListener {
            homeViewModel.setRedactRecord(RecordModel(null,"","standard","","",""))
            findNavController().navigate(R.id.action_nav_home_to_redactFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecordClick(position: Int, data: RecordModel) {
        homeViewModel.setRedactRecord(data)
        findNavController().navigate(R.id.action_nav_home_to_redactFragment)
    }
}