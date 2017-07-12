package com.onetwopunch.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt_LoginMenu ;
    Button bt_JoinMenu;
    Button bt_introducer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_JoinMenu =(Button) findViewById(R.id.bt_JoinMenu);
        bt_LoginMenu=(Button) findViewById(R.id.bt_LoginMenu);
        bt_introducer = (Button) findViewById(R.id.bt_introducer) ;

        bt_JoinMenu.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,JoinActivity.class);
                startActivity(intent);
            }
        });

        bt_LoginMenu.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        bt_introducer.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShowDeveloperActivity.class);
                startActivity(intent);
            }
        });


    }
}
