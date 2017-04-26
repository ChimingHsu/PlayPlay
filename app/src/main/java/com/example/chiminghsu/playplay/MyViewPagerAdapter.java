package com.example.chiminghsu.playplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
        switch(position){
            case 0:
                String[] myFavCourts = {"竹南運運動公園","頭份蟠桃公園","下興里新興國小"};
                fragment = new FragmentForViewPager();
                Bundle bundle1 = new Bundle();
                bundle1.putCharSequenceArray(FragmentForViewPager.KEY_WORD,myFavCourts);
                fragment.setArguments(bundle1);
                return fragment;
            case 1:
                String[] dedtFrom = {"方圓1公里","方圓5公里","方圓10公里"};
                fragment = new FragmentForViewPager();
                Bundle bundle2 = new Bundle();
                bundle2.putCharSequenceArray(FragmentForViewPager.KEY_WORD,dedtFrom);
                fragment.setArguments(bundle2);
                return fragment;
            case 2:
                fragment = new FragmentFriendList();
                return fragment;
            default:
                return new FragmentFriendList();

        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

}
