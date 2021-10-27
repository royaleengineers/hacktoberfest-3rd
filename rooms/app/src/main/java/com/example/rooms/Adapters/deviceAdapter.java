package com.example.rooms.Adapters;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rooms.Database.deviceaTable;
import com.example.rooms.Database.primarytable;
import com.example.rooms.FinanceActivity;
import com.example.rooms.MainActivity;
import com.example.rooms.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class deviceAdapter extends RecyclerView.Adapter<deviceAdapter.ViewHolder> {
    private Context mContext;
    private List<deviceaTable> mDevice;
    public primarytable ptable;
    public boolean flag=false;

    public deviceAdapter(Context mContext, List<deviceaTable> mDevice) {
        this.mContext = mContext;
        this.mDevice = mDevice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.switches,parent,false);
        return new deviceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final deviceaTable dtable = mDevice.get(position);
        holder.setdevicev(mDevice.get(position));
        Intent intent= ((Activity)mContext).getIntent();
        final int roompd=intent.getIntExtra("roompid",0);
        if(dtable.getPid()!=roompd){
            holder.itemView.setVisibility(View.GONE);
        }


        FinanceActivity factivity = new FinanceActivity();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,roompd+" "+dtable.getPid(),Toast.LENGTH_SHORT).show();
                if(flag==false){
                    flag=true;
                    factivity.send(dtable.getToggleON());

                }
                else if(flag==true){
                    flag=false;
                    factivity.send(dtable.getToggleOFF());
                }
            }
        });


        if(dtable.getLogoAsset().equals("default")){
            holder.icon.setImageResource(R.drawable.default_btn);
            if(dtable.getDeviceName().equals("Light") || dtable.getDeviceName().equals("light")){
                holder.icon.setImageResource(R.drawable.light_btn);
            }
            else if(dtable.getDeviceName().equals("Fan") || dtable.getDeviceName().equals("fan")){
                holder.icon.setImageResource(R.drawable.fan_btn);
            }
            else if(dtable.getDeviceName().equals("Sensor") || dtable.getDeviceName().equals("sensor")){
                holder.icon.setImageResource(R.drawable.sensor_btn);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDevice.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView icon;
        public TextView dname;

        public ViewHolder(View itemView){
            super(itemView);

            icon=itemView.findViewById(R.id.btn_icon);
            dname=itemView.findViewById(R.id.btn_name);
        }
        void setdevicev(deviceaTable dtable){
            dname.setText(dtable.getDeviceName());
            icon.setImageBitmap(BitmapFactory.decodeFile(dtable.getLogoAsset()));

        }

    }

//    private class ConnectedThread extends Thread {
//        private final InputStream mmInStream;
//        private final OutputStream mmOutStream;
//
//        public ConnectedThread(BluetoothSocket socket) {
//            InputStream tmpIn = null;
//            OutputStream tmpOut = null;
//
//            // Get the input and output streams, using temp objects because
//            // member streams are final
//            try {
//                tmpIn = socket.getInputStream();
//                tmpOut = socket.getOutputStream();
//            } catch (IOException e) {
//            }
//
//            mmInStream = tmpIn;
//            mmOutStream = tmpOut;
//        }
//
//        public void write(String message) {
//            Log.d(TAG, "...Data to send: " + message + "...");
//            byte[] msgBuffer = message.getBytes();
//            try {
//                mmOutStream.write(msgBuffer);
//            } catch (IOException e) {
//                Log.d(TAG, "...Error data send: " + e.getMessage() + "...");
//            }
//        }
//
//    }


}
