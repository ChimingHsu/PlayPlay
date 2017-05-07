package com.example.chiminghsu.playplay;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Toolbar topToolbar;
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    TabLayout tabLayout;
    FragmentForNavigation fragmentForNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initToolBar();
        initBottomNavigationView();

    }

    private void findViews(){
        topToolbar = (Toolbar) findViewById(R.id.top_toolbar);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
//        return true;
//    }



    private void initToolBar(){
        topToolbar.inflateMenu(R.menu.menu_toolbar);
        final Intent intent = new Intent(this,com.example.chiminghsu.playplay.SetMemberInfoActivity.class);
        topToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.user_info:
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }



    private void initBottomNavigationView(){
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.opt_find_court:
                        String[] tabStrArray1 = {"我附近的球場","我最愛的球場"};
                        initFragment(tabStrArray1);
                        return true;
                    case R.id.opt_find_partner:
                        String[] tabStrArray2 = {"球場附近","我的附近","好友清單"};
                        initFragment(tabStrArray2);
                        return true;
                    case R.id.opt_notify:
                        return true;
                    case R.id.opt_more:
                        return true;
                    default:
                        return false;
                }
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.opt_find_partner);
    }

    private void initFragment(String[] datas){
        fragmentForNavigation = new FragmentForNavigation();
        Bundle bundle = new Bundle();
        bundle.putCharSequenceArray(FragmentForNavigation.TAB_TEXT,datas);
        fragmentForNavigation.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fg_container,fragmentForNavigation).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.user_info:
                //Log.d(String.valueOf(R.id.user_info),"show member info");
                Intent intent = new Intent(this,com.example.chiminghsu.playplay.SetMemberInfoActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
