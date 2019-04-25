package me.jj97181818.carbonpoint;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CooperationActivity extends AppCompatActivity {

    private ViewPager mVpgContent;
    private TabLayout mTblTitle;

    private HotFragment hotFragment;
    private NewFragment newFragment;
    private NearFragment nearFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperation);

        hotFragment = new HotFragment();
        newFragment = new NewFragment();
        nearFragment = new NearFragment();

        mVpgContent = (ViewPager) findViewById(R.id.mVpgContent);
        mTblTitle = (TabLayout) findViewById(R.id.mTblTitle);

        mVpgContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Nullable
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = nearFragment;
                        break;
                    case 1:
                        fragment = newFragment;
                        break;
                    case 2:
                        fragment = hotFragment;
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                String title = null;
                switch (position) {
                    case 0:
                        title = getString(R.string.cooperationNear);
                        break;
                    case 1:
                        title = getString(R.string.cooperationNew);
                        break;
                    case 2:
                        title = getString(R.string.cooperationHot);
                        break;
                }
                return title;
            }
        });


        mTblTitle.setupWithViewPager(mVpgContent);

    }
}
