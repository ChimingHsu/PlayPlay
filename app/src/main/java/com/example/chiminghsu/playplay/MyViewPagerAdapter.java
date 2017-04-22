package com.example.chiminghsu.playplay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        fragment = new InnerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(InnerFragment.KEY_WORD,list.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public static class  InnerFragment extends Fragment{
        public static final String KEY_WORD = "somewords";

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Bundle bundle = this.getArguments();
            View rootView = inflater.inflate(R.layout.fragment_viewpager_item_find_partner,container,false);
            ((TextView)rootView.findViewById(R.id.tv_for_test)).setText((String)bundle.get(KEY_WORD));
            return rootView;
        }

        @Override
        public void onPause() {
            super.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onDetach() {
            super.onDetach();
        }
    }


}
