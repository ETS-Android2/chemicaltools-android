package com.njzjz.chemicaltools;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;

public class TitleActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.njzjz.chemicalTools.MESSAGE";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.home);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);


        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        Resources res = getResources();
        String[] values = new String[]{
                res.getString(R.string.button_element),
                res.getString(R.string.button_mass),
                res.getString(R.string.button_exam),
                res.getString(R.string.button_Settings),
                res.getString(R.string.button_Share),
                res.getString(R.string.button_Github),
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                       //Element
                        openElement(view);
                        break;
                    case 1:
                        //Mass
                        openMass(view);
                        break;
                    case 5:
                        //Github
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/njzjz/Chemical-Tools-for-Android"));
                        startActivity(browserIntent);
                        break;
                    case 4:
                        //Share
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        share.putExtra(Intent.EXTRA_SUBJECT,
                                getString(R.string.app_name));
                        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name) + "\n" +
                                "https://github.com/njzjz/Chemical-Tools-for-Android");
                        startActivity(Intent.createChooser(share,
                                getString(R.string.app_name)));
                        break;
                    case 2:
                        //Exam
                        openExam(view);
                        break;
                    case 3:
                        //Settings
                        openSettings();
                        break;
                    case 6:

                        break;
                }

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //菜单栏返回键功能
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                return true;
            case R.id.action_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                share.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.app_name));
                share.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name) + "\n" +
                        "https://github.com/njzjz/Chemical-Tools-for-Android");
                startActivity(Intent.createChooser(share,
                        getString(R.string.app_name)));
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void openElement(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openMass(View view) {
        Intent intent = new Intent(this, MassActivity.class);
        startActivity(intent);
    }
    public void openExam(View view) {
        Intent intent = new Intent(this, ExamActivity.class);
        startActivity(intent);
    }
    public void openSettings(){
        Intent intent =new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
