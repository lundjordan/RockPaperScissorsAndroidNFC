package com.example.rockpaperscissors;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class HighScoreTracker {
//	// Database fields
//	private SQLiteDatabase database;
//	private SQLHelper dbHelper;
//	private String[] allColumns = { SQLHelper.COLUMN_ID,
//			SQLHelper.SCORE };
//
//	public HighScoreTracker(Context context) {
//		dbHelper = new SQLHelper(context);
//	}
//
//	public void open() throws SQLException {
//		database = dbHelper.getWritableDatabase();
//	}
//
//	public void close() {
//		dbHelper.close();
//	}
//
//	public int createComment(int score) {
//		ContentValues values = new ContentValues();
//		values.put(SQLHelper.Score, score);
//		long insertId = database.insert(SQLHelper.TABLE_HIGH_SCORES, null,
//				values);
//		Cursor cursor = database.query(SQLHelper.TABLE_HIGH_SCORES,
//				allColumns, SQLHelper.COLUMN_ID + " = " + insertId, null,
//				null, null, null);
//		cursor.moveToFirst();
//		Comment newComment = cursorToComment(cursor);
//		cursor.close();
//		return newComment;
//	}
//
//	public void deleteComment(Comment comment) {
//		long id = comment.getId();
//		System.out.println("Comment deleted with id: " + id);
//		database.delete(SQLHelper.TABLE_HIGH_SCORES, SQLHelper.COLUMN_ID
//				+ " = " + id, null);
//	}
//
//	public List<Comment> getAllComments() {
//		List<Comment> comments = new ArrayList<Comment>();
//
//		Cursor cursor = database.query(SQLHelper.TABLE_HIGH_SCORES,
//				allColumns, null, null, null, null, null);
//
//		cursor.moveToFirst();
//		while (!cursor.isAfterLast()) {
//			Comment comment = cursorToComment(cursor);
//			comments.add(comment);
//			cursor.moveToNext();
//		}
//		// Make sure to close the cursor
//		cursor.close();
//		return comments;
//	}
//
//
//
//	private Comment cursorToComment(Cursor cursor) {
//		Comment comment = new Comment();
//		comment.setId(cursor.getLong(0));
//		comment.setComment(cursor.getString(1));
//		return comment;
//	}

}
