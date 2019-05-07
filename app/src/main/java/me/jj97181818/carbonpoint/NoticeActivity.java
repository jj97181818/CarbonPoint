package me.jj97181818.carbonpoint;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class NoticeActivity extends AppCompatActivity {

    private ViewPager mVpgContent;
    private TabLayout mTblTitle;

    private StoreFragment storeFragment;
    private GameFragment gameFragment;

    private View v;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    gotoMainActivity(v);
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_member:
                    gotoMemberActivity(v);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        storeFragment = new StoreFragment();
        gameFragment = new GameFragment();

        mVpgContent = (ViewPager) findViewById(R.id.vpgContent);
        mTblTitle = (TabLayout) findViewById(R.id.tblTitle);

        mVpgContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Nullable
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = storeFragment;
                        break;
                    case 1:
                        fragment = gameFragment;
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                String title = null;
                switch (position) {
                    case 0:
                        title = getString(R.string.noticeStore);
                        break;
                    case 1:
                        title = getString(R.string.noticeGame);
                        break;
                }
                return title;
            }
        });

        mTblTitle.setupWithViewPager(mVpgContent);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void gotoMainActivity(View v) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
    public void gotoMemberActivity(View v) {
        Intent it = new Intent(this, MemberActivity.class);
        startActivity(it);
    }

}
