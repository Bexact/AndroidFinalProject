package com.example.projectproductivity.repository

import androidx.lifecycle.LiveData
import com.example.projectproductivity.database.TaskDao
import com.example.projectproductivity.database.TaskEntry

class TaskRepository(val taskDao:TaskDao) {

    suspend fun insert(taskEntry: TaskEntry) = taskDao.insert(taskEntry)

    suspend fun update(taskEntry: TaskEntry) = taskDao.update(taskEntry)

    suspend fun deleteItem(taskEntry: TaskEntry) = taskDao.delete(taskEntry)

    suspend fun deleteAll(){
        taskDao.deleteAll()
    }

    fun getAllTasks() : LiveData<List<TaskEntry>> = taskDao.getAllTaks()

}