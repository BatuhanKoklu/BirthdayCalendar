package batuhan.com.birthdaycalendar.Adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import batuhan.com.birthdaycalendar.R;

public class CalendarV extends LinearLayout{

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
    private void initControl(Context context, AttributeSet attrs)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_main, this);
        assignUiElements();
    }
}
