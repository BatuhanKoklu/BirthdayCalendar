package batuhan.com.birthdaycalendar.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VeritabaniYardimcisi extends SQLiteOpenHelper {


    public VeritabaniYardimcisi(Context context) {
        super(context, "BirthdayCalendarDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_birthdayModel (\n" +
                "\t birthdayId \tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t birthdayName \tTEXT,\n" +
                "\t birthdayNote \tTEXT,\n" +
                "\t birthdayDate \tTEXT,\n" +
                "\t birthdayFavorite \tINTEGER\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_birthdayModel");
        onCreate(db);
    }
}
