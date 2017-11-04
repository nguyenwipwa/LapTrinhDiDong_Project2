package com.project.com.project2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.project.com.project2.database.DBSQLlite;
import com.project.com.project2.model.MyProgress;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCreate, btnLogin;
    EditText ed_username, ed_password;
    MyProgress myProgress;
    DBSQLlite dbsqLlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        dbsqLlite = new DBSQLlite(this);
    }

    private void init() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        ed_username = (EditText) findViewById(R.id.username);
        ed_password = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCreate = (Button) findViewById(R.id.btnCreate);

    }

    private void login(String username, String password) {
        if (checklogin(username, password)) {
            myProgress = new MyProgress(this, "Loging...");
//            myProgress.show();
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        this.wait(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    // do the thing that takes a long time
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            myProgress.dismiss();
//
//
//
//                        }
//                    });
//                }
//            }).start();
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }
    }

    private void startActivity(Activity activity, Class<?> activityClass) {
        Intent intent = new Intent(activity, activityClass);
        startActivity(intent);
    }

    private boolean checklogin(String username, String password) {

        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnLogin:
                login(ed_username.getText().toString(), ed_password.getText().toString());
                break;
            case R.id.btnCreate:
                startActivity(MainActivity.this, RegisterActivity.class);
                break;
        }
    }
}
