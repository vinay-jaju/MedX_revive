package com.example.android.medx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.text.Layout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Camera camera;
    UserLocalStore userLocalStore;
    private Camera.Parameters parameters;
    boolean isFlashLightOn = false;
    TextView navUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userLocalStore = new UserLocalStore(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Sms.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        User user = userLocalStore.getLoggedInUser();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


       // welcome_text.setText(user.username);
        navigationView.setNavigationItemSelectedListener(this);
//        String username=user.username;
//        navUsername= (TextView)findViewById(R.id.welcome_text);
//        navUsername.setText("Welcome, " + username);
        displayView(R.id.nav_home);
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
        if (id == R.id.action_constant_flash) {
            if (isFlashSupported()) {
                camera = Camera.open();
                parameters = camera.getParameters();

                if(isFlashLightOn){
                    // flashLightButton.setImageResource(R.drawable.flashlight_off);
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(parameters);
                    camera.stopPreview();
                    isFlashLightOn = false;
                }else{
                    // flashLightButton.setImageResource(R.drawable.flashlight_on);
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(parameters);
                    camera.startPreview();
                    isFlashLightOn = true;
                }

            }
            else {
                Toast.makeText(this, "No FlashLight supported", Toast.LENGTH_SHORT).show();
            }




            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_home) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        displayView(item.getItemId());
        return true;

    }

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_home:
                fragment = new Home();
                title  = "Revive";
                break;

            case R.id.nav_maps:
                startActivity(new Intent(this,MapsActivity.class));
                break;

            case R.id.nav_vids:
                startActivity(new Intent(this,YouTubeList.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(this,AboutUs.class));
                break;

            case R.id.nav_logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                Intent loginIntent = new Intent(MainActivity.this, Login.class);
                startActivity(loginIntent);
                break;


        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    private void showNoFlashAlert() {
        new AlertDialog.Builder(this)
                .setMessage("Your device hardware does not support flashlight!")
                .setIcon(android.R.drawable.ic_dialog_alert).setTitle("Error")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).show();
    }

   private boolean isFlashSupported() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }
    @Override
    protected void onDestroy() {
        if(camera != null){
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        super.onDestroy();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            displayUserDetails();
        }
    }

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        Toast.makeText(MainActivity.this,user.username.toString(),Toast.LENGTH_SHORT).show();

        //welcome_text.setText("Welcome, "+user.username);
//        etUsername.setText(user.username);
//        etName.setText(user.name);
//        etAge.setText(user.age + "");
//
}

}
