package com.example.playvideotest

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //经raw目录下的video.mp4文件解析成了一个Uri对象，这里使用的写法是Android要求的固定写法
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        //然后调用VideoView的setVideoURI()方法将刚才解析出来的Uri对象传入，这样VideoView就初始化完成了
        videoView.setVideoURI(uri)

        //开始播放
        play.setOnClickListener {
            //如果当前没有正在播放视频，则调用start()方法开始播放
            if (!videoView.isPlaying) {
                videoView.start()
                Log.d("MainActivity", "video is playing")
            }
        }

        //暂停播放
        pause.setOnClickListener {
            //如果当前视频正在播放，则调用pause()方法暂停播放
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        //重新播放
        replay.setOnClickListener {
            //如果当前视频正在播放，则调用resume()方法从头播放视频
            if (videoView.isPlaying) {
                videoView.resume()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //调用suspend()方法，将VideoView所占用的资源释放掉
        videoView.suspend()
    }

}