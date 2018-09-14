package lsteamer.elmexicano.com.maplisting;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.maplisting.model.CarData;
import lsteamer.elmexicano.com.maplisting.model.Feed;
import lsteamer.elmexicano.com.maplisting.view.ListViewLayer;
import lsteamer.elmexicano.com.maplisting.view.MapViewLayer;
import lsteamer.elmexicano.com.maplisting.view.SectionsPageAdapter;
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

    private ListViewLayer listViewLayer;
    private MapViewLayer mapViewLayer;
    private Presenter presenter;

    private List<CarData> carDataList;

    private SectionsPageAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ButterKnife.bind(this);


        listViewLayer = new ListViewLayer();
        mapViewLayer = new MapViewLayer();


        //Location Permissions
        locationClient = LocationServices.getFusedLocationProviderClient(this);
        requestLocationPermission();

        //TabLayout, Location and Retrofit Call are set in a different method.
        startApp();

    }

    @OnClick(R.id.landingButton)
    void startApp() {
        //They are set in a different layout, to have a button in case there's No Location services. We can't go forward without it.
        if (requestLocationPermission()) {

            //Get the user Location
            setLocation();
            sectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());

            //Downloading the list through retrofit
            Call<Feed> data = Utils.getLoginRequestData(Utils.FULL_URL);
            data.enqueue(new Callback<Feed>() {
                @Override
                public void onResponse(@NonNull Call<Feed> call, @NonNull Response<Feed> response) {
                    if (response.body() != null) {
                        carDataList = response.body().getCarData();

                        presenter.startDataInFragments(carDataList);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Feed> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), getText(R.string.fail), Toast.LENGTH_LONG).show();
                }
            });

            //Setting the Tab and Adapter
            viewPager = findViewById(R.id.container);
            setupViewPager(viewPager, listViewLayer, mapViewLayer);

            TabLayout tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);

            //Hiding the Landing Layout and showing the tabs
            landingLayout.setVisibility(View.GONE);
            tabbedLayout.setVisibility(View.VISIBLE);

            //Set the presenter
            setPresenter();

        }
    }

    //Setting the two fragments to the viewPager
    private void setupViewPager(ViewPager viewPager, ListViewLayer list, MapViewLayer map) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(list, getString(R.string.tab_title_list));
        adapter.addFragment(map, getString(R.string.tab_title_map));
        viewPager.setAdapter(adapter);
    }


    //The location permission must be granted in order for the app to download.
    public boolean requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
            return false;
        } else
            setLocation();
        return true;
    }

    //As soon as the location is given, we inject it to the presenter.
    private void setLocation() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            locationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        presenter.setUserLocation(location);

                    }
                }
            });
    }

    private void setPresenter() {
        presenter = new Presenter(listViewLayer, mapViewLayer);
    }


}
