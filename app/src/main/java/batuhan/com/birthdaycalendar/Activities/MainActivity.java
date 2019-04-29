package batuhan.com.birthdaycalendar.Activities;

import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.SearchView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import batuhan.com.birthdaycalendar.Adapters.AdapterBirthdayCardView;
import batuhan.com.birthdaycalendar.Models.BirthdayModel;
import batuhan.com.birthdaycalendar.R;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private android.support.v7.widget.Toolbar toolbar;
    private AdapterBirthdayCardView adapter;
    private ArrayList<BirthdayModel> birthdayModelList;
    private RecyclerView recyclerView;
    private CalendarView calendarView;
    private TextView txtDate;

    private TextView txtAlertDate;
    private EditText etxtBirthdayName, etxtNote;
    private Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDate = findViewById(R.id.txtDate);
        calendarView = findViewById(R.id.calendarView);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        birthdayModelList = new ArrayList<BirthdayModel>();

        BirthdayModel model = new BirthdayModel(1,"Batuhan Köklü'nün doğum günü","Bodrumda Kutlanacak","22.07.1997",0);
        birthdayModelList.add(model);

        adapter = new AdapterBirthdayCardView(getApplicationContext(),birthdayModelList);
        recyclerView.setAdapter(adapter);

        //Page yüklendiğinde o günün date i text e yazılsın
        String date = new SimpleDateFormat("dd/M/yyyy", Locale.getDefault()).format(new Date());
        txtDate.setText(date);

        //Seçilen günü textview a yazdır
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                txtDate.setText(dayOfMonth+"/"+month+"/"+year);
            }
        });








    }

    //Toolbar entegresi
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        //Search Entegresi
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        return true;
    }


    //Toolbar tuşları
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add:

                View tasarim = getLayoutInflater().inflate(R.layout.alertdialog_design,null);

                txtAlertDate = tasarim.findViewById(R.id.txtAlertDate);
                etxtBirthdayName = tasarim.findViewById(R.id.etxtBirthdayName);
                etxtNote = tasarim.findViewById(R.id.etxtNote);
                btnAdd = tasarim.findViewById(R.id.btnAdd);
                btnCancel = tasarim.findViewById(R.id.btnCancel);

                AlertDialog.Builder ao = new AlertDialog.Builder(MainActivity.this);
                ao.setView(tasarim);

                final AlertDialog alertDialog = ao.create();

                txtAlertDate.setText(txtDate.getText().toString());

                alertDialog.show();


                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String birthday = etxtBirthdayName.getText().toString();

                        Toast.makeText(getApplicationContext(),birthday+" added.",Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     alertDialog.dismiss();
                    }
                });





                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Toolbar Search
    @Override
    public boolean onQueryTextSubmit(String query) {

        Log.e("TextSubmit",query);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        Log.e("TextChanged",newText);

        return true;
    }

    /*public BirthdayModel createModel(){

    }*/



}
