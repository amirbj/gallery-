package com.example.gallery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
	

    private static final String LOG = Database.class.getName();
	 // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "Favorite";
 
    // Contacts table name
    private static final String TABLE_NAME = "FavoriteItem";
    private static final String TABLE_USER ="user";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String CATEGORY = "category";
    private static final String PRICE = "price";
    private static final String DESCRIP= "discription";
    private static final String IMG_PATH= "img_path";
    
    private static final String USER_NAME = "name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASS = "password";
    private static final String USER_ID = "id";

	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE = "create table "+ TABLE_NAME + "("+ KEY_ID + " integer primary key autoincrement," + CATEGORY + " text," +
		PRICE + " integer," + DESCRIP + " text,"+ IMG_PATH + " text);";
		db.execSQL(CREATE_TABLE);
		
		
		String 	CREATE_USER = "CREATE TABLE "  + TABLE_USER + "("+ USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT," + USER_EMAIL + " TEXT," + USER_PASS + " TEXT UNIQUE);";
		db.execSQL(CREATE_USER);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);  
        // Create tables again
        onCreate(db);
		
	}
  public long insertUser(User user){
	  
	  SQLiteDatabase data = this.getWritableDatabase();
	 
	  ContentValues values = new ContentValues();
	  
	  
	  values.put(USER_NAME, user.getName());
	  values.put(USER_EMAIL, user.getEmail());
	  values.put(USER_PASS, user.getPassword());
	  
	  long id = data.insert(TABLE_USER, null, values);
	  return id;
	  
  }
  
  public boolean authenticate(String username, String password) throws SQLException   
  {  
	  SQLiteDatabase db = this.getWritableDatabase();
      Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + USER_EMAIL + "=?" +" AND "+ USER_PASS + "=?" , new String[]{username,password});  
      if (mCursor != null) {             
          if(mCursor.getCount() > 0)  
          {  
              return true;  
          }  
      }  
   return false;  
  }  
  
 public long insertItems(){
	 SQLiteDatabase data = this.getWritableDatabase();
	 ContentValues values = new ContentValues();
	 SGItem items = new SGItem();
	 values.put(CATEGORY, items.getCategory());
	 values.put(DESCRIP, items.getDiscription());
	 values.put(IMG_PATH, items.getImg_path());
	 values.put(PRICE, items.getPrice());
	 long id = data.insert(TABLE_NAME, null, values);
	 return id;
 }

}  


