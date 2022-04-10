package com.zetung.android.zetpass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zetung.android.zetpass.databinding.FragmentEnterBinding

class EnterFragment : Fragment() {

    private var _binding: FragmentEnterBinding? = null
    private val binding get() = _binding!!
    private val enterPass = 423577251

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume(){
        super.onResume()

        binding.enterButton.setOnClickListener {
            if(binding.editPassword.text.toString().isEmpty() || binding.editPassword.text.toString() != enterPass.toString()){
                Toast.makeText(activity,R.string.error_auth_empty, Toast.LENGTH_SHORT).show()
            } else {
                coordinator().startMainFragment()
            }

        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}