package batuhan.com.birthdaycalendar.Models;

import java.util.ArrayList;

public class GunModel {

    private String gun_isim; //pazartesi...pazar
    private int gun_sayi; // 1-31

    public GunModel() {
    }

    public GunModel(String gun_isim, int gun_sayi) {
        this.gun_isim = gun_isim;
        this.gun_sayi = gun_sayi;
    }

    public String getGun_isim() {
        return gun_isim;
    }

    public void setGun_isim(String gun_isim) {
        this.gun_isim = gun_isim;
    }

    public int getGun_sayi() {
        return gun_sayi;
    }

    public void setGun_sayi(int gun_sayi) {
        this.gun_sayi = gun_sayi;
    }
}
