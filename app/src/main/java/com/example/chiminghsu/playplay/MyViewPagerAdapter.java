package com.example.chiminghsu.playplay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Chiming Hsu on 2017/4/20.
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private List<String> list;


    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyViewPagerAdapter(FragmentManager fm,List<String> datas) {
        super(fm);
        fragmentManager = fm;
        list = datas;
    }

    @Override
    public Fragment getItem(int position) {
        fragment = new FragmentForvViewPager();
        Bundle bundle = new Bundle();
        bundle.putString(FragmentForvViewPager.KEY_WORD,list.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
