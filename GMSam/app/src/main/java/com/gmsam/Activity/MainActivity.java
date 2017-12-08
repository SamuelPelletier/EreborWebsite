package com.gmsam.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gmsam.R;
import com.gmsam.fragments.Fragment1;
import com.gmsam.fragments.Fragment2;
import com.gmsam.fragments.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int[] tabIcons = {
            R.drawable.ic_warning_white_48dp,
            R.drawable.ic_location_on_white_48dp,
            R.drawable.ic_help_white_48dp
    };
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
        setupTabIcons();
        setupToolbar();
        ViewPagerListnear(viewPager);
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.icon);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(getString(R.string.Fragment1_titre));
        setSupportActionBar(toolbar);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), getString(R.string.Fragment1_titre));
        adapter.addFragment(new Fragment2(), getString(R.string.Fragment2_titre));
        adapter.addFragment(new Fragment3(), getString(R.string.Fragment3_titre));
        viewPager.setAdapter(adapter);
    }
    public void ViewPagerListnear(ViewPager viewPager){
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        toolbar.setTitle(getString(R.string.Fragment1_titre));
                        break;
                    case 1:
                        toolbar.setTitle(getString(R.string.Fragment2_titre));
                        break;
                    case 2:
                        toolbar.setTitle(getString(R.string.Fragment3_titre));
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.siteweb) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(getString(R.string.siteweb_lien)));
            startActivity(i);
        }
        if (id == R.id.apropos) {
            Intent intent=new Intent(this,AproposActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
