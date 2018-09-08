package lsteamer.elmexicano.com.maplisting;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapter sectionsPagerAdapter;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


    }


}
