package com.example.matrix_yang.cloudcontact;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import model.Friend;
import org.json.JSONException;
import util.HttpClientUtil;
import util.Util;
import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;
import zrc.widget.ZrcListView.OnStartListener;

public class MainActivity extends Activity {
    private ZrcListView listView;
    private Handler handler;
    private ArrayList<String> msgs;
    private ArrayList<Friend> friends;
    private int pageId = -1;
    private MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ZrcListView) findViewById(R.id.zListView);
        handler = new Handler();

        // 设置默认偏移量，主要用于实现透明标题栏功能。（可选）
        float density = getResources().getDisplayMetrics().density;
        listView.setFirstTopOffset((int) (50 * density));

        // 设置下拉刷新的样式（可选，但如果没有Header则无法下拉刷新）
        SimpleHeader header = new SimpleHeader(this);
        header.setTextColor(0xff0066aa);
        header.setCircleColor(0xff33bbee);
        listView.setHeadable(header);

        // 设置加载更多的样式（可选）
        SimpleFooter footer = new SimpleFooter(this);
        footer.setCircleColor(0xff33bbee);
        listView.setFootable(footer);

        // 设置列表项出现动画（可选）
        listView.setItemAnimForTopIn(R.anim.topitem_in);
        listView.setItemAnimForBottomIn(R.anim.bottomitem_in);

        listView.setOnItemClickListener(new ZrcListView.OnItemClickListener() {
            @Override
            public void onItemClick(ZrcListView parent, View view, int position, long id) {
                Friend f= (Friend) listView.getItemAtPosition(position);
                System.out.println("------------->"+f.toString());
                Intent intent =new Intent(MainActivity.this,infoActivity.class);
                intent.putExtra("info",f.toString());
                startActivity(intent);
            }
        });

        // 下拉刷新事件回调（可选）
        listView.setOnRefreshStartListener(new OnStartListener() {
            @Override
            public void onStart() {
                refresh();
            }
        });

        // 加载更多事件回调（可选）
        listView.setOnLoadMoreStartListener(new OnStartListener() {
            @Override
            public void onStart() {
                loadMore();
            }
        });

        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        listView.refresh(); // 主动下拉刷新
    }

    private void refresh(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * 2); // 随机数模拟成功失败。这里从有数据开始。
                if(rand == 0 || pageId == -1){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String r = HttpClientUtil.sendGet("http://192.168.31.228/Friend/getAll");
                            System.out.println("second----------------------"+r + Thread.currentThread().getName());
                            Message message = new Message();
                            message.obj =r;
                            try {
                                friends= (ArrayList<Friend>) Util.JSONArrayStringtoList(r);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    adapter.notifyDataSetChanged();
                    listView.setRefreshSuccess("加载成功"); // 通知加载成功
                    listView.startLoadMore(); // 开启LoadingMore功能
                }else{
                    listView.setRefreshFail("加载失败");
                }
            }
        }, 2 * 1000);
    }

    private void loadMore(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pageId++;
                if(pageId<10){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String r = HttpClientUtil.sendGet("http://192.168.31.228/Friend/getAll");
                            System.out.println("second----------------------"+r + Thread.currentThread().getName());
                            Message message = new Message();
                            message.obj =r;
                            try {
                                List<Friend> morefriends= (ArrayList<Friend>) Util.JSONArrayStringtoList(r);
                                friends.addAll(morefriends);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();
                    adapter.notifyDataSetChanged();
                    listView.setLoadMoreSuccess();
                }else{
                    listView.stopLoadMore();
                }
            }
        }, 2 * 1000);
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return friends==null ? 0 : friends.size();
        }
        @Override
        public Object getItem(int position) {
            return friends.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemView listItemView;
            if(convertView==null) {
                //textView = (TextView) getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);

                // 通过LayoutInflater将xml中定义的视图实例化到一个View中
                convertView = getLayoutInflater().inflate(
                        R.layout.item, null);

                // 实例化一个封装类ListItemView，并实例化它的两个域
                listItemView = new ListItemView();
                listItemView.imageView = (ImageView) convertView
                        .findViewById(R.id.image);
                listItemView.textView = (TextView) convertView
                        .findViewById(R.id.title);

                // 将ListItemView对象传递给convertView
                convertView.setTag(listItemView);
            }else{
                // 从converView中获取ListItemView对象
                listItemView = (ListItemView) convertView.getTag();
            }
            // 获取到mList中指定索引位置的资源
            Drawable img = MainActivity.this.getResources().getDrawable(R.drawable.abc);
            String title = friends.get(position).getName();

            // 将资源传递给ListItemView的两个域对象
            listItemView.imageView.setImageDrawable(img);
            listItemView.textView.setText(title);
            // 返回convertView对象
            return convertView;
        }
    }
    class ListItemView {
        ImageView imageView;
        TextView textView;
    }
}
