package com.example.projectproductivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.example.projectproductivity.database.TaskEntry
import com.example.projectproductivity.databinding.FragmentUpdateBinding
import com.example.projectproductivity.viewmodel.TaskViewModel


class UpdateFragment : Fragment() {

    private  val viewModel: TaskViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUpdateBinding.inflate(inflater)
        var position = 0
        if(arguments != null){
            val data = arguments?.getString("position")
            if (data != null) {
                position = data.toInt()
            }
        }
        val item = viewModel.getItemById(position.toInt())

//
//        item = "Updated title"
//        viewModel.updateItem(item)
        return binding.root
    }


}