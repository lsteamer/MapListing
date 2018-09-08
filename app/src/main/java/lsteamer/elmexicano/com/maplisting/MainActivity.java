package lsteamer.elmexicano.com.maplisting;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import lsteamer.elmexicano.com.maplisting.mvp.ListView;
import lsteamer.elmexicano.com.maplisting.mvp.MapView;
import lsteamer.elmexicano.com.maplisting.mvp.Presenter;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MapView mapView;
    private Presenter presenter;


    private SectionsPageAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        sectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        listView = new ListView();
        mapView = new MapView();

        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager, listView, mapView);

        presenter = new Presenter(listView, mapView);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    //todo I'm sending the fragments directly, It's not optimal but I'll revisit this later
    private void setupViewPager(ViewPager viewPager, ListView list, MapView map){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(list, list.TAG);
        adapter.addFragment(map, map.TAG);
        viewPager.setAdapter(adapter    );
    }



}
