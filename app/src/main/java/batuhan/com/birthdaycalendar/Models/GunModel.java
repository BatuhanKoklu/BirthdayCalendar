package batuhan.com.birthdaycalendar.Models;

import java.util.ArrayList;

public class GunModel {

    private ArrayList<String> gun_isim; //pazartesi...pazar
    private ArrayList<Integer> gun_sayi; // 1-31

    public GunModel(ArrayList<String> gun_isim, ArrayList<Integer> gun_sayi) {
        this.gun_isim = gun_isim;
        this.gun_sayi = gun_sayi;
    }

    public ArrayList<String> getGun_isim() {
        return gun_isim;
    }

    public void setGun_isim(ArrayList<String> gun_isim) {
        this.gun_isim = gun_isim;
    }

    public ArrayList<Integer> getGun_sayi() {
        return gun_sayi;
    }

    public void setGun_sayi(ArrayList<Integer> gun_sayi) {
        this.gun_sayi = gun_sayi;
    }
}
