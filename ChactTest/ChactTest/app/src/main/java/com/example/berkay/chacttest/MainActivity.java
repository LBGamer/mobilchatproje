package com.example.berkay.chacttest;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar mToolBar;

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionPagerAdapter;
    private DatabaseReference mUserRef;

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        mToolBar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Dizzy Bird");

        if (mAuth.getCurrentUser() != null) {

            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        }
        //Tabs
        mViewPager=findViewById(R.id.tabPager);
        mSectionPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionPagerAdapter);

        mTabLayout=findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public void onStart() {

        FirebaseUser currentUser = mAuth.getCurrentUser();
        super.onStart();
        if(currentUser== null){
        sendToStart();

        }else{
            mUserRef.child("online").setValue("true");
        }
    }


    @Override
    protected void onStop() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        super.onStop();
        if(currentUser!=null){
            mUserRef.child("online").setValue(ServerValue.TIMESTAMP);
        }

    }

    private void sendToStart() {
        Intent startIntern = new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntern);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.main_logout_btn){

            FirebaseAuth.getInstance().signOut();
            sendToStart();

        }
        if(item.getItemId()== R.id.main_setting_btn){

            Intent settingIntent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(settingIntent);
        }
        if(item.getItemId()==R.id.main_all_btn){
            Intent settingsIntent = new Intent(MainActivity.this,UserActivity.class);
            startActivity(settingsIntent);

        }








        return true;
    }
}
