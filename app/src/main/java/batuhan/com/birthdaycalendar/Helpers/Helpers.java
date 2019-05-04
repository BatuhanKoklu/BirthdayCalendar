package batuhan.com.birthdaycalendar.Helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.ArrayList;

import batuhan.com.birthdaycalendar.Adapters.AdapterBirthdayCardView;
import batuhan.com.birthdaycalendar.Adapters.BirthdayDAO;
import batuhan.com.birthdaycalendar.Adapters.VeritabaniYardimcisi;
import batuhan.com.birthdaycalendar.Models.BirthdayModel;

public class Helpers {

    public String getText(EditText editText){
        return editText.getText().toString();
    }

    public Drawable resToDrawable(int image,Context context){
        Drawable d = context.getResources().getDrawable(image);
        return d;
    }

    public int boolToint(boolean b){
        int n;
        if (b = true){
            n=1;
        }else{
            n=0;
        }

        return n;
    }

    public void getBirthdays(VeritabaniYardimcisi vt, AdapterBirthdayCardView adapter, RecyclerView rv, Context context){

        adapter = new AdapterBirthdayCardView(context,new BirthdayDAO().getAllBirthdays(vt));
        rv.setAdapter(adapter);

    }
}
