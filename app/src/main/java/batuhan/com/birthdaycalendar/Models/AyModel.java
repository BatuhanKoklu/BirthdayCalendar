package batuhan.com.birthdaycalendar.Models;

import java.util.ArrayList;

public class AyModel {

    private String ay_isim; // Ocak-Aralık
    private int ay_sayi; // 1-12
    private ArrayList<GunModel> gun_list;

    public AyModel() {
    }

    public AyModel(String ay_isim, int ay_sayi, ArrayList<GunModel> gun_list) {
        this.ay_isim = ay_isim;
        this.ay_sayi = ay_sayi;
        this.gun_list = gun_list;
    }

    public String getAy_isim() {
        return ay_isim;
    }

    public void setAy_isim(String ay_isim) {
        this.ay_isim = ay_isim;
    }

    public int getAy_sayi() {
        return ay_sayi;
    }

    public void setAy_sayi(int ay_sayi) {
        this.ay_sayi = ay_sayi;
    }

    public ArrayList<GunModel> getGun_list() {
        return gun_list;
    }

    public void setGun_list(ArrayList<GunModel> gun_list) {
        this.gun_list = gun_list;
    }
}
