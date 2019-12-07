package com.ns.aco.sp.extent;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MyFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    Fragment[]  _fragmentList;
    String[] _pageTitleList;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Fragment[] fragmentList, @NonNull String[] pageTitleList) {
        super(fragmentManager);
        _fragmentList = fragmentList;
        _pageTitleList = pageTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < _pageTitleList.length){
            return _fragmentList[position];
        }else{
            return  null;
        }
    }

    @Override
    public int getCount() {
        return _pageTitleList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position < _pageTitleList.length){
            return _pageTitleList[position];
        }else{
            return "ページ" + (position + 1);
        }
    }
}
