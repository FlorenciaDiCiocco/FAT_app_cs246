package com.team3.fat;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayHome extends AppCompatActivity {
    public static String weight_type = "pounds";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_home);
        ArrayList userList = getListData();
        final ListView lv = (ListView) findViewById(R.id.user_list);
        lv.setAdapter(new CustomListAdapter(this, userList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem user = (ListItem) lv.getItemAtPosition(position);
                Toast.makeText(DisplayHome.this, "Selected :" + " " + user.getWeight()+", "+ user.getDate(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayList getListData() {
        //This function is to add elements to the list that will be printed
        float number = 88.5f;
        ArrayList<ListItem> results = new ArrayList<>();
        ListItem user1 = new ListItem();
        user1.setDate("december");
        user1.setWeight(number);
        results.add(user1);
        ListItem user2 = new ListItem();
        user2.setDate("december");
        user2.setWeight(number);
        results.add(user2);
        ListItem user3 = new ListItem();
        user3.setDate("december");
        user3.setWeight(number);
        results.add(user3);
        return results;
    }

    public void addWeight(){
        Intent intent = new Intent(this, addWeight.class);

        //intent.putExtra(EXTRA_MESSAGE, message2);
        Log.d(this.getLocalClassName(),"About to create intent with new weight.");
        startActivity(intent);
    }
}