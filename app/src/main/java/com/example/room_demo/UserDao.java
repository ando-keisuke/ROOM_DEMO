package com.example.room_demo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// データアクセスオブジェクト
// データベースにアクセスするためのメソッドを定義する
// @Daoアノテーションをクラスの前につける
@Dao
public interface UserDao {
    // すべてのユーザーを取得するクエリ
    @Query("SELECT * FROM user")
    List<User> getAll();

    // uidが一致するユーザーをすべて取得するクエリ
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    // uidが一致するユーザーを取得するクエリ
    @Query("SELECT * FROM User WHERE uid = (:uid)")
    User findById(int uid);

    // first_nameとlast_nameが一致するユーザーを取得するクエリ
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    // ユーザーを追加する
    @Insert
    void insertAll(User... users);

    // ユーザーを削除する
    @Delete
    void delete(User user);
}