package com.example.picoyplaca.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.picoyplaca.R;
import com.example.picoyplaca.fragments.CheckingFragment;
import com.example.picoyplaca.fragments.HistoryFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_check, R.string.tab_history};
    private final Context mContext;
    private static final int CHECK_FRAGMENT = 0;
    private static final int HISTORY_FRAGMENT = 1;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case CHECK_FRAGMENT:
                return CheckingFragment.newInstance("","");
            case HISTORY_FRAGMENT:
                return HistoryFragment.newInstance(1);
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}