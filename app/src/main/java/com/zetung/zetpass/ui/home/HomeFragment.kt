package com.zetung.zetpass.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zetung.zetpass.databinding.FragmentHomeBinding
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.ui.OnRecordClickListener


class HomeFragment : Fragment(), OnRecordClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val recordList = mutableListOf<RecordModel>()
        recordList.add(RecordModel(null,"Zetung",
            "standard","Minecraft",
            "login!Robbey831;password!zxc1234;","----"))
        recordList.add(RecordModel(null,"Zetung",
            "note","HContent",
            "artist good","----"))
        recordList.add(RecordModel(null,"Zetung",
            "standard","Steam",
            "login!Robbey831;password!zxc1234;","----"))

        val adapter = HomeAdapter(recordList)
        adapter.setOnButtonClickListener(this)
        binding.homeView.layoutManager = LinearLayoutManager(requireContext())
        binding.homeView.adapter = adapter


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecordClick(position: Int, data: RecordModel) {
        TODO("Not yet implemented")
    }
}