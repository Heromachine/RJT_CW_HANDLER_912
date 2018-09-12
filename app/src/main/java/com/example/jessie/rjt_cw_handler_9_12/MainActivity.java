package com.example.jessie.rjt_cw_handler_9_12;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    ProgressBar progressBar;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread th= new Thread()
                {
                    @Override
                    public void run()
                    {
                        for(int i = 0; i < 100; i++)
                        {
                            final int val = i;
                            String s = "Progress...";
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            handler.post(new Runnable(){
                                @Override
                                public void run()
                                {
                                    textView.setText("This is the progress: "+ val);
                                    progressBar.setProgress(val);
                                }
                            });
                        }

                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Toast.makeText(MainActivity.this, "Toast Progress", Toast.LENGTH_LONG).show();
                            }
                        });

                        handler.postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                textView.setText("This is the Post Delayed");
                            }
                        }, 10000);
                        super.run();
                    }
                };
                th.start();
            }
        });
    }
}
