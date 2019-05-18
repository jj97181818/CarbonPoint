package me.jj97181818.carbonpoint.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import me.jj97181818.carbonpoint.R;

public class MemberDataActivity extends AppCompatActivity {
    private int localpoint;
    SQLiteDatabase db;
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

        //開啟或建立資料庫
        db = openOrCreateDatabase("couponDB", Context.MODE_PRIVATE, null);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Cursor c = db.rawQuery("SELECT * FROM userpoint", new String[] {});
        if (c.getCount() > 0) {
            c.moveToFirst();			//將指標移至第一筆資料
            int selectedpoint = c.getInt(0);
            TextView pointView = findViewById(R.id.textView20);
            pointView.setText("我的點數： "+ String.valueOf(selectedpoint));

            localpoint = selectedpoint;
        } else {
            ContentValues mcv = new ContentValues();
            mcv.put("point", 1000);
            db.insert("userpoint", null, mcv);

            localpoint = 1000;
        }
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
