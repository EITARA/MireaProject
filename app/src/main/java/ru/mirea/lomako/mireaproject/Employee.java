package ru.mirea.lomako.mireaproject;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Entity
public class Employee {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String salary;
    public String address;
    @Dao
    public interface EmployeeDao {
        @Query("SELECT * FROM employee")
        List<Employee> getAll();
        @Query("SELECT * FROM employee WHERE id = :id")
        Employee getById(long id);
        @Query("SELECT * FROM employee WHERE address = :address")
        Employee getByAddress(String address);
        @Insert
        void insert(Employee employee);
        @Update
        void update(Employee employee);
        @Delete
        void delete(Employee employee);
    }
}