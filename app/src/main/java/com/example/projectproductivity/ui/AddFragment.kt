package com.example.projectproductivity.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.projectproductivity.R
import com.example.projectproductivity.database.TaskEntry
import com.example.projectproductivity.databinding.FragmentAddBinding
import com.example.projectproductivity.viewmodel.TaskViewModel

class AddFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddBinding.inflate(inflater)

        val myAdapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.priorities)
        )

        binding.spinner2.adapter = myAdapter
        binding.btnAdd.setOnClickListener {
            if(TextUtils.isEmpty((binding.editTask.text))){
                Toast.makeText(requireContext(), "It is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val title_str = binding.editTask.text.toString()
            val priority = binding.spinner2.selectedItemPosition
            val taskEntry = TaskEntry(
                0,
                title_str,
                priority,
                System.currentTimeMillis()
            )

            viewModel.insert(taskEntry)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_todoFragment)
        }
        return binding.root
    }



}