package com.example.hjw_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hjw_front.utils.FragmentChanger;
import com.example.hjw_front.utils.PermissionChecker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FragmentChanger fragmentChanger = null;

    //파이어베이스 데이터베이스 추가
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    public MainActivity() {
        this.fragmentChanger = new FragmentChanger(getSupportFragmentManager());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        PermissionChecker permissionChecker = new PermissionChecker(this);
        permissionChecker.permissionCheck();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        fragmentChanger.changeFragment(new HomeFragment());

        ImageView logo = findViewById(R.id.iv_logo);
        logo.setOnClickListener(v -> fragmentChanger.changeFragment(new HomeFragment()));

        Button btn_bk = findViewById(R.id.menu_bookmark);
        btn_bk.setOnClickListener(view -> fragmentChanger.changeFragment(new BookmarkFragment()));

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


                default:
                    break;
            }

            return true;
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d(getClass().getName(), currentUser.getUid());
        } else {
            Log.d(getClass().getName(), "currentUser is null");
            Intent intent = new Intent(getApplicationContext(), GoogleLoginActivity.class);
            startActivityForResult(intent, 1001);
        }
    }
}