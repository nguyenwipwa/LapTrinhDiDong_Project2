package com.project.com.project2.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.com.project2.R;
import com.project.com.project2.database.DBSQLlite;
import com.project.com.project2.model.User;

import java.util.List;

/**
 * Created by MyPC on 04/11/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    private int resource;
    private List<User> list;
    private DBSQLlite dbsqLlite;
    public UserAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
        this.dbsqLlite = new DBSQLlite(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView name_user = convertView.findViewById(R.id.name_user);
        ImageView btnDelete = convertView.findViewById(R.id.btndelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserAdapter.this.notifyDataSetChanged();
                dbsqLlite.deleteUser(list.get(position).getId());
                list.remove(position);

            }
        });
        name_user.setText(list.get(position).getName());
        return convertView;
    }
}
