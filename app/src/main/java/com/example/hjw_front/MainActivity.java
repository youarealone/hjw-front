package com.example.hjw_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.hjw_front.fragments.AccompanyFragment;
import com.example.hjw_front.fragments.BookmarkFragment;
import com.example.hjw_front.fragments.DiscountFragment;
import com.example.hjw_front.fragments.HomeFragment;
import com.example.hjw_front.fragments.LocationFragment;
import com.example.hjw_front.fragments.ScheduleFragment;
import com.example.hjw_front.fragments.SosFragment;
import com.example.hjw_front.utils.FragmentChanger;
import com.example.hjw_front.utils.PermissionChecker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.hjw_front.utils.RequestCode.RC_GOOGLE_LOGIN;

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

        Button btn_bk = findViewById(R.id.menu_bookmark);
        btn_bk.setOnClickListener(view -> fragmentChanger.changeFragment(new BookmarkFragment()));

        findViewById(R.id.menu_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChanger.changeFragment(new AccompanyFragment());
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.menu_discount:
                    fragmentChanger.changeFragment(new DiscountFragment());
                    break;

                case R.id.menu_location:
                    fragmentChanger.changeFragment(new LocationFragment());
                    break;

                case R.id.menu_home:
                    fragmentChanger.changeFragment(new HomeFragment());
                    break;

                case R.id.menu_schedule:
                    fragmentChanger.changeFragment(new ScheduleFragment());
                    break;

                case R.id.menu_sos:
                    fragmentChanger.changeFragment(new SosFragment());
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
        if (currentUser == null) {
            Intent intent = new Intent(getApplicationContext(), GoogleLoginActivity.class);
            startActivityForResult(intent, RC_GOOGLE_LOGIN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_GOOGLE_LOGIN) {
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            if (currentUser == null) {
                Intent intent = new Intent(getApplicationContext(), GoogleLoginActivity.class);
                startActivityForResult(intent, RC_GOOGLE_LOGIN);
            }
        }
    }
}