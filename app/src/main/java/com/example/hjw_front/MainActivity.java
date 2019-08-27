package com.example.hjw_front;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.hjw_front.utils.FragmentChanger ;
import com.example.hjw_front.utils.PermissionChecker;

public class MainActivity extends AppCompatActivity {
    FragmentChanger fragmentChanger = null;

    public MainActivity() {
        this.fragmentChanger = new FragmentChanger(getSupportFragmentManager());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        PermissionChecker permissionChecker = new PermissionChecker(this);
        permissionChecker.permissionCheck();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        fragmentChanger.changeFragment( new HomeFragment());

        ImageView logo = findViewById(R.id.iv_logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChanger.changeFragment(new HomeFragment());
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_discount:
                        fragmentChanger.changeFragment(new DiscountFragment());
                        break;

                    case R.id.menu_location:
                        fragmentChanger.changeFragment(new LocationFragment());
                        break;

                    case R.id.menu_schedule:
                        fragmentChanger.changeFragment(new ScheduleFragment());
                        break;

                    case R.id.menu_sos:
                        fragmentChanger.changeFragment(new SosFragment());
                        break;

                    case R.id.menu_login:
                        fragmentChanger.changeFragment(new LoginFragment());
                        break;

                    default:
                        break;
                }

                return true;
            }
        });

    }
}