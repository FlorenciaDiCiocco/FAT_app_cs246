package com.team3.fat.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.team3.fat.CustomListAdapter;
import com.team3.fat.ListItem;
import com.team3.fat.R;
import com.team3.fat.addWeight;
import com.team3.fat.addWeightKeyboard;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList userList = getListData();
        final ListView lv = (ListView) root.findViewById(R.id.user_list);
        lv.setAdapter(new CustomListAdapter(getActivity(), userList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem user = (ListItem) lv.getItemAtPosition(position);
                Toast.makeText(getActivity(), "Selected :" + " " + user.getWeight()+", "+ user.getDate(), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
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

    public void addWeight(View view){
//        Intent weightIntent = new Intent(this, addWeight.class);
//        startActivity(weightIntent);
//
//        Intent intentToWeight = new Intent(this, addWeightKeyboard.class);
//        startActivity(intentToWeight);
        try {
            Intent weightIntent = new Intent(getActivity(), addWeight.class);
            startActivity(weightIntent);

            Intent intentToWeight = new Intent(getActivity(), addWeightKeyboard.class);
            startActivity(intentToWeight);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(),
                    "I'm having some problems! Try again later!", Toast.LENGTH_LONG).show();
        }
    }
}