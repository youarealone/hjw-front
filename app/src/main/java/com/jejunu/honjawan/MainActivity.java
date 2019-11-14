package com.jejunu.honjawan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.jejunu.honjawan.adapters.FragmentPagerItemAdapter;
import com.jejunu.honjawan.fragments.AccompanyFragment;
import com.jejunu.honjawan.fragments.BookmarkFragment;
import com.jejunu.honjawan.fragments.HomeFragment;
import com.jejunu.honjawan.fragments.LocationFragment;
import com.jejunu.honjawan.fragments.PlaceFragment;
import com.jejunu.honjawan.fragments.ScheduleFragment;
import com.jejunu.honjawan.fragments.SearchFragment;
import com.jejunu.honjawan.fragments.SosFragment;
import com.jejunu.honjawan.utils.FragmentChanger;
import com.jejunu.honjawan.utils.PermissionChecker;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import static com.jejunu.honjawan.utils.RequestCode.RC_GOOGLE_LOGIN;

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

        initFullScreen();
        initToolbar();

        PermissionChecker permissionChecker = new PermissionChecker(this);
        permissionChecker.permissionCheck();

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

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initFullScreen() {
        View decorView = getWindow().getDecorView();
        int uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
            uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility( uiOption );
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