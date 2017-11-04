package com.project.com.project2.model;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by MyPC on 04/11/2017.
 */

public class MyProgress extends ProgressDialog {
    public MyProgress(Context context, String title) {
        super(context);
        this.setTitle(title);
    }
}
