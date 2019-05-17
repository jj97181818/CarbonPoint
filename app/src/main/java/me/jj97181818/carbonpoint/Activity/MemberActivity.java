package me.jj97181818.carbonpoint.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.jj97181818.carbonpoint.R;

public class MemberActivity extends AppCompatActivity {

    private View v;
    private MemberActivity.MyAdapter myAdapter;
    private RecyclerView myRecyclerView;

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
        setContentView(R.layout.activity_member);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //list
        ArrayList<Pair<Integer, String>> myDataset = new ArrayList<>();

        myDataset.add(new Pair<>(R.drawable.transparent, "-會員-"));
        myDataset.add(new Pair<>(R.drawable.rightarrow, "基本資料修改"));
        myDataset.add(new Pair<>(R.drawable.rightarrow, "點數紀錄"));
        myDataset.add(new Pair<>(R.drawable.rightarrow, "我的優惠卷"));
        myDataset.add(new Pair<>(R.drawable.rightarrow, "優惠卷商店"));
        myDataset.add(new Pair<>(R.drawable.transparent, "-載具設定-"));
        myDataset.add(new Pair<>(R.drawable.rightarrow, "手機條碼設定"));
        myDataset.add(new Pair<>(R.drawable.transparent, "-常見問題-"));
        myDataset.add(new Pair<>(R.drawable.rightarrow, "店家"));
        myDataset.add(new Pair<>(R.drawable.transparent, "-聯絡我們-"));
        myDataset.add(new Pair<>(R.drawable.rightarrow, "留言板"));

        myAdapter = new MemberActivity.MyAdapter(myDataset);
        myRecyclerView = (RecyclerView) findViewById(R.id.listviewMemberData);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(myAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MemberActivity.MyAdapter.ViewHolder> {
        private List<Pair<Integer, String>> mData;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ImageView mImageView;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.food_text);
                mImageView = (ImageView) v.findViewById(R.id.food_img);
            }
        }

        public MyAdapter(List<Pair<Integer, String>> data) {
            mData = data;
        }

        @Override
        public MemberActivity.MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.memberlayout, parent, false);
            MemberActivity.MyAdapter.ViewHolder vh = new MemberActivity.MyAdapter.ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MemberActivity.MyAdapter.ViewHolder holder, final int position) {
            holder.mImageView.setImageDrawable(getDrawable(mData.get(position).first));
            holder.mTextView.setText(mData.get(position).second);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it;
                    TextView mTextView = (TextView) v.findViewById(R.id.food_text);
                    switch(position) {
                        case 0:
                            break;
                        case 1:
                            it = new Intent(MemberActivity.this, MemberDataActivity.class);
                            startActivity(it);
                            break;
                        case 2:
                            it = new Intent(MemberActivity.this, PointActivity.class);
                            startActivity(it);
                            break;
                        case 3:
                            it = new Intent(MemberActivity.this, MyGiftActivity.class);
                            startActivity(it);
                            break;
                        case 4:
                            it = new Intent(MemberActivity.this, GiftActivity.class);
                            startActivity(it);
                            break;
                        case 5:
                            break;
                        case 6:
                            it = new Intent(MemberActivity.this, VehicleActivity.class);
                            startActivity(it);
                            break;
                        case 7:
                            break;
                        case 8:
                            it = new Intent(MemberActivity.this, FAQRestaurantActivity.class);
                            startActivity(it);
                            break;
                        case 9:
                            break;
                        case 10:
                            it = new Intent(MemberActivity.this, MessageBoardActivity.class);
                            startActivity(it);
                            break;
                        default:
                    }
                }
//
//                public void onClick(DialogInterface dialog, int which) {
//                    if (which == DialogInterface.BUTTON_POSITIVE) {
//                        TextView pointView = findViewById(R.id.textView16);
//                        pointView.setText("55");
//                    }
//                }
            });
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
    public void gotoNoticeActivity(View v) {
        Intent it = new Intent(this, NoticeActivity.class);
        startActivity(it);
    }

}
