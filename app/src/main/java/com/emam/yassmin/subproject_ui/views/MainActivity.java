package com.emam.yassmin.subproject_ui.views;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emam.yassmin.subproject_ui.R;
import com.emam.yassmin.subproject_ui.adapters.ItemAdapter;
import com.emam.yassmin.subproject_ui.listeners.ItemListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemListener {

    private LinearLayout chooseTypeLinearLayout,
            chooseCityLinearLayout, chooseVenueLinearLayout;
    private ArrayList<String> userChoices;
    private RecyclerView recyclerView;
    private ItemAdapter mAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseCityLinearLayout = (LinearLayout) findViewById(R.id.ll_choose_city);
        chooseTypeLinearLayout = (LinearLayout) findViewById(R.id.ll_choose_type);
        chooseVenueLinearLayout = (LinearLayout) findViewById(R.id.ll_choose_venue);
        userChoices = new ArrayList<>();

        chooseVenueLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter = new ItemAdapter(setVenues(), MainActivity.this);
                String title = "Select Exam Venue";
                String hint = "Please Select Exam Venue";
                showPopupDialog(mAdapter, title, hint);
            }
        });
        chooseTypeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter = new ItemAdapter(setExamsType(), MainActivity.this);
                String title = "Select Exam Type";
                String hint = "Please Select Exam Type";
                showPopupDialog(mAdapter, title, hint);
            }
        });
        chooseCityLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter = new ItemAdapter(setCities(), MainActivity.this);
                String title = "Select Exam City";
                String hint = "Please Select Exam City";
                showPopupDialog(mAdapter, title, hint);
            }
        });
    }

    private void showPopupDialog(ItemAdapter itemAdapter, String popupTitle, String popupHint) {

        LayoutInflater factory = LayoutInflater.from(this);
        final View dialogView = factory.inflate(R.layout.choose_popup_layout, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(dialogView);
        recyclerView = (RecyclerView) dialogView.findViewById(R.id.recycler_view);
        TextView titleTV = (TextView) dialogView.findViewById(R.id.tv_popup_title);
        titleTV.setText(popupTitle);
        TextView hintTV = (TextView) dialogView.findViewById(R.id.tv_popup_hint);
        hintTV.setText(popupHint);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(itemAdapter);
        LinearLayout chooseLL = (LinearLayout) dialogView.findViewById(R.id.ll_choose_done);
        chooseLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < userChoices.size(); i++) {
                    Log.e("tag", userChoices.get(i));
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private ArrayList<String> setExamsType() {
        ArrayList<String> exams = new ArrayList<>();

        for (int x = 0; x < 3; x++) {
            exams.add("exam type" + x);
        }
        return exams;
    }

    private ArrayList<String> setCities() {
        ArrayList<String> cities = new ArrayList<>();

        for (int x = 0; x < 3; x++) {
            cities.add("dubai" + x);
        }
        return cities;
    }

    private ArrayList<String> setVenues() {
        ArrayList<String> venues = new ArrayList<>();

        for (int x = 0; x < 3; x++) {
            venues.add("venue" + x);
        }
        return venues;
    }

    @Override
    public void addChoice(String choice) {

        userChoices.add(choice);
    }

    @Override
    public void removeChoice(String choice) {
        userChoices.remove(choice);
    }
}
