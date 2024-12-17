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


        // メインスレッドで実行するとエラーになるのでスレッドを作る
        // どれか一つだけを使ってその他をコメントアウトしないと
        // 一度に複数の処理が走ってしまうので注意
        // めんどくさいことになる

        // ----ユーザー情報の表示----
        showAllUserInfo();

        // ----ユーザーの追加----
//        addUser();

        // ----ユーザの削除----
//        deleteUser(1);

    }

    // ユーザーの情報を表示する
    private void showAllUserInfo() {
        // ユーザーをすべて表示する
        class showAllData implements Runnable {
            @Override
            public void run() {
                Log.d("ROOM_DBG","----USER LIST----");
                // ユーザーを取得
                List<User> users = db.userDao().getAll();
                // ユーザーを一人ずつ取り出して表示
                for (User user : users) {
                    Log.d("User", user.uid + " : " + user.firstName + " " + user.lastName);
                }
                Log.d("ROOM_DBG","----END LIST----");
            }
        }
        // スレッドを作成して実行
        Thread thread1 = new Thread(new showAllData());
        thread1.start();
    }

    // ユーザーを追加する
    // すでに同じidのユーザーが存在するとエラーになる
    // 前のビルドのデータが残っているときはデータを削除するか
    // idを変更する
    private void addUser() {
        // ユーザーを追加する
        class addData implements Runnable {
            @Override
            public void run() {
                // ユーザを定義する
                // idが重複するとエラーになるので注意
                User user = new User();
                user.uid = 1;
                user.firstName = "Taro";
                user.lastName = "Yamada";

                // ユーザーを追加
                db.userDao().insertAll(user);
            }
        }
        // スレッドを作成して実行
        Thread thread2 = new Thread(new addData());
        thread2.start();
    }

    // ユーザーを削除する
    // 指定したidのデータを削除する
    private void deleteUser(int uid) {
        // ユーザーを削除する
        class deleteData implements Runnable {
            @Override
            public void run() {
                // ユーザーを取得
                User user = db.userDao().findById(uid);
                // ユーザーを削除
                db.userDao().delete(user);
            }
        }
        // スレッドを作成して実行
        Thread thread3 = new Thread(new deleteData());
        thread3.start();
    }

}