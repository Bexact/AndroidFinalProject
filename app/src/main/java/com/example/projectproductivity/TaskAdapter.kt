package com.example.projectproductivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectproductivity.database.TaskEntry
import com.example.projectproductivity.databinding.RowLayoutBinding
import com.example.projectproductivity.ui.AddFragment
import com.google.android.play.core.tasks.Tasks
import java.security.AccessController.getContext

class TaskAdapter : ListAdapter<TaskEntry, TaskAdapter.ViewHolder>(TaskDiffCallback) {

//    private val TasksList = ArrayList<TaskEntry>()
//    private val fullList = ArrayList<TaskEntry>()
    private lateinit var mListener: onItemClickListener
    companion object TaskDiffCallback : DiffUtil.ItemCallback<TaskEntry>(){
        override fun areItemsTheSame(oldItem: TaskEntry, newItem: TaskEntry) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TaskEntry, newItem: TaskEntry) = oldItem == newItem
    }

    class ViewHolder(val binding: RowLayoutBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {

                listener.onItemClicked(adapterPosition)

            }
        }

        fun bind(taskEntry: TaskEntry){
            binding.taskEntry = taskEntry
            binding.executePendingBindings()
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
//        val cur1 = TasksList[position]
//        holder.title.text = cur1.title
//        holder.title.isSelected = true
//        holder.priority.text = cur1.priority.toString()
//        holder.date.text = cur1.timestamp.toString()
//        holder.date.isSelected = true
//
//        holder.tasks_layout.setOnClickListener {
//            listener.onItemClicked(TasksList[holder.adapterPosition])
//        }
//        holder.tasks_layout.setOnLongClickListener {
//            listener.longItemClicked(TasksList[holder.adapterPosition],holder.tasks_layout)
//            true
//        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false),mListener)

    }

//    fun updateList(newList: List<TaskEntry>){
//        fullList.clear()
//        fullList.addAll(newList)
//
//        TasksList.clear()
//        TasksList.addAll(fullList)
//        notifyDataSetChanged()
//
//    }
    interface onItemClickListener{
        fun onItemClicked(position: Int)

    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
}
