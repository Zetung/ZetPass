package com.zetung.android.zetpass

import android.os.Bundle
import android.util.Xml
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.zetung.android.zetpass.databinding.FragmentRedactBinding
import java.io.IOException
import java.io.StringWriter

class RedactFragment : Fragment() {

    private var _binding: FragmentRedactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRedactBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.okButton.setOnClickListener {
            okSave()
        }

    }

    private fun okSave(){
        if(binding.editApplication.text.toString().isEmpty() ||
            binding.editPassword.text.toString().isEmpty() ||
            binding.editLogin.text.toString().isEmpty()){
            Toast.makeText(activity,R.string.error_auth_empty, Toast.LENGTH_SHORT).show()
        } else {
            val dataRecord = DataRecord(binding.editApplication.text.toString(),
                binding.editLogin.text.toString(),
                binding.editPassword.text.toString())
            setFragmentResult("requestNewRecord",
                bundleOf("newRecord" to dataRecord))
            coordinator().startMainFragment()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.done_setting -> okSave()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}