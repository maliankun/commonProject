package com.inspur.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inspur.topo.TopoConnectActivity;


public class MainActivity extends AppCompatActivity {

    EditText aNetEt;
    EditText zNetEt;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aNetEt = findViewById(R.id.et_a_net);
        zNetEt = findViewById(R.id.et_z_net);
        mContext = this;
//        TopoConnectActivity.startAct(MainActivity.this, AppConfig.Constants.TOPO_LIST_IDS, "1111");
//        TopoConnectActivity.startAct(MainActivity.this, AppConfig.Constants.TOPO_LIST_IDS, "1,489847209");

//        Intent intent = new Intent(this, TopoConnectActivity.class);
//        intent.putExtra("aNeId", "1");
//        intent.putExtra("zNeId", "489847209");
//        TopoConnectActivity.startAct(MainActivity.this, AppConfig.Constants.TOPO_LIST_IDS, "1,489847209");
        // Intent intent = new Intent(this, LogicTopoActivity.class);
//        Intent intent = new Intent(this, NetTopoActivity.class);
//        //intent.putExtra("type", "provincetopo");
//        intent.putExtra("aNeId", "1558619973");
//        intent.putExtra("aPortId", "100");
//        intent.putExtra("zNeId", "1503746476");
//        intent.putExtra("zPortId", "200");
//

//        startActivity(intent);

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aNetId = aNetEt.getText().toString();
                String zNetId = zNetEt.getText().toString();

                if (TextUtils.isEmpty(aNetId) || TextUtils.isEmpty(zNetId)) {

                    Toast.makeText(mContext, "输入内容不为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(mContext, TopoConnectActivity.class);
                intent.putExtra("aNeId", aNetId);
                intent.putExtra("zNeId", zNetId);
                startActivity(intent);


            }
        });

//        Intent intent = new Intent(this, LogicTopoActivity.class);
//        intent.putExtra("type", "provincetopo");
//        startActivity(intent);
    }
}
