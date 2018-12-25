package com.azkara.hp.azkar.Ui.Azkar_Muslem_Content_Screen;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
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
import com.azkara.hp.azkar.Util.GeneralMethods;

import java.util.ArrayList;

import static com.azkara.hp.azkar.Util.Constants.ConstantsValues.LargeFont;
import static com.azkara.hp.azkar.Util.Constants.ConstantsValues.MediumFont;
import static com.azkara.hp.azkar.Util.Constants.ConstantsValues.SmallFont;

public class AzkarElMoslemContentAdapter extends RecyclerView.Adapter<AzkarElMoslemContentAdapter.Holder> {

    private ArrayList<AzkarElMoslem> azkarElMoslems;
    private Context context;
    private SharedPrefManager prefManager;
    private boolean isVibrate;

    public AzkarElMoslemContentAdapter(ArrayList<AzkarElMoslem> azkarElMoslems, Context context, boolean isVibrate) {
        this.azkarElMoslems = azkarElMoslems;
        this.context = context;
        prefManager = SharedPrefManager.getInstance().doStuff(context);
        this.isVibrate = isVibrate;
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
                zekr.decreaseCount();
                if (zekr.isCountEqualZero()) {
                    if (prefManager.canDisappears()) {
                        if (isVibrate) {
                            vibrate(750);
                        }
                        azkarElMoslems.remove(zekr);
                        notifyDataSetChanged();
                    }
                    holder.tvZekrCount.setText(String.valueOf(zekr.getZekrCount()));
                } else {
                    holder.tvZekrCount.setText(String.valueOf(zekr.getZekrCount()));
                    if (isVibrate) {
                        vibrate(500);
                    }
                }
            }
        });

        switch (prefManager.getFontFamily()) {
            case 1:
                holder.tvZekrContent.setTypeface(GeneralMethods.changeFont1(context));
                break;
            case 2:
                holder.tvZekrContent.setTypeface(GeneralMethods.changeFont2(context));
                break;
            case 3:
                holder.tvZekrContent.setTypeface(GeneralMethods.changeFont3(context));
                break;
        }

        switch (prefManager.getAzkarElMoslemFontSize()) {
            case SmallFont:
                holder.tvZekrContent.setTextSize(SmallFont);
                break;
            case MediumFont:
                holder.tvZekrContent.setTextSize(MediumFont);
                break;
            case LargeFont:
                holder.tvZekrContent.setTextSize(LargeFont);
                break;
        }
        GeneralMethods.changeViewFont(holder.tvZekrCount);
        GeneralMethods.changeViewFont(holder.tvZekrInfo);
    }

    private void showZekrInfo(String zekrInfo) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_zekr_info, null);
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);
        GeneralMethods.changeViewFont(dialogView);
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

    public void setIsVibrate(boolean isVibrate) {
        this.isVibrate = isVibrate;
    }

    private void vibrate(int millis) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(millis, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(millis);
        }
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
