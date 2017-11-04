package com.project.com.project2;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.project.com.project2.database.DBSQLlite;
import com.project.com.project2.model.User;

public class RegisterActivity extends AppCompatActivity {

    DBSQLlite dbsqLlite;
    Button btnCreate;
    EditText ed_name, ed_email, ed_password;
    RadioGroup rd_group;
    RadioButton male, famale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        anhXa();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        dbsqLlite = new DBSQLlite(this);
        action();
    }
    public void action(){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
//                Intent intent = new Intent(RegisterActivity.this, Main2Activity.class);
//                startActivity(intent);
                finish();
            }
        });
    }


    public void  anhXa(){
        btnCreate = (Button) findViewById(R.id.btnCreate);
        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_password = (EditText) findViewById(R.id.ed_password);
        rd_group = (RadioGroup) findViewById(R.id.rd_group);
        male = (RadioButton) findViewById(R.id.male);
        famale = (RadioButton) findViewById(R.id.famale);

    }
    private String getSex(){
        final String[] sex = {""};
        rd_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
               if(i == R.id.male)
                   sex[0] = "Male";
                else
                    sex[0] = "Famale";
            }
        });
        return sex[0];
    }
    private boolean createUser(){
        User user = new User();
        user.setName(ed_name.getText().toString());
        user.setEmail(ed_email.getText().toString());
        user.setPassword(ed_password.getText().toString());
        user.setAddress("");
        user.setNumber("");
        user.setSex(getSex());
        return dbsqLlite.addUser(user);
    }

    @Override
    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }
}
