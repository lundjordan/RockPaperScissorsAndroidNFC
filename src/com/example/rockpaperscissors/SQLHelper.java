package com.example.rockpaperscissors;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper {

	  public static final String TABLE_HIGH_SCORES = "highScores";
	  public static final String TABLE_CURRENT_HIGH_SCORES = "currentHighScore";
	  public static final String SCORE = "score";
	  
	  public static final String COLUMN_ID = "_id";

	  private static final String DATABASE_NAME = "commments.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_HIGH_SCORES + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + SCORE
	      + " text not null);";
	  
	  private static final String DATABASE_FAVORITES_CREATE = "create table "
	      + TABLE_CURRENT_HIGH_SCORES + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + SCORE
	      + " text not null);";

	  public SQLHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }
	  

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	    database.execSQL(DATABASE_FAVORITES_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(SQLHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGH_SCORES);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENT_HIGH_SCORES);
	    onCreate(db);
	  }
}
