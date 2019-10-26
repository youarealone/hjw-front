package com.example.hjw_front;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hjw_front.adapters.FragmentPagerItemAdapter;
import com.example.hjw_front.fragments.AccompanyFragment;
import com.example.hjw_front.fragments.BookmarkFragment;
import com.example.hjw_front.fragments.HomeFragment;
import com.example.hjw_front.fragments.LocationFragment;
import com.example.hjw_front.fragments.PlaceFragment;
import com.example.hjw_front.fragments.ScheduleFragment;
import com.example.hjw_front.fragments.SearchFragment;
import com.example.hjw_front.fragments.SosFragment;
import com.example.hjw_front.utils.FragmentChanger;
import com.example.hjw_front.utils.PermissionChecker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.hjw_front.utils.RequestCode.RC_GOOGLE_LOGIN;

public class MainActivity extends AppCompatActivity {
    FragmentChanger fragmentChanger = null;
    FragmentPagerItemAdapter adapter;
    ViewPager viewPager;

    //파이어베이스 데이터베이스 추가
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    public MainActivity() {
        this.fragmentChanger = new FragmentChanger(getSupportFragmentManager());
        this.adapter = new FragmentPagerItemAdapter(getSupportFragmentManager());
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

        findViewById(R.id.menu_search).setOnClickListener(view -> fragmentChanger.changeFragment(new SearchFragment()));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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

    private void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new HomeFragment(), "홈");
        adapter.addFragment(new PlaceFragment(), "할인받안?");
        adapter.addFragment(new LocationFragment(), "병원어딘?");
        adapter.addFragment(new AccompanyFragment(), "고치가게!");
        adapter.addFragment(new ScheduleFragment(), "오늘뭐할거?");
        adapter.addFragment(new SosFragment(), "SOS!");
        adapter.addFragment(new BookmarkFragment(), "어디좋안?");
//        adapter.addFragment(new SosFragment(), "마이혼자완");
        viewPager.setAdapter(adapter);
    }
}