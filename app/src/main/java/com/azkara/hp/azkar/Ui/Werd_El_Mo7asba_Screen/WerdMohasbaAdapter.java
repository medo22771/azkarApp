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
        setClickListenerForViews(holder, dailyTask);
        if (dailyTask.getSat()==1) {
            holder.txtSat.setBackgroundResource(R.drawable.black_border_green_background);
        }else {
            holder.txtSat.setBackgroundResource(R.drawable.black_border);
        }

        if (dailyTask.getSun()==1) {
            holder.txtSun.setBackgroundResource(R.drawable.black_border_green_background);
        }else {
            holder.txtSun.setBackgroundResource(R.drawable.black_border);
        }

        if (dailyTask.getMon()==1) {
            holder.txtMon.setBackgroundResource(R.drawable.black_border_green_background);
        }else {
            holder.txtMon.setBackgroundResource(R.drawable.black_border);
        }

        if (dailyTask.getTue()==1) {
            holder.txtTue.setBackgroundResource(R.drawable.black_border_green_background);
        }else {
            holder.txtTue.setBackgroundResource(R.drawable.black_border);
        }

        if (dailyTask.getWed()==1) {
            holder.txtWed.setBackgroundResource(R.drawable.black_border_green_background);
        }else {
            holder.txtWed.setBackgroundResource(R.drawable.black_border);
        }

        if (dailyTask.getThu()==1) {
            holder.txtThu.setBackgroundResource(R.drawable.black_border_green_background);
        }else {
            holder.txtThu.setBackgroundResource(R.drawable.black_border);
        }

        if (dailyTask.getFri()==1) {
            holder.txtFri.setBackgroundResource(R.drawable.black_border_green_background);
        }else {
            holder.txtFri.setBackgroundResource(R.drawable.black_border);
        }
    }

    private void setClickListenerForViews(RowHolder holder, final DailyTask dailyTask) {
        holder.txtSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dailyTask.getSat()==0) {
                    dailyTask.setSat(1);
                    notifyDataSetChanged();
                }
            }
        });

        holder.txtSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dailyTask.getSun()==0) {
                    dailyTask.setSun(1);
                    notifyDataSetChanged();
                }

            }
        });

        holder.txtMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dailyTask.getMon()==0) {
                    dailyTask.setMon(1);
                    notifyDataSetChanged();
                }
            }
        });

        holder.txtTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dailyTask.getTue()==0) {
                    dailyTask.setTue(1);
                    notifyDataSetChanged();
                }
            }
        });

        holder.txtWed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dailyTask.getWed()==0) {
                    dailyTask.setWed(1);
                    notifyDataSetChanged();
                }
            }
        });

        holder.txtThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dailyTask.getThu()==0) {
                    dailyTask.setThu(1);
                    notifyDataSetChanged();
                }
            }
        });

        holder.txtFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dailyTask.getFri()==0) {
                    dailyTask.setFri(1);
                    notifyDataSetChanged();
                }
            }
        });
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
            txtMon = itemView.findViewById(R.id.txtMonday);
            txtTue = itemView.findViewById(R.id.txtTuesday);
            txtWed = itemView.findViewById(R.id.txtWednesday);
            txtThu = itemView.findViewById(R.id.txtThursday);
            txtFri = itemView.findViewById(R.id.txtFriday);

        }
    }
}
