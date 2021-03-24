package com.team3.fat.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.team3.fat.CustomListAdapter;
import com.team3.fat.Globals;
import com.team3.fat.ListItem;
import com.team3.fat.R;
import com.team3.fat.addWeight;
import com.team3.fat.addWeightKeyboard;
import com.team3.fat.add_weight_final;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button adding;
    EditText _myheader;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getListData();
        ArrayList userList = Globals.get_weight_list();
        final ListView lv = (ListView) root.findViewById(R.id.user_list);
        lv.setAdapter(new CustomListAdapter(getActivity(), userList));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                ListItem user = (ListItem) lv.getItemAtPosition(position);
                Toast.makeText(getActivity(), "Selected :" + " " + user.getWeight()+", "+ user.getDate(), Toast.LENGTH_SHORT).show();
            }
        });

        //Here we call Lucy's view to add a register
        adding=root.findViewById(R.id.addbutton);
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weightIntent = new Intent(getActivity(), add_weight_final.class);
                startActivity(weightIntent);
            }
        });



        return root;
    }



    public ArrayList getListData() {
        //This function is to add elements to the list that will be printed
        float number = 88.5f;
        ListItem input = new ListItem();
        input.setDate("13 december");
        input.setWeight(number);
        Globals.set_weight(input);
        return Globals.get_weight_list();
    }
}