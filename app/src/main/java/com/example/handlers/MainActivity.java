package com.example.handlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageView = (ImageView) findViewById(R.id.imageView);

        progressBar.setProgress(0);
        h1.start();
    }

    Handler c1 = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {

            int conteo = msg.getData().getInt("conteo");
            String name = msg.getData().getString("nombre");
            Log.i(null, "Entramos" + String.valueOf(conteo));
            progressBar.setProgress(conteo+1);
            textView.setText(String.valueOf(progressBar.getProgress()) +"%");

            imageView.setScaleType(ImageView.ScaleType.MATRIX);   //required
            imageView.setRotation(progressBar.getProgress()*40);
            imageView.setImageResource(R.drawable.homer);

        }
    };
    /*
    Handler c2 = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {

            Matrix matrix = new Matrix();
            imageView2.setScaleType(ImageView.ScaleType.MATRIX);   //required
            matrix.postRotate((float) progressBar.getProgress()*40, random.nextInt(10), random.nextInt(10));
            imageView2.setImageMatrix(matrix);


        }
    };

     */

    Thread h1 = new Thread(){
        @Override
        public void run() {
            try {
                for (int i = 0; i<100; i++){
                    Bundle dato = new Bundle();
                    Message message = c1.obtainMessage();
                    Thread.sleep(200);
                    dato.putString("nombre", "Un dato tipo String");
                    message.setData(dato);
                    c1.sendMessage(message);
                }
            } catch (Throwable throwable)  {
                throwable.printStackTrace();
            } finally {
                //finish();
            }
        }
    };
/*
    Thread h2 = new Thread(){
        @Override
        public void run() {
            try {
                for (int i = 0; i<100; i++){
                    Thread.sleep(200);
                    c1.sendMessage(c1.obtainMessage(i));
                }
            } catch (Throwable throwable)  {
                throwable.printStackTrace();
            } finally {
                //finish();
            }
        }
    };

 */

}