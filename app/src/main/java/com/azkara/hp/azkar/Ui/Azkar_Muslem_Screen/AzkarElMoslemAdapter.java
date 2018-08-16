package com.azkara.hp.azkar.Ui.Azkar_Muslem_Screen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AzkarElMoslemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> azkarElMoslemCategory;
    private int OddView = 1, EvenView = 2;

    public AzkarElMoslemAdapter(ArrayList<String> azkarElMoslemCategory) {
        this.azkarElMoslemCategory = azkarElMoslemCategory;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null ;
        if (viewType == EvenView){
           // holder = new EvenViewHolder(LayoutInflater.from(parent.getContext()).inflate());
        }else {

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return azkarElMoslemCategory.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? EvenView : OddView;
    }

    class OddViewHolder extends RecyclerView.ViewHolder {

        public OddViewHolder(View itemView) {
            super(itemView);
        }
    }

    class EvenViewHolder extends RecyclerView.ViewHolder {

        public EvenViewHolder(View itemView) {
            super(itemView);
        }
    }
}
