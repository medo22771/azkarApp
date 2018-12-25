package com.azkara.hp.azkar.Ui.sebhaAzkarScreen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Ui.Sebha_Screen.SebhaActivity;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;

import java.util.ArrayList;

public class SebhaAzkarAdapter extends RecyclerView.Adapter<SebhaAzkarAdapter.Holder> {

    private ArrayList<String> azkars;
    private Context context;

    public SebhaAzkarAdapter(ArrayList<String> azkars, Context context) {
        this.azkars = azkars;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_sebha_azkar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final String zekr = azkars.get(position);
        holder.tvZekr.setText(zekr);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SebhaActivity.class);
                intent.putExtra(Constants.IntentStrings.SebhaZekr, zekr);
                context.startActivity(intent);
            }
        });
        GeneralMethods.changeViewFont(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return azkars.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tvZekr;

        public Holder(View itemView) {
            super(itemView);
            tvZekr = itemView.findViewById(R.id.tvZekr);
        }
    }
}
