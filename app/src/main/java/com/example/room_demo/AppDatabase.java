package com.example.room_demo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.room_demo.User;
import com.example.room_demo.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
