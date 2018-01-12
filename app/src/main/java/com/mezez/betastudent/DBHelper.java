package com.mezez.betastudent;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Developer on 25-May-17.
 */

public class DBHelper  extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static int      DATABASE_VERSION   = 1;
    private static String   DATABASE_NAME      = "BetaStudent.db";

    private static final String TABLE_NAME = "assignments";
    private static final String COL0 = "ID";
    private static final String COL1 = "TITLE";
    private static final String COL2 = "DESCRIPTION";
    private static final String COL3 = "DATE";
    private static final String COL4 = "TIME";
    private static final String COL5 = "RQS";

    private static final String PAST_QUESTIONS_TABLE_NAME = "past_questions";
    private static final String PAST_QUESTIONS_COL0 = "ID";
    private static final String PAST_QUESTIONS_COL1 = "TITLE";
    private static final String PAST_QUESTIONS_COL2 = "DESCRIPTION";
    private static final String PAST_QUESTIONS_COL3 = "IMAGENAME";
    private static final String PAST_QUESTIONS_COL4 = "PATH";

    private static final String BOOKMARKS_TABLE_NAME = "bookmarks";
    private static final String BOOKMARKS_COL0 = "ID";
    private static final String BOOKMARKS_COL1 = "TITLE";
    private static final String BOOKMARKS_COL2 = "DESCRIPTION";
    private static final String BOOKMARKS_COL3 = "URL";

    private static final String LECTURES_TABLE_NAME = "lectures";
    private static final String LECTURES_COL0 = "ID";
    private static final String LECTURES_COL1 = "DAY";
    private static final String LECTURES_COL2 = "COURSE";
    private static final String LECTURES_COL3 = "START_TIME";
    private static final String LECTURES_COL4 = "END_TIME";
    private static final String LECTURES_COL5 = "VENUE";

    private static final String COURSES_TABLE_NAME = "courses";
    private static final String COURSES_COL0 = "ID";
    private static final String COURSES_COL1 = "COURSE_TITLE";
    private static final String COURSES_COL2 = "COURSE_CODE";
    private static final String COURSES_COL3 = "LECTURER";
    private static final String COURSES_COL4 = "BOOKS";
    private static final String COURSES_COL5 = "OTHER_INFO";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " TEXT, " + COL2 +" TEXT, " + COL3 +" TEXT, " + COL4 +" TEXT, " + COL5 +" TEXT)";
        String createPastQuestionsTable = "CREATE TABLE IF NOT EXISTS " + PAST_QUESTIONS_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + PAST_QUESTIONS_COL1 + " TEXT, " + PAST_QUESTIONS_COL2 +" TEXT, " + PAST_QUESTIONS_COL3 +" TEXT, " + PAST_QUESTIONS_COL4 +" TEXT)";
        String createBookmarksTable = "CREATE TABLE IF NOT EXISTS " + BOOKMARKS_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + BOOKMARKS_COL1 + " TEXT, " + BOOKMARKS_COL2 +" TEXT, " + BOOKMARKS_COL3 +" TEXT)";
        String createLecturesTable = "CREATE TABLE IF NOT EXISTS " + LECTURES_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + LECTURES_COL1 + " TEXT, " + LECTURES_COL2 +" TEXT, " + LECTURES_COL3 +" TEXT, " + LECTURES_COL4 +" TEXT, " + LECTURES_COL5 +" TEXT)";
        String createCoursesTable = "CREATE TABLE IF NOT EXISTS " + COURSES_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COURSES_COL1 + " TEXT, " + COURSES_COL2 +" TEXT, " + COURSES_COL3 +" TEXT, " + COURSES_COL4 +" TEXT, " + COURSES_COL5 +" TEXT)";


        db.execSQL(createBookmarksTable);
        db.execSQL(createLecturesTable);
        db.execSQL(createPastQuestionsTable);
        db.execSQL(createCoursesTable);
        db.execSQL(createTable);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion){
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        db.execSQL("DROP IF TABLE EXISTS " + PAST_QUESTIONS_TABLE_NAME);
        db.execSQL("DROP IF TABLE EXISTS " + BOOKMARKS_TABLE_NAME);
        db.execSQL("DROP IF TABLE EXISTS " + LECTURES_TABLE_NAME);
        db.execSQL("DROP IF TABLE EXISTS " + COURSES_TABLE_NAME);
            onCreate(db);

    }

    public boolean addAssignment(String title, String description, String date, String time, int RQS ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, title);
        contentValues.put(COL2, description);
        contentValues.put(COL3, date);
        contentValues.put(COL4, time);
        contentValues.put(COL5, RQS);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            //db.close();
            return false;
        }else{
           // db.close();
            return true;
        }
    }

    public boolean addLecture(String day, String course, String startTime, String endTime, String venue ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LECTURES_COL1, day);
        contentValues.put(LECTURES_COL2, course);
        contentValues.put(LECTURES_COL3, startTime);
        contentValues.put(LECTURES_COL4, endTime);
        contentValues.put(LECTURES_COL5, venue);

        long result = db.insert(LECTURES_TABLE_NAME, null, contentValues);

        if(result == -1){
            //db.close();
            return false;
        }else{
            // db.close();
            return true;
        }
    }

    public boolean addCourse(String courseTitle, String courseCode, String lecturer, String books, String otherInfo ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSES_COL1, courseTitle);
        contentValues.put(COURSES_COL2, courseCode);
        contentValues.put(COURSES_COL3, lecturer);
        contentValues.put(COURSES_COL4, books);
        contentValues.put(COURSES_COL5, otherInfo);

        long result = db.insert(COURSES_TABLE_NAME, null, contentValues);

        if(result == -1){
            //db.close();
            return false;
        }else{
            // db.close();
            return true;
        }
    }

    public boolean addPastQuestion(String title, String description, String imageName, String imagePath){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PAST_QUESTIONS_COL1, title);
        contentValues.put(PAST_QUESTIONS_COL2, description);
        contentValues.put(PAST_QUESTIONS_COL3, imageName);
        contentValues.put(PAST_QUESTIONS_COL4, imagePath);

        long result = db.insert(PAST_QUESTIONS_TABLE_NAME, null, contentValues);

        if(result == -1){
            //db.close();
            return false;
        }else{
            // db.close();
            return true;
        }
    }

    public boolean addBookmark(String title, String description, String url){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOKMARKS_COL1, title);
        contentValues.put(BOOKMARKS_COL2, description);
        contentValues.put(BOOKMARKS_COL3, url);

        long result = db.insert(BOOKMARKS_TABLE_NAME, null, contentValues);

        if(result == -1){
            //db.close();
            return false;
        }else{
            // db.close();
            return true;
        }
    }

    public boolean updateAssignment(String new_title, String new_description, String new_time, String new_date, int new_RQS, long id){
        SQLiteDatabase db  = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 + " = '" + new_title +"', " + COL2 + " = '" + new_description+"', " + COL3 + " = '" + new_time + "', " + COL4 + " = '" + new_date+ "', " + COL5 + " = '" + new_RQS +"' WHERE ID == " + id ;
        db.execSQL(query);
        return true;
    }

    public boolean updateLecture(String newDay, String newCourse, String newStartTime, String newEndTime, int newVenue, long id){
        SQLiteDatabase db  = this.getWritableDatabase();
        String query = "UPDATE " + LECTURES_TABLE_NAME + " SET " + LECTURES_COL1 + " = '" + newDay +"', " + LECTURES_COL2 + " = '" + newCourse+"', " + LECTURES_COL3 + " = '" + newStartTime + "', " + LECTURES_COL4 + " = '" + newEndTime+ "', " + LECTURES_COL5 + " = '" + newVenue +"' WHERE ID == " + id ;
        db.execSQL(query);
        return true;
    }

    public boolean updateCourse(String newCourseTitle, String newCourseCode, String newLecturer, String newBooks, int newOtherInfo, long id){
        SQLiteDatabase db  = this.getWritableDatabase();
        String query = "UPDATE " + COURSES_TABLE_NAME + " SET " + COURSES_COL1 + " = '" + newCourseTitle +"', " + COURSES_COL2 + " = '" + newCourseCode +"', " + COURSES_COL3 + " = '" + newLecturer + "', " + COURSES_COL4 + " = '" + newBooks+ "', " + COURSES_COL5 + " = '" + newOtherInfo +"' WHERE ID == " + id ;
        db.execSQL(query);
        return true;
    }


    //COME BACK TO THIS LATER TO FIGURE OUT UPDATING THE IMAGE DETAILS, YAY OR NAY
    public boolean updatePastQuestion(String new_title, String new_description, long id){
        SQLiteDatabase db  = this.getWritableDatabase();
        String query = "UPDATE " + PAST_QUESTIONS_TABLE_NAME + " SET " + PAST_QUESTIONS_COL1 + " = '" + new_title +"', " + PAST_QUESTIONS_COL2 + " = '" + new_description +"' WHERE ID == " + id ;
        db.execSQL(query);
        return true;
    }

    public boolean updateBookmark(String new_title, String new_description, String new_url, long id){
        SQLiteDatabase db  = this.getWritableDatabase();
        String query = "UPDATE " + BOOKMARKS_TABLE_NAME + " SET " + BOOKMARKS_COL1 + " = '" + new_title +"', " + BOOKMARKS_COL2 + " = '" + new_description +"', " + BOOKMARKS_COL3 + " = '" + new_url +"' WHERE ID == " + id ;
        db.execSQL(query);
        return true;
    }

    public boolean deleteAssignment(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE ID == " + id;
        db.execSQL(query);
        return true;
    }

    public boolean deleteLecture(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + LECTURES_TABLE_NAME + " WHERE ID == " + id;
        db.execSQL(query);
        return true;
    }

    public boolean deleteCourse(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + COURSES_TABLE_NAME + " WHERE ID == " + id;
        db.execSQL(query);
        return true;
    }

    public boolean deletePastQuestion(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + PAST_QUESTIONS_TABLE_NAME + " WHERE ID == " + id;
        db.execSQL(query);
        return true;

        //remember to remove the images as well
    }

    public boolean deleteBookmark(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + BOOKMARKS_TABLE_NAME + " WHERE ID == " + id;
        db.execSQL(query);
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
       // db.close();
        return data;
    }

    public Cursor getLectureData(String day){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + LECTURES_TABLE_NAME + " WHERE " + LECTURES_COL1 + " = '" + day +"'";
        Cursor data = db.rawQuery(query, null);
        // db.close();
        return data;
    }

    public Cursor getCourseData(String day){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        // db.close();
        return data;
    }

    public Cursor getAllLectureData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + LECTURES_TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        // db.close();
        return data;
    }

    public Cursor getPastQuestionData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PAST_QUESTIONS_TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        // db.close();
        return data;
    }

    public Cursor getBookmarkData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + BOOKMARKS_TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        // db.close();
        return data;
    }

    //the position argument is the postion of the list item that is clicked
    //is is automatically retrieved by the list item click listener
    public long getItemIdByPosition(int position) {

        long itemID = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from " + TABLE_NAME,
                null);
        int i = data.getColumnIndex(COL0);
        if (data.moveToPosition(position)) {

                itemID = Integer.parseInt(data.getString(i));
        }
        data.close();
        return itemID;
    }

    public long getLectureItemIdByPosition(int position,String day) {

        long itemID = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from " + LECTURES_TABLE_NAME + " WHERE " + LECTURES_COL1 + " = '" + day +"'",
                null);
        int i = data.getColumnIndex(LECTURES_COL0);
        if (data.moveToPosition(position)) {

            itemID = Integer.parseInt(data.getString(i));
        }
        data.close();
        return itemID;
    }

    public long getCourseItemIdByPosition(int position) {

        long itemID = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from " + COURSES_TABLE_NAME,
                null);
        int i = data.getColumnIndex(COURSES_COL0);
        if (data.moveToPosition(position)) {

            itemID = Integer.parseInt(data.getString(i));
        }
        data.close();
        return itemID;
    }

    public long getPastQuestionItemIdByPosition(int position) {

        long itemID = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from " + PAST_QUESTIONS_TABLE_NAME,
                null);
        int i = data.getColumnIndex(PAST_QUESTIONS_COL0);
        if (data.moveToPosition(position)) {

            itemID = Integer.parseInt(data.getString(i));
        }
        data.close();
        return itemID;
    }

    public long getBookmarkItemIdByPosition(int position) {

        long itemID = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from " + BOOKMARKS_TABLE_NAME,
                null);
        int i = data.getColumnIndex(BOOKMARKS_COL0);
        if (data.moveToPosition(position)) {

            itemID = Integer.parseInt(data.getString(i));
        }
        data.close();
        return itemID;
    }

    public Cursor getItem(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL0 + " == " + id ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getLectureItem(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + LECTURES_TABLE_NAME + " WHERE " + LECTURES_COL0 + " == " + id ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getCourseItem(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + COURSES_TABLE_NAME + " WHERE " + COURSES_COL0 + " == " + id ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPastQuestionItem(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PAST_QUESTIONS_TABLE_NAME + " WHERE " + PAST_QUESTIONS_COL0 + " == " + id ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getBookmarksItem(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + BOOKMARKS_TABLE_NAME + " WHERE " + BOOKMARKS_COL0 + " == " + id ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
