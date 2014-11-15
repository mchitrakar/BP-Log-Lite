package com.example.dailybp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataController extends SQLiteOpenHelper {

	private static final String LOGCAT = "DailyBP";
	private static DataController instance;
	
	Context mycontext;
	SQLiteDatabase mydb;

	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_BPDATA_TABLE = "CREATE TABLE tBPDATA ( "
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "kDIASTOLIC INTEGER," + "kSYSTOLIC INTEGER,"
				+ "kHEARTRATE INTEGER, " + "kDATE TEXT )";
		// create books table
		db.execSQL(CREATE_BPDATA_TABLE);
		Log.d(LOGCAT, "Database Created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    db.execSQL("DROP TABLE IF EXISTS bp_data");
        this.onCreate(db);
	}

    public static synchronized DataController getDBController(Context context)
    {
        if (instance == null)
            instance = new DataController(context);

        return instance;
    }
	
	public DataController(Context applicationcontext) {
		super(applicationcontext, "bp_data.db", null, 1);
		Log.d(LOGCAT, "Created");
		mycontext  = applicationcontext;
	}

	public void insertData(TableBP data ) {
//      Toast.makeText(mycontext, 
//					    "DController", Toast.LENGTH_LONG).show();		
		SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kDIASTOLIC", data.diastolic); 
        values.put("kSYSTOLIC", data.systolic); 
        values.put("kHEARTRATE", data.heart_rate); 
        values.put("kDATE", data.time); 
        db.insert("tBPDATA", 
                null, //nullColumnHack
                values); 
        db.close(); 
	}
	
    public List<TableBP> getAllData() {
        List<TableBP> bplist = new LinkedList<TableBP>();
 
        // 1. build the query
        String query = "SELECT  * FROM " + "tBPDATA";
 
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build book and add it to list
        TableBP bpdata = null;
        if (cursor.moveToFirst()) {
            do {
                bpdata = new TableBP();
                bpdata.id = (Integer.parseInt(cursor.getString(0)));
                bpdata.diastolic = Integer.parseInt(cursor.getString(1));
                bpdata.systolic = Integer.parseInt(cursor.getString(2));
                bpdata.heart_rate = Integer.parseInt(cursor.getString(3));
                
                // Add book to books
                bplist.add(bpdata);
            } while (cursor.moveToNext());
        }
 
        Log.d("getAllBooks()", bplist.toString());
        cursor.close();
        // return books
        return bplist;
    }
	
    public void clearTable(){
        SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL("delete from tBPDATA");
    	db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE name='tBPDATA'");
    }
    
    public int getLastId(){
    	int lastid;
    	SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM tBPDATA ORDER BY id DESC LIMIT 1;", null);
        cursor.moveToFirst();
        lastid = cursor.getInt(0);
        cursor.close();
        return lastid;
    }
    
    public TableBP getTable(int id){
    	SQLiteDatabase db = this.getWritableDatabase();
    	String query = "SELECT * FROM tBPDATA WHERE rowid="+id+";";
    	Cursor cursor = db.rawQuery(query,null);
    	TableBP bpdata = new TableBP();
    	cursor.moveToFirst();
    	 bpdata.id = cursor.getInt(0);
         bpdata.diastolic = cursor.getInt(1);
         bpdata.systolic = cursor.getInt(2);
         bpdata.heart_rate = cursor.getInt(3);
         cursor.close();
        return bpdata;
    }
    
    public void deleteRow(int id){
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete("tBPDATA", "id = "+id, null);
    }
    
    public ArrayList<TableBP> readBPList() 
    {
    	ArrayList<TableBP> list = new ArrayList<TableBP>();
    	TableBP bp;
    	SQLiteDatabase db = this.getWritableDatabase();
    	Cursor  cursor = db.rawQuery("select * from tBPDATA",null);
    	if (cursor .moveToFirst()) {
            while (cursor.isAfterLast() == false) {
            	bp = new TableBP();
            	bp.time = cursor.getString(4);//Integer.toString(cursor.getInt(4));
            	bp.systolic = (cursor.getInt(2));
            	bp.diastolic =(cursor.getInt(1));
            	bp.heart_rate = (cursor.getInt(3));
            	bp.id = (cursor.getInt(0));
                list.add(bp);
                cursor.moveToNext();
            }
        }
    	return list;
    }

	public boolean isEmpty() {
		SQLiteDatabase db = this.getWritableDatabase();
		String cnt = "SELECT count(*) FROM tBPDATA";
		Cursor cursor = db.rawQuery(cnt, null);
		cursor.moveToFirst();
		return (cursor.getInt(0) <= 0);
	}
	
	public int howMany() {
		SQLiteDatabase db = this.getWritableDatabase();
		String cnt = "SELECT count(*) FROM tBPDATA";
		Cursor cursor = db.rawQuery(cnt, null);
		//int c = cursor.getCount();
		int count = 0;
		if(null != cursor){
		    if(cursor.getCount() > 0){
		      cursor.moveToFirst();    
		      count = cursor.getInt(0);
		    }
		    cursor.close();
		}
		return count;
	}

}
