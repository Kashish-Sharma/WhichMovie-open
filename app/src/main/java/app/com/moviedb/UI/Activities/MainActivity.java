package app.com.moviedb.UI.Activities;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import app.com.moviedb.Adapters.PagerViewAdapter;
import app.com.moviedb.R;

public class MainActivity extends AppCompatActivity {

    private TextView mUpcomingLabel;
    private TextView mTopRatedLabel;
    private TextView mSearchLabel;

    private ViewPager mViewPager;
    private PagerViewAdapter mPagerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUpcomingLabel = findViewById(R.id.upcoming_movie);
        mTopRatedLabel = findViewById(R.id.top_movie);
        mSearchLabel = findViewById(R.id.search_movie);
        mViewPager = findViewById(R.id.main_pager);

        mPagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerViewAdapter);


        //Changing fragments on swipe
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Changing fragments on click
        mUpcomingLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });
        mTopRatedLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
            }
        });
        mSearchLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(2);
            }
        });

    }

    private void changeTabs(int position){

        if (position == 0){
            mUpcomingLabel.setTextColor(ContextCompat.getColor(this,R.color.white));
            mUpcomingLabel.setTextSize(24);

            mTopRatedLabel.setTextColor(ContextCompat.getColor(this,R.color.grey));
            mTopRatedLabel.setTextSize(18);

            mSearchLabel.setTextColor(ContextCompat.getColor(this,R.color.grey));
            mSearchLabel.setTextSize(18);
        } else  if (position == 1){
            mUpcomingLabel.setTextColor(ContextCompat.getColor(this,R.color.grey));
            mUpcomingLabel.setTextSize(18);

            mTopRatedLabel.setTextColor(ContextCompat.getColor(this,R.color.white));
            mTopRatedLabel.setTextSize(24);

            mSearchLabel.setTextColor(ContextCompat.getColor(this,R.color.grey));
            mSearchLabel.setTextSize(18);
        } else  if (position == 2){
            mUpcomingLabel.setTextColor(ContextCompat.getColor(this,R.color.grey));
            mUpcomingLabel.setTextSize(18);

            mTopRatedLabel.setTextColor(ContextCompat.getColor(this,R.color.grey));
            mTopRatedLabel.setTextSize(18);

            mSearchLabel.setTextColor(ContextCompat.getColor(this,R.color.white));
            mSearchLabel.setTextSize(24);
        }

    }

}
