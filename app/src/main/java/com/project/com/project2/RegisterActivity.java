package com.project.com.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        anhXa();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        dbsqLlite = new DBSQLlite(this);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = true;
                if (ed_name.length() == 0) {
                    ed_name.requestFocus();
                    check = false;
                }
                if (ed_password.length() == 0) {
                    ed_password.requestFocus();
                    check = false;
                }
                if (ed_email.length() == 0) {
                    ed_email.requestFocus();
                    check = false;
                }
                if (!check) {
                    showError("Nhập đậy đủ thông tin");
                } else if (createUser())
                    showAlert("Đăng kí thành công!", true);
                else
                    showAlert("Đăng kí thất bại!", false);
            }
        });
    }

    private void showError(final String messeage) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(messeage);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void showAlert(final String messeage, final boolean check) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
        builder1.setMessage(messeage);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (check) {
                    Intent data = new Intent();
                    data.putExtra("EMAIL", ed_email.getText().toString());
                    data.putExtra("PASSWORD", ed_password.getText().toString());
                    setResult(RESULT_OK, data);
                    finish();
                }

            }
        });
        AlertDialog alertDialog = builder1.create();
        alertDialog.show();
    }

    public void anhXa() {
        btnCreate = (Button) findViewById(R.id.btnCreate);
        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_password = (EditText) findViewById(R.id.ed_password);
        rd_group = (RadioGroup) findViewById(R.id.rd_group);
        male = (RadioButton) findViewById(R.id.male);
        famale = (RadioButton) findViewById(R.id.famale);
        male.setChecked(true);

    }

    private String getSex() {
        final String[] sex = {"Male"};
        rd_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.male)
                    sex[0] = "Male";
                else
                    sex[0] = "Famale";
            }
        });
        return sex[0];
    }

    private boolean createUser() {
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
        super.finish();
    }
}
