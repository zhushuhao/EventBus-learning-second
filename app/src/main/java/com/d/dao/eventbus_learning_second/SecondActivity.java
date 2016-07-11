package com.d.dao.eventbus_learning_second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.d.dao.eventbus_learning_second.bean.MainMessage;

import de.greenrobot.event.EventBus;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        EventBus.getDefault().register(this);
        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // 发送MainMessage
            case R.id.button1:
                EventBus.getDefault().post(new MainMessage("hello EventBus Main"));
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        EventBus.getDefault().unregister(this);
    }
}
