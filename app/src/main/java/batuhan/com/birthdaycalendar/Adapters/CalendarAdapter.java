package batuhan.com.birthdaycalendar.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import batuhan.com.birthdaycalendar.Models.GunModel;
import batuhan.com.birthdaycalendar.R;

public class CalendarAdapter extends BaseAdapter {

    private ArrayList<GunModel> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
    Context context;

    public CalendarAdapter(Context context , ArrayList<GunModel> list) {
        this.list = list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View child = convertView;
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Date today = new Date();
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(today);

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        String insertDate = dateFormat.format(today);
        String[] items1 = insertDate.split("/");
        String d1=items1[0];
        String m1=items1[1];
        String y1=items1[2];
        int d = Integer.parseInt(d1); //Gün
        int m = Integer.parseInt(m1); //Ay
        int y = Integer.parseInt(y1); //Yıl


        if (child == null) {
            child = inflater.inflate(R.layout.single_cell_layout,parent,false);
            holder = new ViewHolder();
            holder.txtDay = (TextView) child.findViewById(R.id.txtDayNumber); // fname is the reference to a textview
            holder.cellConsLayout = (ConstraintLayout) child.findViewById(R.id.cellConsLayout);

            child.setTag(holder);
        }else{
            holder = (ViewHolder) child.getTag();
        }

        final GunModel model = list.get(position);

        //Görünmez yapıldı
        if(model.getGun_sayi() == 0){
            holder.txtDay.setText("");
            holder.cellConsLayout.setBackgroundResource(R.color.colorPrimary);
        }else if(model.getGun_sayi() == d){
            //Change current Date's shape
            holder.txtDay.setText(String.valueOf(model.getGun_sayi()));
            holder.cellConsLayout.setBackgroundResource(R.drawable.today_cell_shape);
            holder.txtDay.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }
        else{
            holder.txtDay.setText(String.valueOf(model.getGun_sayi()));
        }





        return child;

    }

    private static class ViewHolder {
        TextView txtDay;
        ConstraintLayout cellConsLayout;
    }
}
