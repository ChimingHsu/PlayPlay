package com.example.chiminghsu.playplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chiming Hsu on 2017/4/21.
 */

public class FragmentForNavigation extends Fragment {
    public static final String TAB_TEXT="tabText";
    private List<String> tabTextList;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public FragmentForNavigation() {
        super();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String[] tabTexts= (String[]) bundle.get(TAB_TEXT);
        tabTextList = Arrays.asList(tabTexts);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_for_bottom_navigation,container,false);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_container);
        viewPager.setAdapter(new MyViewPagerAdapter(getActivity().getSupportFragmentManager(),tabTextList));

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        for(int i =0;i<tabTextList.size();i++){
            String tabtext = tabTextList.get(i);
            tabLayout.getTabAt(i).setText(tabtext);
        }
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
