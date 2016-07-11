EventBus3.0学习之二
在MainActivity中注册EventBus, 在SecondActivity发送事件
SecondActivity发出的消息可以被这四种方式接收到,前提是接受的参数类型必须与SecondActivity中发送的参数类型一致
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

    四种方式接收到的时间在结果中打印出来如下:
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
    可以得出:在Activity中注册以后,如果在  SecondActivity中发出事件消息,那么可以在所有的地方(MainActivity,SecondActivity,ThirdActivity,ForthActivity等等 )接受到这个消息,
(前提是Activity必须注册并且接收的事件消息参数类型与发出事件消息类型一致)



