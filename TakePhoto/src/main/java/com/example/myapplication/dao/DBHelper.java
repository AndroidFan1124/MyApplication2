package com.example.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class DBHelper extends SQLiteOpenHelper {
	private Context mContext;
	private static DBHelper instance;

	public static final synchronized DBHelper getInstance(Context context,
			String name, CursorFactory factory, int version) {
		if (instance == null) {
			instance = new DBHelper(context, name, factory, version);
		}
		return instance;

	}

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		executeDB(db, "school.sql");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if (newVersion <= oldVersion) {
			return;
		}
		Configuration.oldVersion = oldVersion;
		int changeCnt = newVersion - oldVersion;
		for (int i = 0; i < changeCnt; i++) {
			// 依次执行updatei_i+1文件 由1更新到2【1-2】，2更新到3【2-3】
			String schemaName = "update" + (oldVersion + i) + "_"
					+ (oldVersion + i + 1) + ".sql";
			executeDB(db, schemaName);
		}
	}

	private void executeDB(SQLiteDatabase db, String schemaName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(mContext.getAssets()
					.open(Configuration.DB_PATH + "/" + schemaName)));
			String line;
			String buffer = "";
			while ((line = br.readLine()) != null) {
				buffer += line;
				if (line.trim().endsWith(":")) {
					db.execSQL(buffer.replace(";", ""));
					buffer = "";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {

			}
		}
	}

}
