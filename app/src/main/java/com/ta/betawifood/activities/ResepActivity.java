package com.ta.betawifood.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ta.betawifood.R;
import com.ta.betawifood.adapter.MenuAdapter;
import com.ta.betawifood.models.ResepModel;
import com.ta.betawifood.utils.Session;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class ResepActivity extends AppCompatActivity {

    public static final String RESEP = "Data Resep";
    ArrayList<ResepModel> resepModels;
    Session session;
    RecyclerView recyclerView;
    DatabaseReference dbRefrence;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep);

        searchView = findViewById(R.id.searchresep);
        settingToolbar();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.keepSynced(true);
        dbRefrence = rootRef.child(RESEP);
        recyclerView = findViewById(R.id.rcvmenu);
        recyclerView.setLayoutManager(new GridLayoutManager(ResepActivity.this,3));
    }

    private void settingToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().setTitle("Daftar Resep Masakan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (dbRefrence!=null){
            dbRefrence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        resepModels = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            resepModels.add(ds.getValue(ResepModel.class));
                        }
                      MenuAdapter  menuAdapter = new MenuAdapter(ResepActivity.this,resepModels);
                        recyclerView.setAdapter(menuAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toasty.error(getApplicationContext(),""+databaseError.getMessage(),Toasty.LENGTH_LONG).show();
                }
            });
        }
        if (searchView!=null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    cari(newText);
                    return true;
                }
            });
        }
    }

    private void cari(String newText) {
        ArrayList<ResepModel> searchList = new ArrayList<>();
        for (ResepModel data : resepModels){
            if (data.getJudul().toLowerCase().contains(newText.toLowerCase())){
                searchList.add(data);
            }
        }
        MenuAdapter adapter = new MenuAdapter(this,searchList);
        recyclerView.setAdapter(adapter);
    }

}
