package dinamo.thugbird;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import dinamo.thugbird.elements.Bird;

public class BirdChooserActivity  extends FragmentActivity {

    private static final int NUM_PAGES = 5;
    private static final int DEFAULT_THUG = 2;

    private ViewPager mPager;

    private PagerAdapter mPagerAdapter;
    private SharedPreferences settings;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bird_chooser);

        Typeface font = Typeface.createFromAsset(getAssets(), getString(R.string.thugFont));
        TextView txtTitle = (TextView) findViewById(R.id.chooseYour);
        txtTitle.setTypeface(font);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }
        });

        settings = getSharedPreferences(getString(R.string.PrefsName), 0);
        mPager.setCurrentItem(settings.getInt(getString(R.string.thugColor), DEFAULT_THUG));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.bird_chooser_menu, menu);

        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);
        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() < mPagerAdapter.getCount());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_previous:
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);

                return true;

            case R.id.action_save:
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt(getString(R.string.thugColor), mPager.getCurrentItem());
                editor.apply();
                Toast.makeText(BirdChooserActivity.this, "Thug "+ Bird.getThugName(mPager.getCurrentItem()),Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_next:
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
        }


        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);
        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() < mPagerAdapter.getCount());

        return super.onOptionsItemSelected(item);
    }



    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}