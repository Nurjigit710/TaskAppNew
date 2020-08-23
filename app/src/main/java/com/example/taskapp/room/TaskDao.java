package com.example.taskapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskapp.models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllLive();

    @Insert
    void insert (Task task);

    @Update
    void update (Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task")
    void deleteAll();

    @Query("SELECT * FROM task ORDER BY CASE WHEN :isAsc = 1 THEN task.title END ASC, CASE WHEN :isAsc = 0 THEN task.title END DESC")
    List<Task> getPersonsAlphabetically(boolean isAsc);

    @Query("SELECT * FROM task ORDER BY task.createdAt DESC" )
    List<Task> getLast();
    @Query("SELECT * FROM task ORDER BY task.createdAt ASC" )
    List<Task> getNew();

}

