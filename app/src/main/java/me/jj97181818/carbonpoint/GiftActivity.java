package me.jj97181818.carbonpoint;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
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
    private int point = 80;

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
        ArrayList<Coupon> coupons = new ArrayList<>();

        coupons.add(new Coupon("任一沙拉", "30 點特價 210 元", "2019/12/31", R.drawable.salad, 30));
        coupons.add(new Coupon("中東香料漢堡", "20 點特價 290 元", "2019/12/16", R.drawable.hamburger, 20));
        coupons.add(new Coupon("野菇蒙太奇義大利麵", "25 點特價 320 元", "2019/10/03", R.drawable.pasta, 25));
        coupons.add(new Coupon("元氣雙層蔬菜三明治", "20 點特價 190 元", "2019/09/11", R.drawable.sandwich, 20));
        coupons.add(new Coupon("任一手打飲品", "10 點享八折優惠", "2019/11/23", R.drawable.drink, 10));

        myAdapter = new GiftActivity.MyAdapter(coupons);
        myRecyclerView = (RecyclerView) findViewById(R.id.list_view2);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(myAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<GiftActivity.MyAdapter.ViewHolder> {
        private List<Coupon> mData;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ImageView mImageView;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.food_text);
                mImageView = (ImageView) v.findViewById(R.id.food_img);
            }
        }

        public MyAdapter(List<Coupon> data) {
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
            holder.mImageView.setImageDrawable(getDrawable(mData.get(position).image));
            holder.mTextView.setText(mData.get(position).name + "\n" + mData.get(position).description);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(GiftActivity.this)
                            .setTitle("通知")
                            .setIcon(android.R.drawable.ic_menu_info_details)
                            .setMessage("確定要兌換此優惠卷嗎？")
                            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    point -= mData.get(position).point;

                                    TextView pointView = findViewById(R.id.textView16);
                                    pointView.setText(String.valueOf(point));
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
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
    public void gotoMemberActivity(View v) {
        Intent it = new Intent(this, MemberActivity.class);
        startActivity(it);
    }
    public void gotoNoticeActivity(View v) {
        Intent it = new Intent(this, NoticeActivity.class);
        startActivity(it);
    }

}
