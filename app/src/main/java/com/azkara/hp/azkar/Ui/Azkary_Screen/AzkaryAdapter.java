package com.azkara.hp.azkar.Ui.Azkary_Screen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.azkara.hp.azkar.Model.Azkary;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Util.GeneralMethods;

import java.util.ArrayList;

public class AzkaryAdapter extends RecyclerView.Adapter<AzkaryAdapter.Holder> {
    ArrayList<Azkary> azkaries;

    public AzkaryAdapter(ArrayList<Azkary> azkaries) {
        this.azkaries = azkaries;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_azkary,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final Azkary azkary = azkaries.get(position);
        if (azkary.isChecked()){
            holder.cbZekr.setChecked(true);
        }
        holder.tvZekr.setText(azkary.getZekr_content());

        holder.cbZekr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                azkary.setChecked(isChecked);
            }
        });

        GeneralMethods.changeViewFont(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return azkaries.size();
    }

    public ArrayList<Azkary> getAzkaries(){
        return this.azkaries;
    }

    public void addNewZekr(Azkary azkary){
        azkaries.add(azkary);
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        CheckBox cbZekr ;
        TextView tvZekr ;
        public Holder(View itemView) {
            super(itemView);
            cbZekr = itemView.findViewById(R.id.cbZekr);
            tvZekr = itemView.findViewById(R.id.tvZekr);
        }
    }
}
