package batuhan.com.birthdaycalendar.Models;

import java.util.ArrayList;

public class AyModel {

    private ArrayList<String> ay_isim; // Ocak-Aralık
    private ArrayList<Integer> ay_sayi; // 1-12
    private GunModel gunModel;

    public AyModel(ArrayList<String> ay_isim, ArrayList<Integer> ay_sayi, GunModel gunModel) {
        this.ay_isim = ay_isim;
        this.ay_sayi = ay_sayi;
        this.gunModel = gunModel;
    }

    public ArrayList<String> getAy_isim() {
        return ay_isim;
    }

    public void setAy_isim(ArrayList<String> ay_isim) {
        this.ay_isim = ay_isim;
    }

    public ArrayList<Integer> getAy_sayi() {
        return ay_sayi;
    }

    public void setAy_sayi(ArrayList<Integer> ay_sayi) {
        this.ay_sayi = ay_sayi;
    }

    public AyModel(GunModel gunModel) {
        this.gunModel = gunModel;
    }
}
