package com.emam.yassmin.subproject_ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.emam.yassmin.subproject_ui.R;
import com.emam.yassmin.subproject_ui.listeners.ItemListener;

import java.util.ArrayList;

/**
 * Created by yahme on 5/6/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private ArrayList<String> choicesList;
    private ItemListener itemListener;

    public ItemAdapter(ArrayList<String> stringArrayList, ItemListener listener)
    {
        choicesList = stringArrayList;
        itemListener = listener;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);

            checkBox = (CheckBox) view.findViewById(R.id.chk_choice);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkbox_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final String choice = choicesList.get(position);

        holder.checkBox.setText(choice);

        holder.checkBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked)
                {
                    itemListener.addChoice(choice);
                }
                else {itemListener.removeChoice(choice);}
            }
        });
    }

    @Override
    public int getItemCount() {
        return choicesList.size();
    }
}