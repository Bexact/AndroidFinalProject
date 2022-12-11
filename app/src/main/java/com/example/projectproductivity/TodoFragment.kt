package com.example.projectproductivity

import android.content.Intent
import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.projectproductivity.databinding.FragmentTodoBinding
import com.example.projectproductivity.ui.AddFragment
import com.example.projectproductivity.viewmodel.TaskViewModel
import com.google.android.material.snackbar.Snackbar

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
        adapter.setOnItemClickListener(object : TaskAdapter.onItemClickListener{
            override fun onItemClicked(position: Int) {

//                val fragment = UpdateFragment()
//                val nextArgs = Bundle()
//                nextArgs.putString("position",position.toString() )
//
//                fragment.arguments = nextArgs

                findNavController().navigate(R.id.action_todoFragment_to_updateFragment)
            }
        })
        viewModel.getAllTasks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.tasksRecyclerView.adapter = adapter
        binding.fab2.setOnClickListener {
            findNavController().navigate(R.id.action_todoFragment_to_addFragment)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val taskEntry = adapter.currentList[position]
                viewModel.delete(taskEntry)

                Snackbar.make(binding.root,"Deleted",Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.insert(taskEntry)
                    }
                    show()
                }
            }

        }).attachToRecyclerView(binding.tasksRecyclerView)
        return binding.root
    }


}