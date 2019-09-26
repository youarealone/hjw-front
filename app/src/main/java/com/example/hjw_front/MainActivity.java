package com.example.hjw_front;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.hjw_front.utils.FragmentChanger;
import com.example.hjw_front.utils.PermissionChecker;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FragmentChanger fragmentChanger = null;

    //파이어베이스 데이터베이스 추가
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public MainActivity() {
        this.fragmentChanger = new FragmentChanger(getSupportFragmentManager());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionChecker permissionChecker = new PermissionChecker(this);
        permissionChecker.permissionCheck();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        fragmentChanger.changeFragment(new HomeFragment());

        ImageView logo = findViewById(R.id.iv_logo);
        logo.setOnClickListener(v -> fragmentChanger.changeFragment(new HomeFragment()));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
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

                case R.id.menu_bookmark:
                    fragmentChanger.changeFragment(new BookmarkFragment());
                    break;

                default:
                    break;
            }

            return true;
        });

    }
}