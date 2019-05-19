package batuhan.com.birthdaycalendar.Adapters;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import batuhan.com.birthdaycalendar.Models.AyModel;
import batuhan.com.birthdaycalendar.Models.DateModel;
import batuhan.com.birthdaycalendar.Models.GunModel;

public class CalendarArrayAdapter {

    Date currentTime = Calendar.getInstance().getTime();

    public void setupArrays(){

        DateFormatSymbols dfs = new DateFormatSymbols();
        ArrayList<String> gunler = new ArrayList<String>(Arrays.asList(dfs.getWeekdays()));

        ArrayList<Integer> gunSayi = new ArrayList<Integer>();
        for (int i=1;i>32;i++){
            gunSayi.add(i);
        }

        ArrayList<String> aylar = new ArrayList<String>(Arrays.asList(dfs.getMonths()));
        ArrayList<Integer> aySayi = new ArrayList<Integer>();
        for (int i=1;i>13;i++){
            aySayi.add(i);
        }

        GunModel gunModel = new GunModel(gunler,gunSayi);
        AyModel ayModel = new AyModel(aylar,aySayi,gunModel);
        DateModel dateModel = new DateModel(ayModel,2019);


    }
}
