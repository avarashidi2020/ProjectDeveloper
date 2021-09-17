package app.db_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import app.asynctask.AsyncTask;
import app.datamodel.Model_Post;

public class db_sqlite extends SQLiteOpenHelper {

  public static final String DB_NAME = "databASE.db";
  public static final String TBL_GETPOST = "tbl_getpost";
  public static final String KEY_IDPOST = "idpost";
  public static final String KEY_TITLE = "title";
  public static final String KEY_IMAGE = "image";
  public static final String KEY_link_VIDEO = "linkvideo";
  public static final String KEY_USERFAVORITE = "userfavorite";
  Context context;

  public db_sqlite(Context context) {
    super(context, DB_NAME, null, 1);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + TBL_GETPOST + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , idpost TEXT, title TEXT, image TEXT, linkvideo TEXT, userfavorite TEXT )");

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS" + TBL_GETPOST);
    onCreate(db);
  }

  public void Get_information(List<Model_Post> dataModel) {
    AsyncTask asyncTask = new AsyncTask(context, dataModel, this);
    asyncTask.execute();
  }

  public boolean Check_Id(int id) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery(" select * from " + TBL_GETPOST + " where id = '" + id + "'", null);
    if (cursor.getCount() > 0)
      return true;
    return false;
  }

  public boolean Check_Database() {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery(" select * from " + TBL_GETPOST, null);
    if (cursor.getCount() > 0)
      return true;
    return false;
  }

  public List<Model_Post> Get_Post() {
    List<Model_Post> lists = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery(" select * from " + TBL_GETPOST, null);
    cursor.moveToFirst();
    if (cursor.getCount() > 0) {
      while (!cursor.isAfterLast()) {
        Model_Post dataModel = new Model_Post();
        dataModel.setId(cursor.getString(1));
        dataModel.setTitle(cursor.getString(2));
        dataModel.setImage(cursor.getString(3));
        dataModel.setLinkvideo(cursor.getString(4));
        dataModel.setUserfavorite(cursor.getString(5));
        cursor.moveToNext();
        lists.add(dataModel);
      }
    }
    return lists;
  }
}
