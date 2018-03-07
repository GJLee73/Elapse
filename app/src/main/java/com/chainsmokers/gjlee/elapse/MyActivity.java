package com.chainsmokers.gjlee.elapse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chainsmokers.gjlee.elapse.page.GamesPage;
import com.chainsmokers.gjlee.elapse.page.ListPage;
import com.chainsmokers.gjlee.elapse.page.MainPage;

public class MyActivity extends AppCompatActivity {

    private ImageView buttonLogout;

    private TabLayout tabLayout;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("isLogin",false);
                editor.commit();

                Intent intent = new Intent(MyActivity.this, MainActivity.class);
                Toast.makeText(getApplicationContext(),"Logout Success",Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });

        // TabLayout 설정.
        tabLayout = findViewById(R.id.tabLayout_My);
        tabLayout.addTab(tabLayout.newTab().setText("My Page"));
        tabLayout.addTab(tabLayout.newTab().setText("List"));
        tabLayout.addTab(tabLayout.newTab().setText("Friends"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // ViewPager 설정.
        viewPager = findViewById(R.id.viewPager_My);
        viewPager.setAdapter(new MyActivity.PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    // ViewPager PageAdapter 선언.
    private class PageAdapter extends FragmentPagerAdapter {

        private int pageCount;

        public PageAdapter(FragmentManager fm, int pageCount) {
            super(fm);
            this.pageCount = pageCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return GamesPage.newInstance();
                case 1:
                    return ListPage.newInstance();
                case 2:
                    return GamesPage.newInstance();
                default:
                    return MainPage.newInstance();
            }
        }

        @Override
        public int getCount() {
            return pageCount;
        }
    }
}
