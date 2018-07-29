package com.azkara.hp.azkar.Ui.Werd_El_Mo7asba_Screen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azkara.hp.azkar.Model.DailyTask;
import com.azkara.hp.azkar.R;

import java.util.ArrayList;

public class WerdMohasbaAdapter extends RecyclerView.Adapter<WerdMohasbaAdapter.RowHolder> {

    private ArrayList<DailyTask> dailyTasks;
    private Context context;

    public WerdMohasbaAdapter(ArrayList<DailyTask> dailyTasks, Context context) {
        this.dailyTasks = dailyTasks;
        this.context = context;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RowHolder(LayoutInflater.from(context).inflate(R.layout.row_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        DailyTask dailyTask = dailyTasks.get(position);
        holder.txtTask.setText(dailyTask.getTaskName());
        checkFroCheckedDays(holder, dailyTask);
        setClickListenerForViews(holder, dailyTask);
        if (dailyTask.isDayChecked(context.getString(R.string.saturday))) {
            holder.txtSat.setBackgroundResource(R.drawable.black_border_green_background);
        }else if (dailyTask.isDayChecked(context.getString(R.string.sunday))) {
            holder.txtSun.setBackgroundResource(R.drawable.black_border_green_background);
        }else {
            holder.txtSat.setBackgroundResource(R.drawable.black_border);
        }
    }

    private void checkFroCheckedDays(RowHolder holder, DailyTask dailyTask) {

        if (dailyTask.isDayChecked(context.getString(R.string.saturday))) {
            holder.txtSat.setBackgroundResource(R.drawable.black_border_green_background);
        }else if (dailyTask.isDayChecked(context.getString(R.string.sunday))) {
            holder.txtSun.setBackgroundResource(R.drawable.black_border_green_background);
        }else {
            holder.txtSat.setBackgroundResource(R.drawable.black_border);
        }
//        if (dailyTask.isDayChecked(context.getString(R.string.monday))) {
//            holder.txtMon.setBackgroundResource(R.drawable.black_border_green_background);
//        }
//        if (dailyTask.isDayChecked(context.getString(R.string.tuesday))) {
//            holder.txtTue.setBackgroundResource(R.drawable.black_border_green_background);
//        }
//        if (dailyTask.isDayChecked(context.getString(R.string.wednesday))) {
//            holder.txtWed.setBackgroundResource(R.drawable.black_border_green_background);
//        }
//        if (dailyTask.isDayChecked(context.getString(R.string.thursday))) {
//            holder.txtThu.setBackgroundResource(R.drawable.black_border_green_background);
//        }
//        if (dailyTask.isDayChecked(context.getString(R.string.friday))) {
//            holder.txtFri.setBackgroundResource(R.drawable.black_border_green_background);
//        }

    }

    private void setClickListenerForViews(RowHolder holder, final DailyTask dailyTask) {
        holder.txtSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dailyTask.isDayChecked(context.getString(R.string.saturday))) {
                    dailyTask.setDayToBeChecked((context.getString(R.string.saturday)));
                    notifyDataSetChanged();
                }
            }
        });

        holder.txtSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dailyTask.isDayChecked((context.getString(R.string.sunday)))) {
                    dailyTask.setDayToBeChecked((context.getString(R.string.sunday)));
                    notifyDataSetChanged();
                }

            }
        });

//        holder.txtMon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dailyTask.setDayToBeChecked((context.getString(R.string.monday)));
//                notifyDataSetChanged();
//            }
//        });
//
//        holder.txtTue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dailyTask.setDayToBeChecked((context.getString(R.string.tuesday)));
//                notifyDataSetChanged();
//            }
//        });
//
//        holder.txtWed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dailyTask.setDayToBeChecked((context.getString(R.string.wednesday)));
//                notifyDataSetChanged();
//            }
//        });
//
//        holder.txtThu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dailyTask.setDayToBeChecked((context.getString(R.string.thursday)));
//                notifyDataSetChanged();
//            }
//        });
//
//        holder.txtFri.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dailyTask.setDayToBeChecked((context.getString(R.string.friday)));
//                notifyDataSetChanged();
//            }
//        });
    }

    public ArrayList<DailyTask> getDailyTasks() {
        return this.dailyTasks;
    }

    @Override
    public int getItemCount() {
        return dailyTasks.size();
    }


    class RowHolder extends RecyclerView.ViewHolder {

        TextView txtTask, txtSat, txtSun, txtMon, txtTue, txtWed, txtThu, txtFri;

        public RowHolder(View itemView) {
            super(itemView);
            txtTask = itemView.findViewById(R.id.txtTaskName);
            txtSat = itemView.findViewById(R.id.txtSaturday);
            txtSun = itemView.findViewById(R.id.txtSunday);
//            txtMon = itemView.findViewById(R.id.txtMonday);
//            txtTue = itemView.findViewById(R.id.txtTuesday);
//            txtWed = itemView.findViewById(R.id.txtWednesday);
//            txtThu = itemView.findViewById(R.id.txtThursday);
//            txtFri = itemView.findViewById(R.id.txtFriday);

        }
    }
}
