package com.project.com.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.com.project2.model.User;

public class ShowUserActivity extends AppCompatActivity {
    private TextView ed_name, ed_email, ed_address, ed_number, ed_sex;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        anhXa();
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void anhXa(){
        button = (Button) findViewById(R.id.button4);
        ed_name = (TextView) findViewById(R.id.name_user);
        ed_email = (TextView) findViewById(R.id.ed_email);
        ed_address = (TextView) findViewById(R.id.address);
        ed_number = (TextView) findViewById(R.id.numberPhone);
        ed_sex = (TextView) findViewById(R.id.sex);
    }
    private void init(){
        this.setTitle("Profile");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        User user = (User) bundle.get("USER");
        ed_name.setText(user.getName());
        ed_email.setText(user.getEmail());
        ed_address.setText(user.getAddress());
        ed_number.setText(user.getNumber());
        ed_sex.setText(user.getSex());
    }
}
