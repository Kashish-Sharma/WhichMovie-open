package app.com.moviedb.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.com.moviedb.UI.Fragments.SearchMoviesFragment;
import app.com.moviedb.UI.Fragments.TopRatedFragment;
import app.com.moviedb.UI.Fragments.UpcomingMoviesFragment;


public class PagerViewAdapter extends FragmentPagerAdapter {
    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UpcomingMoviesFragment();
            case 1:
                return new TopRatedFragment();
            case 2:
                return new SearchMoviesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
