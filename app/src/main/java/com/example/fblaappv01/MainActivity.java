package com.example.fblaappv01;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.fblaappv01.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity"; //for organizing logs
    private static final int ACTIVITY_NUM = 0;

    private Context mContext = MainActivity.this;


    private DrawerLayout drawer;


    //private WebView webView; //sets up a private webview variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // to tell our app that we want to use our toolbar as our actionbar, because we removed the default actionbar
        Toolbar toolbar = findViewById(R.id.toolbar); //creates a toolbar variable that is linked to the toolbar we created
        setSupportActionBar(toolbar); //sets our toobar as our actionbar
        Log.d(TAG, "onCreate: starting."); //tags it, lets developer know what activity im in

        //LinearLayout r1 = (LinearLayout) findViewById(R.id.r1);



        setupBottomNavigationView();

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //TextView textView = (TextView) findViewById(R.id.emailus1);
        //textView.setText(R.string.email_underline_string);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nagivation_drawer_open, R.string.nagivation_drawer_close);
        //creating a new actionbardrawertoggle named toggle, with the context as "this", passing the variable "drawer" and "toolbar" becuase these are the two vairbales that will be connected and synchronized
        //then we pass the two string resources we created
        drawer.addDrawerListener(toggle); //then we take our drawer variable and call a drawer listener and pass this toggle variable
        toggle.syncState(); //then we call syncstate to take care of rotating our hamburger icon to get rid of the drawer itself

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }




        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewsFragment()).commit(); //paste herre so that when we start our activity we open our message fragment immediately
            navigationView.setCheckedItem(R.id.nav_news);
    }

        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.fragment_container, new MeetingsFragment());
        //fragmentTransaction.commit();


        //Intent intent = new Intent(Intent.ACTION_SEND);
                //intent.setType("text/plain"); // send email as plain text
                //intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "some@email.address" });
        //intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                //intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                //startActivity(Intent.createChooser(intent, ""));
        //RelativeLayout item = (RelativeLayout)findViewById(R.id.emailus1);
        //View child = getLayoutInflater().inflate(R.layout.nav_header, null);
        //item.addView(child);

        //View view;
        //LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //view = inflater.inflate(R.layout.nav_header, null);

        //TextView txt = (TextView) findViewById(R.id.emailus1);
        //txt.setPaintFlags(txt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


    }



    private void setupBottomNavigationView(){

        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView"); //so in the log we know the code has made it this far and wont crash
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setUpBottomNavigationView(bottomNavigationViewEx); //references helper so i dont have to update the nav view settings in each activities

        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);

        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
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
            case R.id.nav_terms_service: //define what we want to do when we click this item
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new com.example.fblaappv01.TermsServiceFragment()).commit(); //takes care of placing our fragment in the fragment container frame layout
                break;
            case R.id.nav_faq: //define what we want to do when we click this item
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new com.example.fblaappv01.FAQFragment()).commit(); //takes care of placing our fragment in the fragment container frame layout
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share FBLA Chapter Tracker", Toast.LENGTH_SHORT).show(); //this si to simply see that we clicked this item, its the toast message
                try { //try/catch block to prompt the user to share the app
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "FBLA Chapter Tracker!");
                    String shareMessage = "\nCheck out FBLA Chapter Tracker! I'm loving it so far!\n\n";
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


