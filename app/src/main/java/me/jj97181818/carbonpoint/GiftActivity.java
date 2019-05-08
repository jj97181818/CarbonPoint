package me.jj97181818.carbonpoint;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GiftActivity extends AppCompatActivity {
    private View v;
    private GiftActivity.MyAdapter myAdapter;
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
                    gotoMemberActivity(v);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //list
        ArrayList<Pair<Integer, String>> myDataset = new ArrayList<>();

        myDataset.add(new Pair<>(R.drawable.salad, "任一沙拉\n" + "30 點特價 210 元"));
        myDataset.add(new Pair<>(R.drawable.hamburger, "中東香料漢堡 \n" + "20 點特價 290 元 "));
        myDataset.add(new Pair<>(R.drawable.pasta, "野菇蒙太奇義大利麵\n" + "25 點特價 320 元 "));
        myDataset.add(new Pair<>(R.drawable.sandwich, "元氣雙層蔬菜三明治 \n" + "20 點特價 290 元 "));
        myDataset.add(new Pair<>(R.drawable.drink, "任一手打飲品 \n" + "20 點享八折優惠 "));

        myAdapter = new GiftActivity.MyAdapter(myDataset);
        myRecyclerView = (RecyclerView) findViewById(R.id.list_view2);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(myAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<GiftActivity.MyAdapter.ViewHolder> {
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
        public GiftActivity.MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.giftlayout, parent, false);
            GiftActivity.MyAdapter.ViewHolder vh = new GiftActivity.MyAdapter.ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(GiftActivity.MyAdapter.ViewHolder holder, final int position) {
            holder.mImageView.setImageDrawable(getDrawable(mData.get(position).first));
            holder.mTextView.setText(mData.get(position).second);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(GiftActivity.this, "第 " + position + " 個被選擇了！", Toast.LENGTH_SHORT).show();
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(GiftActivity.this, "第 " + position + " 個被長按了！", Toast.LENGTH_SHORT).show();
                    return true;
                }
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
    public void gotoMemberActivity(View v) {
        Intent it = new Intent(this, MemberActivity.class);
        startActivity(it);
    }
    public void gotoNoticeActivity(View v) {
        Intent it = new Intent(this, NoticeActivity.class);
        startActivity(it);
    }

}
