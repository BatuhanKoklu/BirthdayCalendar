package batuhan.com.birthdaycalendar.Adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import batuhan.com.birthdaycalendar.Models.BirthdayModel;
import batuhan.com.birthdaycalendar.Models.EventObjects;
import batuhan.com.birthdaycalendar.R;

public class BirthdayDAO {

    public void addBirthday(VeritabaniYardimcisi vt, String birthdayName,String birthdayNote,String birthdayDate, int birthdayFavorite){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();

        degerler.put("birthdayName",birthdayName);
        degerler.put("birthdayNote",birthdayNote);
        degerler.put("birthdayDate",birthdayDate);
        degerler.put("birthdayFavorite",birthdayFavorite);

        dbx.insertOrThrow("tbl_birthdayModel",null,degerler);
        dbx.close();
    }

    public ArrayList<BirthdayModel> searchBirthdayName(VeritabaniYardimcisi vt, String birthdayName){
        ArrayList<BirthdayModel> birthdayModelArrayList = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM tbl_birthdayModel WHERE birthdayName like'%"+birthdayName+"%' ",null);

        while (c.moveToNext()){

            BirthdayModel model = new BirthdayModel(c.getInt(c.getColumnIndex("birthdayId"))
                    ,c.getString(c.getColumnIndex("birthdayName"))
                    ,c.getString(c.getColumnIndex("birthdayNote"))
                    ,c.getString(c.getColumnIndex("birthdayDate"))
                    ,c.getInt(c.getColumnIndex("birthdayFavorite")));

            birthdayModelArrayList.add(model);

        }

        return birthdayModelArrayList;
    }


    public ArrayList<BirthdayModel> searchBirthdayDate(VeritabaniYardimcisi vt, String birthdayDate){
        ArrayList<BirthdayModel> birthdayModelArrayList = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM tbl_birthdayModel WHERE birthdayDate ='"+birthdayDate+"'",null);

        while (c.moveToNext()){

            BirthdayModel model = new BirthdayModel(c.getInt(c.getColumnIndex("birthdayId"))
                    ,c.getString(c.getColumnIndex("birthdayName"))
                    ,c.getString(c.getColumnIndex("birthdayNote"))
                    ,c.getString(c.getColumnIndex("birthdayDate"))
                    ,c.getInt(c.getColumnIndex("birthdayFavorite")));

            birthdayModelArrayList.add(model);

        }

        return birthdayModelArrayList;
    }

    public ArrayList<BirthdayModel> getAllBirthdays(VeritabaniYardimcisi vt){
        ArrayList<BirthdayModel> birthdayModelArrayList = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM tbl_birthdayModel",null);

        while (c.moveToNext()){

            BirthdayModel model = new BirthdayModel(c.getInt(c.getColumnIndex("birthdayId"))
                    ,c.getString(c.getColumnIndex("birthdayName"))
                    ,c.getString(c.getColumnIndex("birthdayNote"))
                    ,c.getString(c.getColumnIndex("birthdayDate"))
                    ,c.getInt(c.getColumnIndex("birthdayFavorite")));

            birthdayModelArrayList.add(model);

        }

        return birthdayModelArrayList;
    }

    public void deleteBirhday(VeritabaniYardimcisi vt, BirthdayModel model){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        dbx.delete("tbl_birthdayModel","birthdayId=?",new String[]{String.valueOf(model.getBirthdayId())} );
        dbx.close();
    }

    public void addBirthdayFromModel(VeritabaniYardimcisi vt,BirthdayModel model){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();

        degerler.put("birthdayName",model.getBirthdayName());
        degerler.put("birthdayNote",model.getBirthdayNote());
        degerler.put("birthdayDate",model.getBirthdayDate());
        degerler.put("birthdayFavorite",model.getBirthdayFavorite());

        dbx.insertOrThrow("tbl_birthdayModel",null,degerler);
        dbx.close();
    }

    public void changeFavoriteStatus(VeritabaniYardimcisi vt,BirthdayModel model, int to){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();


        degerler.put("birthdayFavorite",to);

        dbx.update("tbl_birthdayModel",degerler,"birthdayId=?", new String[] {String.valueOf(model.getBirthdayId())});

    }

    public int countBirthdays(VeritabaniYardimcisi vt){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor cursor = dbx.rawQuery("SELECT * FROM tbl_birthdaymodel",null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public List<BirthdayModel> getAllFutureEvents(VeritabaniYardimcisi vt){
        Date dateToday = new Date();
        List<BirthdayModel> events = new ArrayList<>();
        String query = "SELECT * FROM tbl_birthdayModel";
        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor cursor = dbx.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(cursor.getColumnIndexOrThrow("birthdayName"));
                String message = cursor.getString(cursor.getColumnIndexOrThrow("birthdayNote"));
                String startDate = cursor.getString(cursor.getColumnIndexOrThrow("birthdayDate"));
                //convert start date to date object
                Date reminderDate = convertStringToDate(startDate);
                if(reminderDate.after(dateToday) || reminderDate.equals(dateToday)){
                    events.add(new BirthdayModel(id,name,message,startDate,1));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return events;
    }

    public Date convertStringToDate(String dateInString){
        DateFormat format = new SimpleDateFormat("dd/M/yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}
