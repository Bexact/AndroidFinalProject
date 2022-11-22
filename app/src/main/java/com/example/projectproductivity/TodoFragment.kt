package com.example.projectproductivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.projectproductivity.databinding.FragmentTodoBinding
import com.example.projectproductivity.viewmodel.TaskViewModel

class TodoFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTodoBinding.inflate(inflater)

        binding.fab2.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_addFragment)
        }
        return binding.root
    }


}