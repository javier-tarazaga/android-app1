package com.tinygrip;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragment(Fragments.PROFILE);


        findViewById(R.id.btn_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showFragment(Fragments.PROFILE);
            }
        });
        findViewById(R.id.btn_discovery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(Fragments.DISCOVER);
            }
        });
        findViewById(R.id.btn_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(Fragments.MAP);
            }
        });
        findViewById(R.id.btn_offline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(Fragments.OFFLINE);
            }
        });
        findViewById(R.id.btn_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(Fragments.SETTINGS);
            }
        });
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

    private void showFragment(Fragments fragment){
       Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(fragment.toString());
        if(fragmentByTag == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentsFactory.getFragment(fragment), fragment.toString()).commit();
        } else {
            Log.d(TAG, "This fragment is already on top");
        }
    }

}
