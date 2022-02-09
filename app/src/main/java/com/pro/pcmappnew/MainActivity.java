package com.pro.pcmappnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.pro.pcmappnew.fragment.AboutFragment;
import com.pro.pcmappnew.fragment.HomeFragment;
import com.pro.pcmappnew.fragment.ItemListFragment;
import com.pro.pcmappnew.fragment.JoinFragment;
import com.pro.pcmappnew.fragment.OrderFragment;

public  class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth firebaseAuth;
    private DrawerLayout drawer;
    private ImageView txtview_edit;
        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            View headerview=navigationView.getHeaderView(0);
            txtview_edit = (ImageView)headerview.findViewById(R.id.userprofile);

            txtview_edit.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick (View v){
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);

                }
            });
        }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            switch (id){


                case R.id.action_logout:
                    firebaseAuth.signOut();
                    startActivity(new Intent(this, LoginActivity.class));
                break;

                case R.id.action_exit:
                    finish();
                    break;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            // Handle navigation view item clicks here.
            switch(item.getItemId()){

                case R.id.navigation_home :
                    HomeFragment homeFragment = new HomeFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment, homeFragment).commit();
                    break;
                case R.id.navigation_itemList :
                    Intent intent = new Intent(MainActivity.this, MarketActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_order:
                    OrderFragment orderFragment = new OrderFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment, orderFragment).commit();
                    break;
                case R.id.navigation_joinus:
                    JoinFragment joinFragment = new JoinFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment, joinFragment).commit();
                    break;
                case R.id.navigation_about:
                    AboutFragment aboutFragment = new AboutFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment, aboutFragment).commit();
                    break;

            }
            //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
}