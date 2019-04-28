package batuhan.com.birthdaycalendar;

import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import batuhan.com.birthdaycalendar.Adapters.AdapterBirthdayCardView;
import batuhan.com.birthdaycalendar.Models.BirthdayModel;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private android.support.v7.widget.Toolbar toolbar;
    private AdapterBirthdayCardView adapter;
    private ArrayList<BirthdayModel> birthdayModelList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add:
                Toast.makeText(getApplicationContext(),"Add New Birth",Toast.LENGTH_SHORT).show();
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
