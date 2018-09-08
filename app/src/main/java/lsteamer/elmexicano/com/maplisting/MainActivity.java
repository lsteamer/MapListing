package lsteamer.elmexicano.com.maplisting;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.maplisting.mvp.ListView;
import lsteamer.elmexicano.com.maplisting.mvp.MapView;
import lsteamer.elmexicano.com.maplisting.mvp.Presenter;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.landingLayout)
    ConstraintLayout landingLayout;
    @BindView(R.id.tabbedLayout)
    LinearLayout tabbedLayout;

    public FusedLocationProviderClient locationClient;


    private static ListView listView;
    private static MapView mapView;
    private static Presenter presenter;


    private SectionsPageAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        ButterKnife.bind(this);


        listView = new ListView();
        mapView = new MapView();


        //Location Permissions
        locationClient = LocationServices.getFusedLocationProviderClient(this);
        requestLocationPermission();

        startApp();


    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
    }

    @OnClick(R.id.landingButton)
    void startApp() {
        if (requestLocationPermission()) {

            setLocation();
            sectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());


            viewPager = findViewById(R.id.container);
            setupViewPager(viewPager, listView, mapView);


            TabLayout tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            landingLayout.setVisibility(View.GONE);
            tabbedLayout.setVisibility(View.VISIBLE);

        }
    }

    //todo I'm sending the fragments directly, It's not optimal but I'll revisit this later
    private void setupViewPager(ViewPager viewPager, ListView list, MapView map) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(list, list.TAG);
        adapter.addFragment(map, map.TAG);
        viewPager.setAdapter(adapter);
    }


    public boolean requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_COARSE_LOCATION}, 1);
            return false;
        } else
            setLocation();
        return true;
    }


    private void setLocation() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            locationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        setPresenter(location);

                    }
                }
            });
    }

    private void setPresenter(Location location) {
        presenter = new Presenter(listView, mapView, location);
    }


}
