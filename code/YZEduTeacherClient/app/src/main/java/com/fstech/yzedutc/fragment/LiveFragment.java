package com.fstech.yzedutc.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fstech.yzedutc.R;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

/**
 * Created by shaoxin on 18-4-17.
 * 直播界面的Fragment
 */

public class LiveFragment extends Fragment implements View.OnClickListener {
    // 定义UI对象
    private TXLivePusher mLivePusher;
    private TXLivePushConfig mLivePushConfig;
    private TXCloudVideoView mCaptureView;
    private Button btnCameraChange;
    private Button btnPlay;
    private boolean isPush;
    private String rtmpUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    /*
    * 初始化视图
    * 无参数
    * 无返回
    * */
    private void initView() {
        mLivePusher = new TXLivePusher(getActivity());
        mLivePushConfig = new TXLivePushConfig();
        mLivePusher.setConfig(mLivePushConfig);
        btnPlay = (Button) getActivity().findViewById(R.id.btnPlay);
        btnCameraChange = (Button) getActivity().findViewById(R.id.btnCameraChange);
        btnCameraChange.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
    }

    /*
    * 初始化数据
    * 无参数
    * 无返回
    * */
    private void initData() {
        isPush = false;
        rtmpUrl = "rtmp://22280.livepush.myqcloud.com/live/22280_9600f698362d11e892905cb9018cf0d4?bizid=22280";
//        mLivePusher.startPusher(rtmpUrl);
        mCaptureView = (TXCloudVideoView) getActivity().findViewById(R.id.video_view);
        mLivePusher.setBeautyFilter(0, 3, 3, 3);
        mLivePusher.startCameraPreview(mCaptureView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCameraChange:
                mLivePusher.switchCamera();
                break;
            case R.id.btnPlay:
                setPush();
                break;
            default:
                break;
        }
    }

    private void setPush() {
        if (isPush == false) {
            isPush = true;
            mLivePusher.startCameraPreview(mCaptureView);
            mLivePusher.startPusher(rtmpUrl);
            btnPlay.setBackgroundResource(R.drawable.play_pause);
        } else {
            isPush = false;
            stopRtmpPublish();
            btnPlay.setBackgroundResource(R.drawable.play_start);
        }
    }

    //结束推流，注意做好清理工作
    public void stopRtmpPublish() {
        mLivePusher.stopCameraPreview(true); // 停止摄像头预览
        mLivePusher.stopPusher();               // 停止推流
        mLivePusher.setPushListener(null);      // 解绑 listener
        mLivePushConfig.setTouchFocus(false);   // 不能手动对焦
        mLivePusher.setConfig(mLivePushConfig);
    }

    @Override
    public void onDestroy() {
        stopRtmpPublish();
        super.onDestroy();
    }
}
