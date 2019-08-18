package com.example.picoyplaca;

import android.net.Uri;
import android.os.Bundle;

import com.example.picoyplaca.databases.LocalDbHelper;
import com.example.picoyplaca.dummy.DummyContent;
import com.example.picoyplaca.fragments.CheckingFragment;
import com.example.picoyplaca.fragments.HistoryFragment;
import com.example.picoyplaca.models.ItemHistoryObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.picoyplaca.ui.main.SectionsPagerAdapter;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements CheckingFragment.onNewCheck, HistoryFragment.OnListFragmentInteractionListener {

    private LocalDbHelper mDBhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBhelper = new LocalDbHelper(this);

        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "C O N T R Á T E N M E :)", Snackbar.LENGTH_LONG)
                        .setAction(":)", null).show();
            }
        });

    }

    @Override
    public void onCheck(ItemHistoryObject item) {
//        HistoryFragment.refreshHistory(item);
        String tagName = "android:switcher:" + R.id.view_pager + ":" + 1; // Your pager name & tab no of Second Fragment

        //Get SecondFragment object from FirstFragment
        HistoryFragment f2 = (HistoryFragment) getSupportFragmentManager().findFragmentByTag(tagName);

        //Then call your wish method from SecondFragment to update appropriate list
        f2.refreshHistory(item);
    }

    @Override
    public void refreshHistory(ItemHistoryObject item) {

    }
}