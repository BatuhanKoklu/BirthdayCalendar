package batuhan.com.birthdaycalendar.Models;

import java.util.ArrayList;

public class DateModel {

    private ArrayList<AyModel> DateList;
    private int yil;

    public DateModel(ArrayList<AyModel> dateList, int yil) {
        DateList = dateList;
        this.yil = yil;
    }
    

    public ArrayList<AyModel> getDateList() {
        return DateList;
    }

    public void setDateList(ArrayList<AyModel> dateList) {
        DateList = dateList;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }
}
