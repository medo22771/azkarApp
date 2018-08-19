package com.azkara.hp.azkar.Ui.Azkar_Muslem_Screen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azkara.hp.azkar.Model.AzkarElMoslemCategory;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Ui.Azkar_Muslem_Content_Screen.AzkarElMoslemContentActivity;

import java.util.ArrayList;

public class AzkarElMoslemAdapter extends RecyclerView.Adapter<AzkarElMoslemAdapter.ZekrTitle> {

    ArrayList<AzkarElMoslemCategory> categories;
    Context context;

    public AzkarElMoslemAdapter(Context context, ArrayList<AzkarElMoslemCategory> categories) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ZekrTitle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ZekrTitle(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_azkar_el_moslem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ZekrTitle holder, int position) {
        AzkarElMoslemCategory category = categories.get(position);
        // TODO: 8/17/2018 change backGround
        holder.tvZekrTitle.setText(category.getTitle());
        holder.itemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AzkarElMoslemContentActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ZekrTitle extends RecyclerView.ViewHolder {
        TextView tvZekrTitle;
        RelativeLayout itemParent;

        public ZekrTitle(View itemView) {
            super(itemView);
            tvZekrTitle = itemView.findViewById(R.id.tvZekrTitle);
            itemParent = itemView.findViewById(R.id.itemParent);
        }
    }

}
