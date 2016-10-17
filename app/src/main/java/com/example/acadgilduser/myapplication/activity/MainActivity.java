package com.example.acadgilduser.myapplication.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.activeandroid.ActiveAndroid;
import com.example.acadgilduser.myapplication.R;
import com.example.acadgilduser.myapplication.adapter.UserListAdapter;
import com.example.acadgilduser.myapplication.api.UserApi;
import com.example.acadgilduser.myapplication.model.User;
import com.example.acadgilduser.myapplication.utils.RetroFit;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.content_main)
    RelativeLayout contentMain;
    @Bind(R.id.fab2)
    FloatingActionButton fab2;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.my_recycler_view)
    RecyclerView myRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        myRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.fab2, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab2:
                Log.e(TAG, "onClick: " + User.all().toString());
                mAdapter = new UserListAdapter(this, User.all());
                myRecyclerView.setAdapter(mAdapter);
                break;
            case R.id.fab:
                final Retrofit retrofit = new RetroFit(MainActivity.this).getInstance();
                UserApi api = retrofit.create(UserApi.class);
                Call<ArrayList<User>> call = api.getAllUsers();
                call.enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {

                        Log.e(TAG, "onResponse: " + response.body().toString());
                        ActiveAndroid.beginTransaction();
                        try {
                            for (User u : response.body()) {
                                u.save();
                            }
                            ActiveAndroid.setTransactionSuccessful();
                        } finally {
                            ActiveAndroid.endTransaction();
                        }

                    }

                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
                break;
        }
    }
}
