package com.example.rooms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rooms.Adapters.deviceAdapter;
import com.example.rooms.Database.RoomDatabase;
import com.example.rooms.Database.deviceaTable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import abak.tr.com.boxedverticalseekbar.BoxedVertical;

public class FinanceActivity extends AppCompatActivity implements ServiceConnection, SerialListener {


    FloatingActionButton backbutton;
    TextView roomName, sensordat;
    Button addSwtch;
    int roompid;
    private RoomDatabase roomDatabase;
    private List <deviceaTable>devList;
//    private List <deviceaTable>swtlst;
    private RecyclerView devicerRecycle;
    private deviceAdapter deviceadapter;
    deviceaTable dtable;



    private enum Connected { False, Pending, True }

    private String deviceAddress;
    private SerialService service;

    private TextView receiveText;
    private TextView sendText;


    private Connected connected = Connected.False;
    private boolean initialStart = true;
//    private boolean hexEnabled = false;
    private boolean pendingNewline = false;
    private String newline = TextUtil.newline_crlf;



//    Handler h;
//
//    private static final String TAG = "bluetooth2";
//
//    final int RECIEVE_MESSAGE = 1;        // Status  for Handler
//    private BluetoothAdapter btAdapter = null;
//    private BluetoothSocket btSocket = null;
//    private StringBuilder sb = new StringBuilder();
//
//    private ConnectedThread mConnectedThread;
//
//    // SPP UUID service
//    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
//
//    // MAC-address of Bluetooth module (you must edit this line)
//    private static String address = "00:15:FF:F2:19:5F";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        Intent btintent= getIntent();
        deviceAddress = btintent.getStringExtra("device");

        Window window = FinanceActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(FinanceActivity.this, R.color.colorfin));

        BoxedVertical bv = (BoxedVertical)findViewById(R.id.boxed_vertical);


        sensordat=findViewById(R.id.sensor_dat);

//        h = new Handler() {
//            public void handleMessage(android.os.Message msg) {
//                switch (msg.what) {
//                    case RECIEVE_MESSAGE:                                                   // if receive massage
//                        byte[] readBuf = (byte[]) msg.obj;
//                        String strIncom = new String(readBuf, 0, msg.arg1);                 // create string from bytes array
//                        sb.append(strIncom);                                                // append string
//                        int endOfLineIndex = sb.indexOf("\r\n");                            // determine the end-of-line
//                        if (endOfLineIndex > 0) {                                            // if end-of-line,
//                            String sbprint = sb.substring(0, endOfLineIndex);               // extract string
//                            sb.delete(0, sb.length());                                      // and clear
//                            sensordat.setText( sbprint);
//
//                        }
//                        //Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");
//                        break;
//                }
//            };
//        };





        bv.setOnBoxedPointsChangeListener(new BoxedVertical.OnValuesChangeListener() {
            @Override
            public void onPointsChanged(BoxedVertical boxedPoints, final int value) {
                System.out.println(value);
            }

            @Override
            public void onStartTrackingTouch(BoxedVertical boxedPoints) {
                Toast.makeText(FinanceActivity.this, "onStartTrackingTouch", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(BoxedVertical boxedPoints) {
                Toast.makeText(FinanceActivity.this, "onStopTrackingTouch", Toast.LENGTH_SHORT).show();
            }
        });

        roomName=findViewById(R.id.room_name);
        addSwtch=findViewById(R.id.add_swtch);
        backbutton=findViewById(R.id.back_btn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent= new Intent(FinanceActivity.this,MainActivity.class);
                profileIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
                finish();
            }
        });
        Intent intent= getIntent();
        String roomid=intent.getStringExtra("roomid");
        roompid=intent.getIntExtra("roompid",0);
        roomName.setText(roomid);
        addSwtch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder swtch=new AlertDialog.Builder(FinanceActivity.this);
                View mview=getLayoutInflater().inflate(R.layout.addswitch_dialog,null);
                final EditText sinputtxt=mview.findViewById(R.id.sname);
                final EditText ontxt=mview.findViewById(R.id.onport);
                final EditText offtxt=mview.findViewById(R.id.offport);
                Button sset=mview.findViewById(R.id.set_sbutton);
                swtch.setView(mview);
                final AlertDialog alertDialog = swtch.create();
                sset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String setdevicename=sinputtxt.getText().toString();
                        String setonport=ontxt.getText().toString();
                        String setoffport=offtxt.getText().toString();
                        deviceaTable dtable=new deviceaTable(roompid,setdevicename,"default",setonport,
                                setoffport);
                        long deviceId = roomDatabase.roomDao().switchInsertion(dtable);
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();

            }
        });

                devList=new ArrayList<>();
//                swtlst=new ArrayList<>();
                setupDB2();
                devicerRecycle=findViewById(R.id.switch_recyclerview);
                devicerRecycle.setLayoutManager(new LinearLayoutManager(FinanceActivity.this,LinearLayoutManager.HORIZONTAL,
                        false));
                deviceadapter=new deviceAdapter(FinanceActivity.this,devList);
                devicerRecycle.setAdapter(deviceadapter);


                class getdevice extends AsyncTask<Void, Void, List<deviceaTable>> {
                    @Override
                    protected List<deviceaTable> doInBackground(Void... voids) {
                        return roomDatabase.getDatabase(getApplicationContext()).roomDao().getAllSwitches();
                    }

                    @Override
                    protected void onPostExecute(List<deviceaTable> deviceatables) {
                        super.onPostExecute(deviceatables);
                        if(devList.size()==0){
                            devList.addAll(deviceatables);

//                            if(dtable.getPid()==roompid){

//                            }

//                            for(deviceaTable did : deviceatables){
//                                {
//                                    swtlst.add(did);
//                                }
//                            }
                            deviceadapter.notifyDataSetChanged();
                        }else{
                            devList.add(0,deviceatables.get(0));
                            deviceadapter.notifyItemInserted(0);
                        }
                        devicerRecycle.smoothScrollToPosition(0);
                    }
                }
                new getdevice().execute();




    }

//    @Override
//    public void onDestroy() {
//        if (connected != Connected.False)
//            disconnect();
//        stopService(new Intent(this, SerialService.class));
//        super.onDestroy();
//    }

    @Override
    public void onStart() {
        super.onStart();
        if(service != null)
            service.attach((SerialListener) this);
        else
            startService(new Intent(this, SerialService.class)); // prevents service destroy on unbind from recreated activity caused by orientation change
    }

    @Override
    public void onStop() {
        if(service != null && !this.isChangingConfigurations())
            service.detach();
        super.onStop();
    }

//    @SuppressWarnings("deprecation") // onAttach(context) was added with API 23. onAttach(activity) works for all API versions
//    @Override
//    public void onAttach(@NonNull Activity activity) {
//        super.onAttach(activity);
//        this.bindService(new Intent(this, SerialService.class), (ServiceConnection) this, Context.BIND_AUTO_CREATE);
//    }
//
//    @Override
//    public void onDetach() {
//        try { this.unbindService((ServiceConnection) this); } catch(Exception ignored) {}
//        super.onDetach();
//    }

    @Override
    public void onResume() {
        super.onResume();
        if(initialStart && service != null) {
            initialStart = false;
            this.runOnUiThread(this::connect);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        service = ((SerialService.SerialBinder) binder).getService();
        service.attach((SerialListener) this);
        if(initialStart) {
            initialStart = false;
            this.runOnUiThread(this::connect);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        service = null;
    }



    private void connect() {
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddress);
            status("connecting...");
            connected = Connected.Pending;
            SerialSocket socket = new SerialSocket(this.getApplicationContext(), device);
            service.connect(socket);
        } catch (Exception e) {
            onSerialConnectError(e);
        }
    }

    private void disconnect() {
        connected = Connected.False;
        service.disconnect();
    }

    public void send(String str) {
        if(connected != Connected.True) {
            Toast.makeText(this, "not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            String msg;
            byte[] data;
                msg = str;
                data = (str + newline).getBytes();
            SpannableStringBuilder spn = new SpannableStringBuilder(msg + '\n');
            spn.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorSendText)), 0, spn.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            receiveText.append(spn);
            service.write(data);
        } catch (Exception e) {
            onSerialIoError(e);
        }
    }

    private void receive(byte[] data) {
        String msg = new String(data);
        if(newline.equals(TextUtil.newline_crlf) && msg.length() > 0) {
            // don't show CR as ^M if directly before LF
            msg = msg.replace(TextUtil.newline_crlf, TextUtil.newline_lf);
            // special handling if CR and LF come in separate fragments
            if (pendingNewline && msg.charAt(0) == '\n') {
                Editable edt = receiveText.getEditableText();
                if (edt != null && edt.length() > 1)
                    edt.replace(edt.length() - 2, edt.length(), "");
                }
                pendingNewline = msg.charAt(msg.length() - 1) == '\r';
            }
        sensordat.append(msg);
    }

    private void status(String str) {
        SpannableStringBuilder spn = new SpannableStringBuilder(str + '\n');
        spn.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorStatusText)), 0, spn.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        receiveText.append(spn);
    }

    @Override
    public void onSerialConnect() {
        status("connected");
        connected = Connected.True;
    }

    @Override
    public void onSerialConnectError(Exception e) {
        status("connection failed: " + e.getMessage());
        disconnect();
    }

    @Override
    public void onSerialRead(byte[] data) {
        receive(data);
    }

    @Override
    public void onSerialIoError(Exception e) {
        status("connection lost: " + e.getMessage());
        disconnect();
    }



    public void setupDB2(){ roomDatabase= RoomDatabase.getDatabase(FinanceActivity.this); }




    private void errorExit(String title, String message){
        Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
        finish();
    }

}