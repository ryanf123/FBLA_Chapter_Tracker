package com.example.fblaappv01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;

    //private WebView webView; //sets up a private webview variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to tell our app that we want to use our toolbar as our actionbar, because we removed the default actionbar
        Toolbar toolbar = findViewById(R.id.toolbar); //creates a toolbar variable that is linked to the toolbar we created
        setSupportActionBar(toolbar); //sets our toobar as our actionbar

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nagivation_drawer_open, R.string.nagivation_drawer_close);
        //creating a new actionbardrawertoggle named toggle, with the context as "this", passing the variable "drawer" and "toolbar" becuase these are the two vairbales that will be connected and synchronized
        //then we pass the two string resources we created
        drawer.addDrawerListener(toggle); //then we take our drawer variable and call a drawer listener and pass this toggle variable
        toggle.syncState(); //then we call syncstate to take care of rotating our hamburger icon to get rid of the drawer itself




        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewsFragment()).commit(); //paste herre so that when we start our activity we open our message fragment immediately
            navigationView.setCheckedItem(R.id.nav_news);
    }

        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.fragment_container, new MeetingsFragment());
        //fragmentTransaction.commit();



    }


    @Override
    public void onBackPressed() { //calls the previous web history item upon the user pressing the back button

        WebView webView = findViewById(R.id.webView);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_about: //define what we want to do when we click this item
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit(); //takes care of placing our fragment in the fragment container frame layout
                break;
            case R.id.nav_news: //define what we want to do when we click this item
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewsFragment()).commit(); //takes care of placing our fragment in the fragment container frame layout
                break;
            case R.id.nav_calender: //define what we want to do when we click this item
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalenderFragment()).commit(); //takes care of placing our fragment in the fragment container frame layout
                break;
            case R.id.nav_events: //define what we want to do when we click this item
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventsFragment()).commit(); //takes care of placing our fragment in the fragment container frame layout
                break;
            case R.id.nav_contact: //define what we want to do when we click this item
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).commit(); //takes care of placing our fragment in the fragment container frame layout
                break;
            case R.id.nav_meeting: //define what we want to do when we click this item
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MeetingsFragment()).commit(); //takes care of placing our fragment in the fragment container frame layout
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); //this si to simply see that we clicked this item, its the toast message
                try { //try/catch block to prompt the user to share the app
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "FBLA App!");
                    String shareMessage = "\nCheck out FBLA App! I'm loving it so far!\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "Thanks for sharing!!!"));
                } catch (Exception e) {
                    //e.toString();
                }
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}


