package com.fivewheeler.training.newscomparator.editor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fivewheeler.training.newscomparator.NewsFeedSelector;
import com.fivewheeler.training.newscomparator.R;
import com.fivewheeler.training.newscomparator.fragments.AllNewsFragment;
import com.fivewheeler.training.newscomparator.fragments.BaseFragment;
import com.fivewheeler.training.newscomparator.fragments.BusinessNewsFragment;
import com.fivewheeler.training.newscomparator.fragments.EducationNewsFragment;
import com.fivewheeler.training.newscomparator.fragments.HealthNewsFragment;
import com.fivewheeler.training.newscomparator.fragments.InternationalNewsFragment;
import com.fivewheeler.training.newscomparator.fragments.NationalNewsFragment;
import com.fivewheeler.training.newscomparator.fragments.SportsNewsFragment;
import com.fivewheeler.training.newscomparator.fragments.TechnologyNewsFragment;
import com.fivewheeler.training.newscomparator.fragments.dummy.DummyContent;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BaseFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Initialize the menu to home screen.
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_home));
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
        getMenuInflater().inflate(R.menu.dashboard, menu);
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
            startActivity(new Intent(this, NewsFeedSelector.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            setTitle("All Categories news");
            // replace it will all news fragment
            FragmentManager manager = this.getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.contentFrame);

            FragmentTransaction transaction = manager.beginTransaction();

            if(fragment==null){
                transaction.add(R.id.contentFrame, new AllNewsFragment(), "home");
            }else {
                transaction.replace(R.id.contentFrame, new AllNewsFragment(), "home");
            }

            transaction.commit();

        } else if (id == R.id.nav_international) {
            setTitle("International news");
            // replace with international news
            // replace it will all news fragment
            FragmentManager manager = this.getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.contentFrame);

            FragmentTransaction transaction = manager.beginTransaction();
            if(fragment==null){
                transaction.add(R.id.contentFrame, new InternationalNewsFragment(), "international");
            }else {
                transaction.replace(R.id.contentFrame, new InternationalNewsFragment(), "international");
            }

            transaction.commit();
        } else if (id == R.id.nav_national) {
            setTitle("National news");
            // replace with national news
            FragmentManager manager = this.getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.contentFrame);

            FragmentTransaction transaction = manager.beginTransaction();
            if(fragment==null){
                transaction.add(R.id.contentFrame, new NationalNewsFragment(), "national");
            }else {
                transaction.replace(R.id.contentFrame, new NationalNewsFragment(), "national");
            }


            transaction.commit();
        } else if (id == R.id.nav_business) {
            setTitle("Business news");
            // replace with business news
            FragmentManager manager = this.getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.contentFrame);

            FragmentTransaction transaction = manager.beginTransaction();
            if(fragment==null){
                transaction.add(R.id.contentFrame, new BusinessNewsFragment(), "business");
            }else {
                transaction.replace(R.id.contentFrame, new BusinessNewsFragment(), "business");
            }

            transaction.commit();

        } else if (id == R.id.nav_sports) {
            setTitle("Sports news");
            // replace with sports news
            FragmentManager manager = this.getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.contentFrame);

            FragmentTransaction transaction = manager.beginTransaction();
            if(fragment==null){
                transaction.add(R.id.contentFrame, new SportsNewsFragment(), "sports");
            }else {
                transaction.replace(R.id.contentFrame, new SportsNewsFragment(), "sports");
            }
            transaction.commit();

        } else if (id == R.id.nav_health) {
            setTitle("Health news");
            // replace with health news
            FragmentManager manager = this.getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.contentFrame);

            FragmentTransaction transaction = manager.beginTransaction();
            if(fragment==null){
                transaction.add(R.id.contentFrame, new HealthNewsFragment(), "health");
            }else {
                transaction.replace(R.id.contentFrame, new HealthNewsFragment(), "health");
            }

            transaction.commit();

        } else if (id == R.id.nav_technology) {
            setTitle("Technology news");
            // replace with technology news
            FragmentManager manager = this.getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.contentFrame);

            FragmentTransaction transaction = manager.beginTransaction();
            if(fragment==null){
                transaction.add(R.id.contentFrame, new TechnologyNewsFragment(), "technology");
            }else {
                transaction.replace(R.id.contentFrame, new TechnologyNewsFragment(), "technology");
            }

            transaction.commit();

        }
        else if (id == R.id.nav_education) {
            setTitle("Educational news");
            // replace with educational news
            FragmentManager manager = this.getSupportFragmentManager();
            Fragment fragment = manager.findFragmentById(R.id.contentFrame);

            FragmentTransaction transaction = manager.beginTransaction();
            if(fragment==null){
                transaction.add(R.id.contentFrame, new EducationNewsFragment(), "education");
            }else {
                transaction.replace(R.id.contentFrame, new EducationNewsFragment(), "education");
            }

            transaction.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.i("OnList Item clicked", "OnList item clicked");
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.linkUrl));
        startActivity(browserIntent);
    }
}
