package com.app.hubert.newbieguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.listener.OnGuideChangedListener;
import com.app.hubert.guide.listener.OnLayoutInflatedListener;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighLight;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        final Button btnSimple = (Button) findViewById(R.id.btn_simple);
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewbieGuide.with(FirstActivity.this)
                        .setLabel("guide1")
//                        .setShowCounts(3)//控制次数
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLight(btnSimple)
                                .setLayoutRes(R.layout.view_guide_simple))
                        .show();
            }
        });
        final Button btnDialog = (Button) findViewById(R.id.btn_dialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewbieGuide.with(FirstActivity.this)
                        .setLabel("guide2")
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLight(btnDialog)
                                .setEverywhereCancelable(false)//是否点击任意位置消失引导页
                                .setLayoutRes(R.layout.view_guide_dialog, R.id.btn_ok)
                                .setOnLayoutInflatedListener(new OnLayoutInflatedListener() {
                                    @Override
                                    public void onLayoutInflated(View view) {
                                        TextView tv = view.findViewById(R.id.tv_text);
                                        tv.setText("this like dialog");
                                    }
                                }))
                        .show();
            }
        });

        final View anchorView = findViewById(R.id.ll_anchor);
        final Button btnAnchor = (Button) findViewById(R.id.btn_anchor);
        btnAnchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewbieGuide.with(FirstActivity.this)
                        .setLabel("anchor")
                        .anchor(anchorView)
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLight(btnAnchor, HighLight.Shape.CIRCLE, 5)
                                .setLayoutRes(R.layout.view_guide_anchor))
                        .show();
            }
        });

        final Button btnListener = findViewById(R.id.btn_listener);
        btnListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewbieGuide.with(FirstActivity.this)
                        .setLabel("listener")
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .setOnGuideChangedListener(new OnGuideChangedListener() {
                            @Override
                            public void onShowed(Controller controller) {
                                Toast.makeText(FirstActivity.this, "引导层显示", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onRemoved(Controller controller) {
                                Toast.makeText(FirstActivity.this, "引导层消失", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addGuidePage(GuidePage.newInstance()
                                .addHighLight(btnListener))
                        .show();
            }
        });

        findViewById(R.id.btn_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(FirstActivity.this);
            }
        });
    }
}
