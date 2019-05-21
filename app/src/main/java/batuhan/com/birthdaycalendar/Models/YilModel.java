package batuhan.com.birthdaycalendar.Models;

import java.util.ArrayList;

public class YilModel {

    private int yil;
    private ArrayList<AyModel> ay_list;

    public YilModel(int yil, ArrayList<AyModel> ay_list) {
        this.yil = yil;
        this.ay_list = ay_list;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }

    public ArrayList<AyModel> getAy_list() {
        return ay_list;
    }

    public void setAy_list(ArrayList<AyModel> ay_list) {
        this.ay_list = ay_list;
    }
}
