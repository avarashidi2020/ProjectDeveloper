package app.asynctask;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import app.datamodel.Model_Post;
import app.db_sqlite.db_sqlite;

public class AsyncTask extends android.os.AsyncTask<Void, Void, Void> {
  Context context;
  List<Model_Post> dataModel;
  db_sqlite db;

  public AsyncTask(Context context, List<Model_Post> dataModel, db_sqlite db) {
    this.context = context;
    this.dataModel = dataModel;
    this.db = db;
  }

  @Override
  protected Void doInBackground(Void... voids) {
    SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
    for (int i = 0; i < dataModel.size() ; i++) {
      Model_Post data = dataModel.get(i);
      boolean checkIdStory = db.Check_Id(Integer.parseInt(data.getId()));
      if (checkIdStory == false) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(db_sqlite.KEY_IDPOST, data.getId());
        contentValues.put(db_sqlite.KEY_TITLE, data.getTitle());
        contentValues.put(db_sqlite.KEY_IMAGE, data.getImage());
        contentValues.put(db_sqlite.KEY_link_VIDEO, data.getLinkvideo());
        contentValues.put(db_sqlite.KEY_USERFAVORITE, data.getUserfavorite());
        sqLiteDatabase.insert(db_sqlite.TBL_GETPOST, null, contentValues);
      }
    }
    return null;
  }
}
