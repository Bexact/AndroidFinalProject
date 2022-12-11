package com.example.projectproductivity

import android.os.Bundle
import android.text.Layout.Directions
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
    private lateinit var adapter: TaskAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTodoBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = TaskAdapter()

        viewModel.getAllTasks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.tasksRecyclerView.adapter = adapter
        binding.fab2.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_addFragment)
        }
        return binding.root
    }


}