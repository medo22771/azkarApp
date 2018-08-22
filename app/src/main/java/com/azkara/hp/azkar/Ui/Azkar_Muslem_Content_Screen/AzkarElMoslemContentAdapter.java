package com.azkara.hp.azkar.Ui.Azkar_Muslem_Content_Screen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.azkara.hp.azkar.Model.AzkarElMoslem;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.easyandroidanimations.library.FadeInAnimation;

import java.util.ArrayList;

public class AzkarElMoslemContentAdapter extends RecyclerView.Adapter<AzkarElMoslemContentAdapter.Holder> {

    private ArrayList<AzkarElMoslem> azkarElMoslems;
    private Context context;
    private SharedPrefManager prefManager;

    public AzkarElMoslemContentAdapter(ArrayList<AzkarElMoslem> azkarElMoslems, Context context) {
        this.azkarElMoslems = azkarElMoslems;
        this.context = context;
        prefManager = SharedPrefManager.getInstance().doStuff(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_azkar_el_moslem_content, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        final AzkarElMoslem zekr = azkarElMoslems.get(position);
        holder.tvZekrInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showZekrInfo(zekr.getZekrInfo());
            }
        });
        holder.tvZekrCount.setText(String.valueOf(zekr.getZekrCount()));
        holder.tvZekrContent.setText(zekr.getZekrContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FadeInAnimation(holder.cardView)
                        .setDuration(500)
                        .animate();
                if (zekr.decreaseCount()) {
                    if (prefManager.canDisappears()) {
                        azkarElMoslems.remove(zekr);
                        notifyItemRemoved(position);
                    }
                } else {
                    holder.tvZekrCount.setText(String.valueOf(zekr.getZekrCount()));
                }
            }
        });
    }

    private void showZekrInfo(String zekrInfo) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_zekr_info, null);
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        TextView tvZekrInfo = dialogView.findViewById(R.id.tvZekrInfo);
        Button btnOk = dialogView.findViewById(R.id.btnOk);

        tvZekrInfo.setText(zekrInfo);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return azkarElMoslems.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvZekrContent, tvZekrInfo, tvZekrCount;

        public Holder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.zekrCard);
            tvZekrContent = itemView.findViewById(R.id.tvZekrContent);
            tvZekrCount = itemView.findViewById(R.id.tvZekrCount);
            tvZekrInfo = itemView.findViewById(R.id.tvZekrInfo);
        }
    }
}
