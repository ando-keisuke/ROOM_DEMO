データベースORMのテストです。

このサイトの言うとおりに実装しました
https://developer.android.com/training/data-storage/room?hl=ja

build.gradleへの依存関係の追加とバージョン変更

MainActivity他、3ファイルを作成しただけです

-- 手順 --
1.エンティティを定義する
@Entityアノテーションを使って、テーブルを定義する

例 : Userエンティティ
// ↓この@Entityがアノテーション
@Entity
public class User {
    // ↓これもアノテーション
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
}

2.DAO(データアクセスオブジェクト)を定義する
@Daoアノテーションを使って、データベースにアクセスするためのメソッドを定義する

例 : UserDao
@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
           "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);


3.データベースを定義する
データベースはアプリがデータにアクセルするためのメインアクセスポイント
DAOごとに、引数がない、DAOのインスタンスを返す抽象メソッドを定義する必要がある

例 : AppDatabase

↓アノテーション
@Database(entities = {User.class}, version = 1)

// 引数がなくて、UserDaoのインスタンスを返す抽象メソッド

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

4.データベースのインスタンスを取得する
Room.databaseBuilder()を使って、データベースのインスタンスを取得する

例 : MainActivity
AppDatabase db = Room.databaseBuilder(getApplicationContext(),
        AppDatabase.class, "database-name").build();
