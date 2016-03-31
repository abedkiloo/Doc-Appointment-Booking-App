package com.unifysoftech.abedx.medicare;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.unifysoftech.abedxh.docbook.fragments.Diabetes;
import com.unifysoftech.abedxh.docbook.fragments.FoodPoisoning;
import com.unifysoftech.abedxh.docbook.fragments.Malaria;
import com.unifysoftech.abedxh.docbook.fragments.PatientLogin;
import com.unifysoftech.abedxh.docbook.fragments.Register;
import com.unifysoftech.abedxh.docbook.fragments.Respiratory;
import com.unifysoftech.abedxh.docbook.fragments.Stroke_Heart_Disease;

public class DoctorCatergoriesFragment extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static FragmentManager fragmentManager;
    public static  DrawerLayout drawerLayout;
    public static NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_catergories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);


        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.catergories_frame, new Diabetes());
         drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(getIntent().hasExtra("Fragment"))
        {
            switch (getIntent().getStringExtra("Fragment")){
                case  "Malaria":
                    fragmentManager.beginTransaction().replace(R.id.catergories_frame, new Malaria()).commit();
                    break;
                case "Register":
                    fragmentManager.beginTransaction().replace(R.id.catergories_frame,new Register()).commit();
            }
        }
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
        getMenuInflater().inflate(R.menu.doctor_catergories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Diabetes) {
            fragmentManager.beginTransaction().replace(R.id.catergories_frame, new Diabetes()).commit();
        } else if (id == R.id.FoodPoisoning) {
            fragmentManager.beginTransaction().replace(R.id.catergories_frame, new FoodPoisoning()).commit();
        } else if (id == R.id.Malaria) {
            fragmentManager.beginTransaction().replace(R.id.catergories_frame, new Malaria()).commit();
        } else if (id == R.id.Respiratory) {
            fragmentManager.beginTransaction().replace(R.id.catergories_frame, new Respiratory()).commit();
        } else if (id == R.id.Stroke_Heart_Diseases) {
            fragmentManager.beginTransaction().replace(R.id.catergories_frame, new Stroke_Heart_Disease()).commit();
        } else if (id == R.id.Login) {
            fragmentManager.beginTransaction().replace(R.id.catergories_frame, new PatientLogin()).commit();
        }
        else if (id == R.id.Register) {
            fragmentManager.beginTransaction().replace(R.id.catergories_frame, new Register()).commit();
        }
        else if (id == R.id.Pharmacies) {
            fragmentManager.beginTransaction().replace(R.id.catergories_frame, new Pharmacies()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    public void goToFragment(Fragment fragment,String title)
//    {
//        android.support.v4.app.FragmentManager mFragmentManager=getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction transaction=mFragmentManager.beginTransaction();
//        drawerLayout.closeDrawer(GravityCompat.START);
//        transaction.replace(R.id.catergories_frame,fragment,"The Fragment").addToBackStack(null).commit();
//        setTitle("Tile");
//
//
//
//    }
}
