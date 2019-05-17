package me.jj97181818.carbonpoint.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.jj97181818.carbonpoint.R;

public class MyGiftActivity extends AppCompatActivity {
    private View v;
    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;

    SQLiteDatabase db;

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
                    gotoMemberActivity(v);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gift);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //開啟或建立資料
        db = openOrCreateDatabase("couponDB", Context.MODE_PRIVATE, null);

        //list
        ArrayList<Pair<Integer, String>> myDataset = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM coupon", new String[] {});

        if (c.getCount() > 0) {
            c.moveToFirst();			//將指標移至第一筆資料
        }

        for (int i = 0; i < c.getCount(); i++) {
            String name = c.getString(0);	//取得第0欄的資料，根據欄位type使用適當語法
            String description = c.getString(1);
            String date = c.getString(2);
            int image = c.getInt(3);
            String code = c.getString(5);

            myDataset.add(new Pair<>(image, name + "\n" + description + "\n\n" + "優惠碼：" + code + "\n" + "使用期限：" + date));

            c.moveToNext();		//將指標移至下一筆資料
        }


        mAdapter = new MyAdapter(myDataset);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Pair<Integer, String>> mData;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ImageView mImageView;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.info_text);
                mImageView = (ImageView) v.findViewById(R.id.info_img);
            }
        }

        public MyAdapter(List<Pair<Integer, String>> data) {
            mData = data;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.mygiftlayout, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.mImageView.setImageDrawable(getDrawable(mData.get(position).first));
            holder.mTextView.setText(mData.get(position).second);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MyGiftActivity.this, "出示優惠碼給店家，即可兌換優惠～", Toast.LENGTH_SHORT).show();
                }
            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    Toast.makeText(MyGiftActivity.this, "第 " + position + " 個被長按了！", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    public void gotoMainActivity(View v) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
    public void gotoMemberActivity(View v) {
        Intent it = new Intent(this, MemberActivity.class);
        startActivity(it);
    }
    public void gotoNoticeActivity(View v) {
        Intent it = new Intent(this, NoticeActivity.class);
        startActivity(it);
    }
}
