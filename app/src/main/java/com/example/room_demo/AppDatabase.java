package com.example.room_demo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.room_demo.User;
import com.example.room_demo.UserDao;

// Databaseアノテーションを付けたクラスを作成する
// entitiesには、データベースに保存するエンティティクラスを指定する
// このクラスでは、引数ゼロでDAO(データアクセスオブジェクト)を返す
// 引数のない抽象メソッドを定義する必要がある
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    // 引数ゼロで、UserDaoインターフェースを返すメソッド
    public abstract UserDao userDao();
}
