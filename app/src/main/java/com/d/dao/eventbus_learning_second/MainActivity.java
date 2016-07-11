package com.d.dao.eventbus_learning_second;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.d.dao.eventbus_learning_second.bean.MainMessage;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView mText;
    private Button mButton;
    private final static String TAG = "EventBusTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.text);
        mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
        EventBus.getDefault().register(this);
    }

//    07-11 00:10:32.508 17347-17347/com.d.dao.eventbus_learning_second E/EventBusTest: MainThread接收到消息hello EventBus Main时间:1468210232512
//    07-11 00:10:32.508 17347-17347/com.d.dao.eventbus_learning_second E/EventBusTest: onMainThread returned: Thread[main,5,main]
//    07-11 00:10:32.508 17347-17724/com.d.dao.eventbus_learning_second E/EventBusTest: BackGroundThread接收到消息hello EventBus Main
//    07-11 00:10:32.508 17347-17724/com.d.dao.eventbus_learning_second E/EventBusTest: onBackgroundThread returned: Thread[pool-1-thread-1,5,main]时间:1468210232514
//    07-11 00:10:32.508 17347-17347/com.d.dao.eventbus_learning_second E/EventBusTest: PostThread接收到消息hello EventBus Main
//    07-11 00:10:32.508 17347-17347/com.d.dao.eventbus_learning_second E/EventBusTest: onPostThread returned: Thread[main,5,main]时间:1468210232515
//    07-11 00:10:32.508 17347-17727/com.d.dao.eventbus_learning_second E/EventBusTest: AsyncThread接收到消息hello EventBus Main
//    07-11 00:10:32.508 17347-17727/com.d.dao.eventbus_learning_second E/EventBusTest: onAsyncThread returned: Thread[pool-1-thread-2,5,main]时间:1468210232518
//
//            --------- beginning of /dev/log/system


    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onMainMessage1(MainMessage msg) {
        mText.setText(msg.getMsg());
        Log.e(TAG,"MainThread接收到消息"+msg.getMsg()+ "时间:"+System.currentTimeMillis());
        Log.e(TAG, "onMainThread returned: " + Thread.currentThread());
    }
    @Subscribe(threadMode = ThreadMode.BackgroundThread)
    public void onMainMessage2(MainMessage msg) {
        Log.e(TAG,"BackGroundThread接收到消息"+msg.getMsg());
        Log.e(TAG, "onBackgroundThread returned: " + Thread.currentThread()+"时间:"+ System.currentTimeMillis());
    }
    @Subscribe(threadMode = ThreadMode.Async)
    public void onMainMessage3(MainMessage msg) {
        Log.e(TAG,"AsyncThread接收到消息"+msg.getMsg());
        Log.e(TAG, "onAsyncThread returned: " + Thread.currentThread()+"时间:"+ System.currentTimeMillis());
    }
    @Subscribe(threadMode = ThreadMode.PostThread)
    public void onMainMessage4(MainMessage msg) {
        Log.e(TAG,"PostThread接收到消息"+msg.getMsg());
        Log.e(TAG, "onPostThread returned: " + Thread.currentThread()+"时间:"+ System.currentTimeMillis());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
