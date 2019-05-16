package batuhan.com.birthdaycalendar.Activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import batuhan.com.birthdaycalendar.Adapters.AdapterBirthdayCardView;
import batuhan.com.birthdaycalendar.Adapters.BirthdayDAO;
import batuhan.com.birthdaycalendar.Adapters.CalendarAdapter;
import batuhan.com.birthdaycalendar.Adapters.SwipeToDeleteCallback;
import batuhan.com.birthdaycalendar.Adapters.VeritabaniYardimcisi;
import batuhan.com.birthdaycalendar.Helpers.Helpers;
import batuhan.com.birthdaycalendar.Models.BirthdayModel;
import batuhan.com.birthdaycalendar.R;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private android.support.v7.widget.Toolbar toolbar;
    private AdapterBirthdayCardView adapter;
    private ArrayList<BirthdayModel> birthdayModelList;
    private RecyclerView recyclerView;
    private CalendarView calendarView;
    private TextView txtDate;
    private CoordinatorLayout coordinatorLayout;

    private TextView txtAlertDate;
    private EditText etxtBirthdayName, etxtNote;
    private Button btnAdd, btnCancel, btnStar;

    private VeritabaniYardimcisi vt;

    private Helpers h;

    boolean star;

    private Menu menu;

    private CalendarAdapter calendarAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vt = new VeritabaniYardimcisi(this);
        h = new Helpers();

        birthdayModelList = new ArrayList<BirthdayModel>();
        birthdayModelList = new BirthdayDAO().getAllBirthdays(vt);

        //calendarView = findViewById(R.id.calendarView);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        adapter = new AdapterBirthdayCardView(getApplicationContext(),birthdayModelList);




        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        h.getBirthdays(vt,adapter,recyclerView,this);


        txtDate = findViewById(R.id.txtDate);
        //Page yüklendiğinde o günün date i text e yazılsın
        String date = new SimpleDateFormat("dd/M/yyyy", Locale.getDefault()).format(new Date());
        txtDate.setText(date);

        enableSwipeToDeleteAndUndo();










        //Seçilen günü textview a yazdır
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth+"/"+month+"/"+year;
                txtDate.setText(date);

                birthdayModelList = new ArrayList<BirthdayModel>();

                birthdayModelList = new BirthdayDAO().searchBirthdayDate(vt,date);

                adapter = new AdapterBirthdayCardView(getApplicationContext(),birthdayModelList);
                recyclerView.setAdapter(adapter);

            }
        });






    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final BirthdayModel model = adapter.getData().get(position);

                adapter.removeItem(position);
                recyclerView.setAdapter(adapter);
                new BirthdayDAO().deleteBirhday(vt,model);


                Snackbar snackbar = Snackbar.make(coordinatorLayout,"Birthday was removed from the list",
                        Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        adapter.restoreItem(model,position);
                        recyclerView.scrollToPosition(position);
                        new BirthdayDAO().addBirthdayFromModel(vt,model);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        };

        ItemTouchHelper ıtemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        ıtemTouchHelper.attachToRecyclerView(recyclerView);
    }

    //Toolbar entegresi
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.menu = menu;

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        //Search Entegresi
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        updateToolbarPoint();



        return true;
    }

    public void updateToolbarPoint(){

        MenuItem txtpoint = menu.findItem(R.id.text_point);

        String count = Integer.toString(new BirthdayDAO().countBirthdays(vt));
        txtpoint.setTitle(count);
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
                btnStar = tasarim.findViewById(R.id.btnStar);

                star = false;

                AlertDialog.Builder ao = new AlertDialog.Builder(MainActivity.this);
                ao.setView(tasarim);

                final AlertDialog alertDialog = ao.create();

                txtAlertDate.setText(txtDate.getText().toString());

                alertDialog.show();

                //Add Birthday
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new BirthdayDAO().addBirthday(vt,h.getText(etxtBirthdayName),h.getText(etxtNote),txtAlertDate.getText().toString(),h.boolToint(star));

                        String birthday = etxtBirthdayName.getText().toString();

                        Toast.makeText(getApplicationContext(),birthday+" added." + " star : " + h.boolToint(star),Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

                        h.getBirthdays(vt,adapter,recyclerView,getApplicationContext());
                        updateToolbarPoint();

                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     alertDialog.dismiss();
                    }
                });


                btnStar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        star = !star;

                        if (star == true){
                            Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();
                            btnStar.setBackgroundResource(R.drawable.icon_filled_star_yellow);
                        }else{
                            Toast.makeText(getApplicationContext(),"false",Toast.LENGTH_SHORT).show();
                            btnStar.setBackgroundResource(R.drawable.icon_empty_star_yellow);
                        }

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

        //List olıştur, bir günde birden fazla dg olabilir
        /*birthdayModelList = new ArrayList<BirthdayModel>();

        birthdayModelList = new BirthdayDAO().searchBirthdayName(vt,query);

        adapter = new AdapterBirthdayCardView(getApplicationContext(),birthdayModelList);
        recyclerView.setAdapter(adapter);*/

        //searchDateAndAddRV(vt,query,adapter,recyclerView,getApplicationContext());

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        birthdayModelList = new ArrayList<BirthdayModel>();

        birthdayModelList = new BirthdayDAO().searchBirthdayName(vt,newText);

        adapter = new AdapterBirthdayCardView(getApplicationContext(),birthdayModelList);
        recyclerView.setAdapter(adapter);

        if(newText == ""){
            h.getBirthdays(vt,adapter,recyclerView,getApplicationContext());
        }

        return true;
    }


    /*public void searchDateAndAddRV(VeritabaniYardimcisi vt, String date, AdapterBirthdayCardView adapter, RecyclerView recyclerView ,Context context){

        ArrayList<BirthdayModel> list = new ArrayList<BirthdayModel>();

        list = new BirthdayDAO().searchBirthdayDate(vt,date);

        adapter = new AdapterBirthdayCardView(context,list);
        recyclerView.setAdapter(adapter);
    }*/



}
