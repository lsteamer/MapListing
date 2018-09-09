package lsteamer.elmexicano.com.maplisting;

import android.content.pm.PackageManager;
import android.location.Location;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.maplisting.model.CarData;
import lsteamer.elmexicano.com.maplisting.model.Feed;
import lsteamer.elmexicano.com.maplisting.mvp.ListView;
import lsteamer.elmexicano.com.maplisting.mvp.MapView;
import lsteamer.elmexicano.com.maplisting.mvp.Presenter;
import lsteamer.elmexicano.com.maplisting.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.landingLayout)
    ConstraintLayout landingLayout;
    @BindView(R.id.tabbedLayout)
    LinearLayout tabbedLayout;

    public FusedLocationProviderClient locationClient;


    private static ListView listView;
    private static MapView mapView;
    private static Presenter presenter;

    private List<CarData> carDataList;

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

        Call<Feed> data = Utils.getLoginRequestData(Utils.FULL_URL);
        data.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if(response.body() != null){
                    carDataList = response.body().getCarData();

                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {

            }
        });


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
        adapter.addFragment(list, getString(R.string.tab_text_1));
        adapter.addFragment(map, getString(R.string.tab_text_2));
        viewPager.setAdapter(adapter);
    }


    public boolean requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
            return false;
        } else
            setLocation();
        return true;
    }


    private void setLocation() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
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
        presenter = new Presenter(listView, mapView, locationClient, location, carDataList);
    }


}
