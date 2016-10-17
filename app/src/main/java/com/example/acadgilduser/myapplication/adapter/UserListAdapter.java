package com.example.acadgilduser.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acadgilduser.myapplication.R;
import com.example.acadgilduser.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<User> allUsers = new ArrayList<>();

    public UserListAdapter(Context context, List<User> all) {
        this.mContext = context;
        this.allUsers.addAll(all);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User u = allUsers.get(position);
        holder.email.setText(u.getEmail() + u.getId());
        holder.name.setText(u.getName());
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.email)
        TextView email;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
