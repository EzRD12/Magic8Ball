package com.example.ezrodriguez.magic8ball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.lets_play).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,GameActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed (){
        if (i>1) {
            Toast.makeText(this,"Prueba",Toast.LENGTH_SHORT).show();
        }
    }

}
