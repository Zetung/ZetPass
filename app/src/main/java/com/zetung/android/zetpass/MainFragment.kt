package com.zetung.android.zetpass

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.zetung.android.zetpass.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var dataList:ArrayList<Parcelable>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

//        val data = mutableListOf<DataRecord>()
//        for(i in 1..10){
//            val dataRecord = DataRecord("$i app","$i log - ","$i pass")
//            data.add(dataRecord)
//        }

        dataList = arguments!!.getParcelableArrayList("DataRecordSession")
        val dataListAdapter: ArrayList<DataRecord> = dataList as ArrayList<DataRecord>

        val con = context!!
        binding.recordList.layoutManager = LinearLayoutManager(con)
        binding.recordList.adapter = DataRecordAdapter(dataListAdapter)


        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
//                search_view.clearFocus()
//                if(dataListAdapter.contains()){
//
//                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })


    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}