package com.project.com.project2;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.project.com.project2.database.DBSQLlite;
import com.project.com.project2.model.MyProgress;
import com.project.com.project2.model.User;

import static android.provider.Telephony.Carriers.PASSWORD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreate, btnLogin;
    private EditText ed_email, ed_password;
    private MyProgress myProgress;
    private DBSQLlite dbsqLlite;
    private CheckBox checkBox;
    private static final String SPF_NAME = "vidslogin"; //  <--- Add this
    private static final String USERNAME = "username";  //  <--- To save username
    private static final String PASSWORD = "password";  //  <--- To save password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        dbsqLlite = new DBSQLlite(this);
    }

    private void init() {
        dbsqLlite = new DBSQLlite(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        ed_email = (EditText) findViewById(R.id.email);
        ed_password = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setChecked(false);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        SharedPreferences loginPreferences = getSharedPreferences(SPF_NAME,
                Context.MODE_PRIVATE);
        ed_email.setText(loginPreferences.getString(USERNAME, ""));
        ed_password.setText(loginPreferences.getString(PASSWORD, ""));
        if (login() != null) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }
    }

    private void showAlert(final String messeage) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(messeage);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    public User login() {
        return dbsqLlite.getLogin();
    }

    public void save(String strUserName, String strPassword) {
        if (checkBox.isChecked()) {
            SharedPreferences loginPreferences = getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
            loginPreferences.edit().putString(USERNAME, strUserName).putString(PASSWORD, strPassword).commit();
        } else {
            SharedPreferences loginPreferences = getSharedPreferences(SPF_NAME, Context.MODE_PRIVATE);
            loginPreferences.edit().clear().commit();
        }
    }

    public void notifyThis(String title, String message) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(this);
        b.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon_email)
                .setTicker("Register account completed!")
                .setContentTitle(title)
                .setContentText(message)
                .setContentInfo("REGISTER");

        Intent startMyActivity = new Intent(this, MainActivity.class);
        PendingIntent myIntent = PendingIntent.getActivity(this, 1, startMyActivity, 0);
        b.setContentIntent(myIntent);

        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, b.build());
    }

    private void login(String email, String password) {
        if (checklogin(email, password)) {
            save(email, password);
            dbsqLlite.addLogin(dbsqLlite.getUser(email, password).getId());
//            myProgress = new MyProgress(this, "Loging...");
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        } else {
            showAlert("Login fail!");
        }
    }

    private void startActivity(Activity activity, Class<?> activityClass) {
        Intent intent = new Intent(activity, activityClass);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            ed_email.setText(data.getStringExtra("EMAIL"));
            ed_password.setText(data.getStringExtra("PASSWORD"));
            notifyThis("Notification", "Successfully registered account: " + data.getStringExtra("EMAIL"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean checklogin(String email, String password) {
        return dbsqLlite.getUser(email, password) != null;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnLogin:
                login(ed_email.getText().toString(), ed_password.getText().toString());
                break;
            case R.id.btnCreate:
                startActivity(MainActivity.this, RegisterActivity.class);
                break;
        }
    }
}


