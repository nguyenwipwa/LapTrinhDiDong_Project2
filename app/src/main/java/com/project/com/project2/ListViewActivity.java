package com.project.com.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListViewActivity extends AppCompatActivity {
    LinearLayout  manhinh;
    ExpandableListView explv;
    List<String> listHeader;
    HashMap<String,List<String>> listdata;
    expandble expandble;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        addControl();
        expandble = new expandble(ListViewActivity.this, listHeader, listdata);
        explv.setAdapter(expandble);
        manhinh = (LinearLayout)findViewById(R.id.mh);
        manhinh.setBackgroundResource(R.drawable.d5);

}

    private void addControl() {
        explv = (ExpandableListView)findViewById(R.id.expandanlistview);
        listHeader = new ArrayList<>();
        listdata = new HashMap<String ,List<String>>();
        listHeader.add("ZingMp3");
        listHeader.add("Nhaccuatui");
        listHeader.add("Chiasenhac");
        List<String> phimthang10 = new ArrayList<String>();
        phimthang10.add("Em của ngày hôm qua");
        phimthang10.add("Em của quá khứ");
        phimthang10.add("Em gái mưa");
        List<String>phimthang11 = new ArrayList<String>();
        phimthang11.add("1 2 3 4");
        phimthang11.add("Túy Âm");
        phimthang11.add("Tuổi đá buồn");
        List<String>phimthang12 = new ArrayList<String>();
        phimthang12.add("Phai dấu cuộc tình");
        phimthang12.add("Giận lòng");
        phimthang12.add("Kết thức lâu rồi");
        listdata.put(listHeader.get(0),phimthang10);
        listdata.put(listHeader.get(1),phimthang11);
        listdata.put(listHeader.get(2),phimthang12);



    }
}
