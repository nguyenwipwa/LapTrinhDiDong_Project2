package com.project.com.project2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.com.project2.R;
import com.project.com.project2.ShowUserActivity;
import com.project.com.project2.database.DBSQLlite;
import com.project.com.project2.model.User;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private List<User> list;
    private LayoutInflater inflter;
    private DBSQLlite dbsqLlite;

    public GridAdapter(Context applicationContext, List<User> list) {
        this.context = applicationContext;
        this.list = list;
        inflter = (LayoutInflater.from(applicationContext));
        this.dbsqLlite = new DBSQLlite(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.grid_user, null);
        TextView name = (TextView) view.findViewById(R.id.name_user);

        final User user = list.get(i);
        name.setText(user.getName());
        ImageView btnDelete = view.findViewById(R.id.btndelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridAdapter.this.notifyDataSetChanged();
                dbsqLlite.deleteUser(user.getId());
                list.remove(i);

            }
        });
        showUser(view, user);
        return view;
    }

    private void showUser(View convertView, final User user) {
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowUserActivity.class);
                intent.putExtra("USER", user);
                context.startActivity(intent);
            }
        });
    }
}
