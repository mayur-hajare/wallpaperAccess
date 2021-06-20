package com.myur.wallpaperaccess;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    ImageView menuIcon;
    LinearLayout contentView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RecyclerView recyclerView, topRecycleView;

    EditText searchEt;
    ImageView SearchIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content_view);
        drawerLayout = findViewById(R.id.LayoutDrawable);
        navigationView = findViewById(R.id.navigation_view);

        navigationDrawer();
        //Navigation drawer profile
        View headerView = navigationView.getHeaderView(0);
        ImageView appLogo = headerView.findViewById(R.id.app_Image);

        //Search
        searchEt = findViewById(R.id.searchEv);
        SearchIv = findViewById(R.id.search_image);
        SearchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Search Button Clicked", Toast.LENGTH_SHORT).show();

            }
        });


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.

        setContentView(R.layout.activity_main);
        //this will bind your MainActivity.class file with activity_main.


    }

    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.action_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(getApplicationContext(), "Search Button Clicked", Toast.LENGTH_SHORT).show();
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                    Toast.makeText(getApplicationContext(), " Button Clicked", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), " Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        //anim Nav
        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offScale = 1 - diffScaledOffset;
                contentView.setScaleX(offScale);
                contentView.setScaleY(offScale);

                //translate
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);


            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_home:
                Toast.makeText(getApplicationContext(), "Home Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_treading:
                Toast.makeText(getApplicationContext(), "Tred Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_mostview:
                Toast.makeText(getApplicationContext(), "Most View Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_LogOut:
                Toast.makeText(getApplicationContext(), "LogOut Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_AboutUs:
                Toast.makeText(getApplicationContext(), "About Us Clicked", Toast.LENGTH_SHORT).show();
                break;


        }
        return true;
    }
}