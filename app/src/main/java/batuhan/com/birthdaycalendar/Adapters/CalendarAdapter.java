package batuhan.com.birthdaycalendar.Adapters;

import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import batuhan.com.birthdaycalendar.R;

public class CalendarAdapter extends ArrayAdapter<Date> {
    // for view inflation
    private LayoutInflater inflater;

    public CalendarAdapter(Context context, ArrayList<Date> days) {
        super(context, R.layout.calendar_layout, days);
        //this.eventDays = eventDays;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // day in question
        Calendar calendar = Calendar.getInstance();
        Date date = getItem(position);
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // today
        Date today = new Date();
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(today);

        // inflate item if it does not exist yet
        if (view == null)
            view = inflater.inflate(R.layout.calendar_layout, parent, false);
        // clear styling
        ((TextView) view).setTypeface(null, Typeface.NORMAL);
        ((TextView) view).setTextColor(Color.BLACK);

        if (month != calendarToday.get(Calendar.MONTH) || year != calendarToday.get(Calendar.YEAR)) {
            // if this day is outside current month, grey it out
            ((TextView) view).setTextColor(Color.parseColor("#E0E0E0"));
        } else if (day == calendarToday.get(Calendar.DATE)) {
            // if it is today, set it to blue/bold
            ((TextView) view).setTextColor(Color.WHITE);
            ((TextView) view).setGravity(Gravity.CENTER);
            view.setBackgroundResource(R.drawable.add_button_orange);
        }

        // set text
        ((TextView) view).setText(String.valueOf(calendar.get(Calendar.DATE)));

        return view;
    }

    public class CalendarV extends LinearLayout {

        LinearLayout header;
        Button btnToday;
        Button btnPrev;
        Button btnNext;

        TextView txtDateDay;
        TextView txtDisplayDate;
        TextView txtDateYear;

        GridView gridView;


        public CalendarV(Context context, AttributeSet attrs) {
            super(context, attrs);
            initControl(context, attrs);
        }

        private void assignUiElements() {
            // layout is inflated, assign local variables to components
            header = findViewById(R.id.calendar_header);
            btnPrev = findViewById(R.id.btnPrev);
            btnNext = findViewById(R.id.btnNext);
            txtDateDay = findViewById(R.id.txtDateDay);
            txtDateYear = findViewById(R.id.txtDateYear);
            txtDisplayDate = findViewById(R.id.txtDisplayDate);
            btnToday = findViewById(R.id.date_display_today);
            gridView = findViewById(R.id.gridView);

        }

        /**
         * Load control xml layout
         */
        private void initControl(Context context, AttributeSet attrs) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.activity_main, this);
            assignUiElements();
        }

        public void updateCalendar() {
            ArrayList<Date> cells = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();

            // determine the cell for current month's beginning
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 2;

            // move calendar backwards to the beginning of the week
            calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

            // fill cells
            while (cells.size() < 30) {
                cells.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            // update grid
            gridView.setAdapter(new CalendarAdapter(getContext(), cells));

            // update title
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE,d MMM,yyyy");
            String[] dateToday = sdf.format(Calendar.getInstance().getTime()).split(",");
            txtDateDay.setText(dateToday[0]);
            txtDisplayDate.setText(dateToday[1]);
            txtDateYear.setText(dateToday[2]);
        }


    }


}
