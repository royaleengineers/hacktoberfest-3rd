package com.example.rooms.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rooms.Database.primarytable;
import com.example.rooms.FinanceActivity;
import com.example.rooms.R;

import java.util.List;

public class roomAdapter extends RecyclerView.Adapter<roomAdapter.ViewHolder> {
    private Context mContext;
    private List<primarytable> mRoom;
    public primarytable ptable;

    public roomAdapter(Context mContext, List<primarytable> mRoom) {
        this.mContext = mContext;
        this.mRoom = mRoom;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.items,parent,false);
        return new roomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final primarytable ptable = mRoom.get(position);
        holder.setroomv(mRoom.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, FinanceActivity.class);
                intent.putExtra("roomid",ptable.getRoomName());
                intent.putExtra("roompid",ptable.getPid());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mRoom.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView room;
        public TextView date;

        public ViewHolder(View itemView){
            super(itemView);

            room=itemView.findViewById(R.id.room_name);
            date=itemView.findViewById(R.id.date_time);
        }
        void setroomv(primarytable ptable){
            room.setText(ptable.getRoomName());
            date.setText(ptable.getDateTime());

        }

    }
}
