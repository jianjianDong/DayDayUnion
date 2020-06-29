package com.example.saveunion.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.saveunion.R;
import com.example.saveunion.base.BaseFragment;
import com.example.saveunion.ui.fragment.CommandFragment;
import com.example.saveunion.ui.fragment.HomeFragment;
import com.example.saveunion.ui.fragment.RedPacketFragment;
import com.example.saveunion.ui.fragment.SearchFragment;
import com.example.saveunion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private CommandFragment commandFragment;
    private RedPacketFragment redPacketFragment;
    private SearchFragment searchFragment;
    private FragmentManager supportFragmentManager;

//    @BindView(R.id.main_page_container)
//    FrameLayout mainPageContainer;
    @BindView(R.id.nav_view)
    public BottomNavigationView mNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mNavView = findViewById(R.id.nav_view);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
        initFragment();
        mNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        LogUtils.d(MainActivity.class, "home");
                        switchFragment(homeFragment);
                        break;
                    case R.id.navigation_search:
                        LogUtils.d(MainActivity.class, "search");
                        switchFragment(searchFragment);
                        break;
                    case R.id.navigation_select:
                        LogUtils.d(MainActivity.class, "select");
                        switchFragment(commandFragment);
                        break;
                    case R.id.navigation_special:
                        LogUtils.d(MainActivity.class,"special");
                        switchFragment(redPacketFragment);
                        break;
                }
                return true;
            }
        });

    }


    private void initFragment(){
        homeFragment = new HomeFragment();
        commandFragment = new CommandFragment();
        redPacketFragment = new RedPacketFragment();
        searchFragment = new SearchFragment();
        supportFragmentManager = getSupportFragmentManager();
        switchFragment(homeFragment);
    }

    private void switchFragment(BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_page_container, fragment);
        fragmentTransaction.commit();
    }


}