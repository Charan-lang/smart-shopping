package com.example.rabiy.styleomega14.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.rabiy.styleomega14.Adapters.ViewPagerAdapter;
import com.example.rabiy.styleomega14.Fragments.HistoryFragment;
import com.example.rabiy.styleomega14.Fragments.ProfileFragment;
import com.example.rabiy.styleomega14.R;

public class MyAccountActivity extends AppCompatActivity {

    private TabLayout tabLayoutAccount;
    private ViewPager viewPagerAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        tabLayoutAccount=findViewById(R.id.tab_layout_account);
        viewPagerAccount=findViewById(R.id.view_pager_account);
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());

        //Adding fragments
        adapter.AddFragment(new ProfileFragment(),"Profile");
        adapter.AddFragment(new HistoryFragment(),"Purchase History");

        //Adapter setup
        viewPagerAccount.setAdapter(adapter);
        tabLayoutAccount.setupWithViewPager(viewPagerAccount);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Account");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

    }
}
