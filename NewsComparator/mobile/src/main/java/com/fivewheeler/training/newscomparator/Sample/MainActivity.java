package com.fivewheeler.training.newscomparator.Sample;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.fivewheeler.training.newscomparator.R;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ExpandListAdapter ExpAdapter;
    private ArrayList<Group> ExpListItems;
    private ExpandableListView ExpandList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandList = (ExpandableListView) findViewById(R.id.exp_list);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandListAdapter(MainActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);

    }

    public ArrayList<Group> SetStandardGroups() {

        String group_names[] = {"Local News","Entertainment", "Business","Technology","Sports","Education","Health"};

        String country_names[] = {"The Hindu","Deccan Chronicle","Indian Express","The Times Of India",
                "The Hindu","Deccan Chronicle","Indian Express","The Times Of India",
                "The Hindu","Deccan Chronicle","Indian Express","The Times Of India",
                "The Hindu","Deccan Chronicle","Indian Express","The Times Of India",
                "The Hindu","Deccan Chronicle","Indian Express","The Times Of India",
                "The Hindu","Deccan Chronicle","Indian Express","The Times Of India",
                "The Hindu","Deccan Chronicle","Indian Express","The Times Of India"};

        int Images[] = { R.drawable.d, R.drawable.f,
                R.drawable.b, R.drawable.a,
                R.drawable.d, R.drawable.f,
                R.drawable.b, R.drawable.a,

                R.drawable.d, R.drawable.f,
                R.drawable.b, R.drawable.a,
                R.drawable.d, R.drawable.f,
                R.drawable.b, R.drawable.a,
                R.drawable.d, R.drawable.f,
                R.drawable.b, R.drawable.a,
                R.drawable.d, R.drawable.f,
                R.drawable.b, R.drawable.a,
                R.drawable.d, R.drawable.f,
                R.drawable.b, R.drawable.a
        };

        ArrayList<Group> list = new ArrayList<Group>();

        ArrayList<Child> ch_list;

        int size = 4;
        int j = 0;

        for (String group_name : group_names) {
            Group gru = new Group();
            gru.setName(group_name);

            ch_list = new ArrayList<Child>();
            for (; j < size; j++) {
                Child ch = new Child();
                ch.setName(country_names[j]);
                ch.setImage(Images[j]);
                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);

            size = size + 4;
        }

        return list;
    }

}