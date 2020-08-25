package com.ihfazh.latihanfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private homeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mHomeFragment = new homeFragment();
        Fragment fragment = mFragmentManager.findFragmentByTag(homeFragment.class.getSimpleName());

        if (!(fragment instanceof homeFragment)){
            mFragmentManager.beginTransaction()
                    .add(R.id.frame_container, mHomeFragment, homeFragment.class.getSimpleName())
                    .commit();

        }

    }
}