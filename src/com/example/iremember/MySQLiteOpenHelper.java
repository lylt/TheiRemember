package com.example.iremember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	private static final String DB_NAME="MyDatabase.db";
	private static final int vs=1;
	private SQLiteDatabase database;
	//init table
	public static final String TB_NAME="remember";
	public static final String CL_ID="_id";
	public static final String CL_Tittle="Tittle";
	public static final String CL_body="Body";

	public MySQLiteOpenHelper(Context context) {
		super(context, DB_NAME,null,vs);
		database=this.getWritableDatabase();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sqlCreateDB="CREATE TABLE "+TB_NAME+"("+ CL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
		CL_Tittle+" TEXT, "+CL_body+" TEXT)";
		Log.d("debug",sqlCreateDB);
		db.execSQL(sqlCreateDB);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
		 onCreate(db);
	}
	public void INSERT_RECORD(Record r){
		ContentValues mcontentValues= new ContentValues();
		mcontentValues.put(CL_Tittle, r.getTittle());
		mcontentValues.put(CL_body, r.getBody());
		database.insertOrThrow(TB_NAME, null, mcontentValues);
		
	}
	public void DELETE_Record(int id){
		database.delete(TB_NAME, CL_ID + " = " + id, null);
	}
	public void UPDATE_SinhVien(Record r, int id) {
		 ContentValues values = new ContentValues();
		 values.put(CL_Tittle, r.getTittle());
		 values.put(CL_body, r.getBody());
		 database.update(TB_NAME, values, "_id = " + id, null);
		 }
		 
		// select toan bo bang TB_NAME = "SinhVien"
		 public Cursor SELECT_ALL__RECORD() {
		 return database.query(TB_NAME, null, null, null, null, null, null);
		 }
		 
		// neu DB cua cac ban co nhieu bang cac ban co the viet them cac
		 // phuong thuc update,insert,delete,select cho cac bang nay tiep o ben duoi
		 
		 //.....
		 
		 // minh viet phuong thuc chung dung de SELECT trong CSDL DB_NAME = "MyDataBase.db"
		 // khi truyen vao 1 cau select sql voi 1 bang bat ky trong DB thi ket qua tra ra la cursor
		 
		 public Cursor SELECTSQL(String sql) {
		 return database.rawQuery(sql, null);
		 }
		 
		// phuong thuc nay de dong DB khi khong su dung
		 public void CloseBD() {
		 if (database != null && database.isOpen())
		 database.close();
		 }
		 

}
