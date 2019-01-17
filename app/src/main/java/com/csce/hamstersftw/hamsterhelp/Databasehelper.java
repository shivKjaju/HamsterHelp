package com.csce.hamstersftw.hamsterhelp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/**
 * Created by shivk on 3/11/2018.
 */

public class Databasehelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HamsterHelp.db";
    private static final String TABLE_NAME = "Information";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_Fname = "firstName";
    private static final String COLUMN_Lname = "lastName";
    private static final String COLUMN_Bday = "birthDay";
    private static final String COLUMN_Phno = "mobile";
    private static final String COLUMN_a1 = "addressLine1";
    private static final String COLUMN_a2 = "addressLine2";
    private static final String COLUMN_e = "email";
    private static final String COLUMN_p = "password";
    private static final String COLUMN_Tag = "Tag";
    private static final String COLUMN_NumberRating = "NumberRating";
    private static final String COLUMN_Rating = "Rating";
    SQLiteDatabase sqLiteDatabase;
    private static final String TABLE_CREATE = "create table Information (id integer primary key not null , " +
            "Tag text not null, FirstName text not null, lastName text not null, birthDay text not null, mobile text not null, addressLine1 text not null, addressLine2 text not null,email text not null,password text not null,NumberRating text not null,Rating text not null)";

    public Databasehelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.sqLiteDatabase = sqLiteDatabase;
    }
    public void insertInfo( Userinfo u){

        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from Information";
        //Uncomment this two line if can not find the table Tag
        //Uncomment query1 and the execution method if the TAG column is not found in the table
//       String query1 = "ALTER TABLE Information ADD COLUMN NumberRating ";
//       String query2 = "ALTER TABLE Information ADD COLUMN Rating";
       //String query3 = "ALTER TABLE Information ADD COLUMN Tag";
//       sqLiteDatabase.execSQL(query2);
//       sqLiteDatabase.execSQL(query1);
       //sqLiteDatabase.execSQL(query3);
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int count = cursor.getCount();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_Tag,u.getTag());
        values.put(COLUMN_NumberRating,"0");
        values.put(COLUMN_Rating,"0");
        values.put(COLUMN_Fname, u.getFirstName());
        values.put(COLUMN_Lname,u.getLastName());
        values.put(COLUMN_Bday,u.getBirthDay());
        values.put(COLUMN_Phno,u.getMobile());
        values.put(COLUMN_a1,u.getAddressLine1());
        values.put(COLUMN_a2,u.getAddressLine2());
        values.put(COLUMN_e,u.getEmail());
        values.put(COLUMN_p,u.getPassword());
        sqLiteDatabase.insert(TABLE_NAME, null,values);
        sqLiteDatabase.close();

    }

    public String serachPass(String email){
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select email,password from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(email)){
                    b= cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }
    public String EmailChecking(String email){
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select email from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String a,b;
        b = "false";

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a != null) {
                    if (a.equals(email)) {
                        b = "true";
                        break;
                    }
                    else {
                        b = "false";
                    }
                }
            }while(cursor.moveToNext());
        }


        return b;
    }
    public ArrayList<String> serachTag(String Tag){
        sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> TagInformation = new ArrayList<String>();
        String query = "select Tag,FirstName from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        // Userinfo TagInfo = new Userinfo();
        String a,FirstName;
        FirstName = "not found";

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a != null) {
                    if (a.equals(Tag)) {
                        FirstName = cursor.getString(1);
//                   TagInfo.setFirstName(cursor.getString(1));
//                   TagInfo.setLastName(cursor.getString(2));
//                   TagInfo.setMobile(cursor.getString(3));
//                   TagInfo.setEmail(cursor.getString(4));
                        TagInformation.add(FirstName);
                    }
                }
            }while(cursor.moveToNext());
        }
        return TagInformation;
    }
    public ArrayList<Userinfo> serachTagWithTheObject(String Tag){
        sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Userinfo> TagInformation = new ArrayList<Userinfo>();
        String query = "select Tag,FirstName,Rating,NumberRating,email from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        String a,FirstName,NumberRating;
        String Rating = "none";
        FirstName = "not found";
        String email ="none";
        NumberRating = "none";

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                Rating = cursor.getString(2);
                if(a != null) {
                    if (a.equals(Tag)) {
                        Userinfo TagInfo = new Userinfo();
                        FirstName = cursor.getString(1);
                        email = cursor.getString(4);
                        if (Rating.equals("0") ){
                            Rating = "Rating :none";
                        }
                        NumberRating = cursor.getString(3);
                        if (NumberRating.equals("0") ){
                            NumberRating = "0 Rating";
                        }
                        TagInfo.setFirstName(FirstName);
                        TagInfo.setRating(Rating);
                        TagInfo.setNumberRating(NumberRating);
                        TagInfo.setEmail(email);
                        TagInformation.add(TagInfo);

                    }
                }
            }while(cursor.moveToNext());
        }
        return TagInformation;
    }

    public ArrayList<String> PersonalInformation(String FirstName, String Tag, String email) {
        sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> PersonInformation = new ArrayList<String>();
        String query = "select FirstName,Tag,lastName,mobile,email from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String a,b,firstName,lastName,Mobile,Email;
        firstName = "not found";
        lastName = "not found";
        Mobile = "not found";
        Email = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                b = cursor.getString(1);
                Email = cursor.getString(4);
                if(a != null && b != null && Email  != null ) {
                    if (a.equals(FirstName) && b.equals(Tag) && Email.equals(email)) {

                        lastName=  cursor.getString(2);
                        Mobile = cursor.getString(3);

                        PersonInformation.add(FirstName);
                        PersonInformation.add(lastName);
                        PersonInformation.add(Email);
                        PersonInformation.add(Mobile);
                        PersonInformation.add(cursor.getString(1));
                        break;
                    }
                }
            }while(cursor.moveToNext());
        }
        return PersonInformation;
    }
    public ArrayList<String> EmailSearchPersonalInformation(String Email ) {
        sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> PersonInformationByEmail = new ArrayList<String>();
        String query = "select email,FirstName,lastName,mobile,Tag from "+TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String a,firstName,lastName,Mobile,helperTag;
        firstName = "not found";
        lastName = "not found";
        Mobile = "not found";
        helperTag = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a != null ) {
                    if (a.equals(Email) ) {
                        firstName= cursor.getString(1);
                        lastName=  cursor.getString(2);
                        Mobile = cursor.getString(3);
                        helperTag = cursor.getString(4);
                        PersonInformationByEmail.add(firstName);
                        PersonInformationByEmail.add(lastName);
                        PersonInformationByEmail.add(Email);
                        PersonInformationByEmail.add(Mobile);
                        PersonInformationByEmail.add(helperTag);
                        break;
                    }
                }
            }while(cursor.moveToNext());
        }
        return PersonInformationByEmail;
    }
    public void UpdateRating (String Email, String NewRating){
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select email,Rating,NumberRating from "+TABLE_NAME;
        String query1 ="";
        String query2= "";
        String query3="";
        String query4= "";
        double numNewRating = 0;
        double numOldRating = 0;
        double average = 0;
        double numberOfRating = 0;
        int NewNumberOfRating =0;
        String result="";
        String result2="";
        double rounded;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        String email,Rating,NumberRating ;
        if(cursor.moveToFirst()){
            do{
                email = cursor.getString(0);
                Rating = cursor.getString(1);
                NumberRating = cursor.getString(2);
                if(email != null ) {
                    if (email.equals(Email) ) {
                        if (Rating.equals("0") ){
                            NewRating ="Rating: "+ NewRating + "/5";
                            query1="UPDATE " +TABLE_NAME +" SET Rating ="+ "'"+NewRating+"'" +" WHERE email =" + "'"+Email+"'";
                            query3="UPDATE " +TABLE_NAME +" SET NumberRating ="+ "'"+"1 Rating"+"'" +" WHERE email =" + "'"+Email+"'";
                            sqLiteDatabase.execSQL(query1);
                            sqLiteDatabase.execSQL(query3);
                        }else{
                            String token[]= Rating.split("/");
                            String token1[]= token[0].split(" ");
                            String token3[]= NumberRating.split(" ");
                            numNewRating = Double.parseDouble(NewRating);
                            numOldRating = Double.parseDouble(token1[1]);
                            numberOfRating = Double.parseDouble(token3[0]);
                            average = ((numOldRating * numberOfRating) + numNewRating) / (numberOfRating + 1) ;
                            NewNumberOfRating =  (int)numberOfRating + 1;

                            BigDecimal bd = new BigDecimal(average);
                            bd = bd.round(new MathContext(2));
                            rounded = bd.doubleValue();

                            result = String.valueOf(rounded);
                            result2 =String.valueOf(NewNumberOfRating);
                            result = "Rating: "+result + "/5";
                            result2 = result2 +" Rating";
                            query2="UPDATE " +TABLE_NAME +" SET Rating ="+ "'"+result+"'" +" WHERE email =" + "'"+Email+"'";
                            query4="UPDATE " +TABLE_NAME +" SET NumberRating ="+ "'"+result2+"'" +" WHERE email =" + "'"+Email+"'";
                            sqLiteDatabase.execSQL(query2);
                            sqLiteDatabase.execSQL(query4);


                        }

                        break;
                    }
                }
            }while(cursor.moveToNext());
        }


    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        String query = "DROP TABLE IF Exists " + TABLE_NAME;
//        sqLiteDatabase.execSQL(query);
//        this.onCreate(sqLiteDatabase);
    }
}
