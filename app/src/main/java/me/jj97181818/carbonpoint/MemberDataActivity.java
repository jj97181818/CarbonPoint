package me.jj97181818.carbonpoint;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MemberDataActivity extends AppCompatActivity {
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
                    gotoNoticeActivity(v);
                    return true;
                case R.id.navigation_member:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_data);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void gotoMainActivity(View v) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
    public void gotoNoticeActivity(View v) {
        Intent it = new Intent(this, NoticeActivity.class);
        startActivity(it);
    }
}
