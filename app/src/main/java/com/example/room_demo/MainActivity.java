package com.example.room_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        class dbTest extends Thread{
            public void run() {
                UserDao userDao = db.userDao();
                List<User> users = userDao.getAll();

                Log.i("DB_TEST",users.toString());
            }
        }
        Thread t = new dbTest();
        t.start();
    }

}