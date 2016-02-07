package net.junsun.l14_thread_handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar1;
    Handler handler1;

    ProgressBar progressBar2;
    Handler handler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // method 1
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar);
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new DownloadRunnable();
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
        handler1 = new Handler();

        // method 2
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new DownloadThread();
                thread.start();
            }
        });
        handler2 = new DownloadHandler();
    }

    private class DownloadRunnable implements Runnable {
        @Override
        public void run() {
            // DON"T DO THIS - progressBar1.setProgress(50);
            try {
                for(int progress=0; progress<=100; progress+=1) {
                    final int p=progress;
                    handler1.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar1.setProgress(p);
                        }
                    });
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadThread extends Thread {
        @Override
        public void run() {
            try {
                // Message message = handler2.obtainMessage();
                for (int progress = 0; progress <= 100; progress += 1) {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("progress", progress);
                    message.setData(bundle);
                    handler2.sendMessage(message);

                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int progress = bundle.getInt("progress");
            progressBar2.setProgress(progress);
        }
    }
}
