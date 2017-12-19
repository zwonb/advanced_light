package com.zwonb.myeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView tv_message;
    private Button bt_message;
    private Button bt_subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
EventBus.getDefault().register(this);
        tv_message = (TextView) this.findViewById(R.id.tv_message);
        tv_message.setText("MainActivity");
        bt_subscription = (Button) this.findViewById(R.id.bt_subscription);
        bt_subscription.setText("注册事件");
        bt_message = (Button) this.findViewById(R.id.bt_message);
        bt_message.setText("跳转到SecondActivity");
        bt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        bt_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new MessageEvent("helllo"));
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
//                if (!EventBus.getDefault().isRegistered(MainActivity.this)) {
//                    //注册事件
//                    EventBus.getDefault().register(MainActivity.this);
//                } else {
//                    Toast.makeText(MainActivity.this, "请勿重复注册事件", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void getEvent(MessageEvent messageEvent) {
        tv_message.setText(messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
//        取消注册事件
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
