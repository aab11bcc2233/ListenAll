package com.madaohtp.listenall

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer()

        if (mediaPlayer != null) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mediaPlayer?.setAudioAttributes(
                        AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                                .build()
                )
            } else {
                mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
            }

            mediaPlayer?.setDataSource("http://192.168.2.120:8080/xxx.mp3")

            mediaPlayer?.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
                override fun onPrepared(mp: MediaPlayer?) {
                    toast("准备成功")
                    mediaPlayer?.duration
                    mediaPlayer?.start()
                }
            })

            mediaPlayer?.prepareAsync()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }

    }
}
