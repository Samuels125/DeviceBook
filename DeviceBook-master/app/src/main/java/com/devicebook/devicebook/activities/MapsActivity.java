package com.devicebook.devicebook.activities;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.devicebook.devicebook.R;
import com.devicebook.devicebook.objects.PlaceInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    Fragment mapfragment;
    AutoCompleteTextView inputSearch;
    Button placeButton;
    ArrayAdapter placeAdapter;
    private GoogleApiClient mGoogleApiClient;
    private PlaceInfo mPlace;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOcation_PERMISSIONS_REQUEST_CODE = 1234;
    private boolean mLocationpermissiongranted = false;
    private static final float DEFAULT_ZOOM = 15;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40,-168),new LatLng(71,136));

    //widgets//
    private ImageView mGps;
    private PlaceAutocompleteAdapter mplaceAutocompleteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_store_finder);
        inputSearch = findViewById(R.id.input_search);
        mGps = findViewById(R.id.gps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //.findFragmentById(R.id.map);
        //  mapFragment.getMapAsync(this);
        getLocationPermission();
        init();

    }

    private void init(){

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
inputSearch.setOnItemClickListener(mAutocompleteListener);
        mplaceAutocompleteAdapter = new PlaceAutocompleteAdapter
                (this,Places.getGeoDataClient(this, null),LAT_LNG_BOUNDS,null);

        inputSearch.setAdapter(mplaceAutocompleteAdapter);

        inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_DONE
                        ||keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        ||keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){
//execute methods
                    geoLocate();
                }

                return false;
            }
        });
        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDeviceLocation();
            }
        });
        hidesoftkeyboard();
    }



private void geoLocate(){
        String searchString = inputSearch.getText().toString();
    Geocoder geocoder = new Geocoder(MapsActivity.this);
    List<Address> list = new ArrayList<>();
    try{
list = geocoder.getFromLocationName(searchString, 1);
    }catch(IOException E){

    }
    if(list.size()>0){
        Address address = list.get(0);
        //Toast.makeText(this,address.toString(),Toast.LENGTH_SHORT).show();

        moveCamera(new LatLng(address.getLatitude(),address.getLongitude()),DEFAULT_ZOOM,address.getAddressLine(0));

    }
    hidesoftkeyboard();
}
    private void getDeviceLocation() {
        //Log.d(TAG,"getDeviceLocation: getting current device currentlocation");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {

            if (mLocationpermissiongranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Log.d(TAG,"Found Location");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM,"My Location");
                        } else {
                            //Log.d(TAG,"Unable to find Location");
                        }
                    }
                });
            }
        } catch (SecurityException e) {

        }
    }

    private void moveCamera(LatLng latLng, float zoom, String title ) {
        mMap.moveCamera((CameraUpdateFactory.newLatLngZoom(latLng, zoom)));

        if(!title.equals("My Location")){MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title(title);
            mMap.addMarker(options);}


        hidesoftkeyboard();
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
    }


    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                mLocationpermissiongranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOcation_PERMISSIONS_REQUEST_CODE);

            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOcation_PERMISSIONS_REQUEST_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationpermissiongranted = false;
        switch (requestCode) {
            case LOcation_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationpermissiongranted = false;
                            return;
                        }
                    }
                    mLocationpermissiongranted = true;
                    initMap();
                }
            }

        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mLocationpermissiongranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.getUiSettings().isMapToolbarEnabled();
        }
       float zoomLevel = 16.0f; //This goes up to 21
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.animateCamera(CameraUpdateFactory.zoomTo(4), 1000, null);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-33.86997, 151.2089), 6));
        // Add a marker in Sydney and move the camera

        //float zoomLevel = 14;
        // Zoom in, animating the camera.

    }
    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicebook_menu, menu);

        // MenuItem searchitem = menu.findItem(R.id.search);
        //SearchView mysearchview =(SearchView) MenuItemCompat.getActionView(searchitem);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        //Intent menuintent = new Intent(AdminListOrders.this,DisplayGridActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        // startActivity(menuintent);
        return super.onOptionsItemSelected(item);
    }

    private void hidesoftkeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Auto complete google places api//

    private AdapterView.OnItemClickListener mAutocompleteListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


           final AutocompletePrediction item = mplaceAutocompleteAdapter.getItem(i);
           final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlacceDetailsCallback);
hidesoftkeyboard();
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlacceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if(!places.getStatus().isSuccess()){
                places.release();
                //needed to stop memory error//
                return;
            }
            final Place  place = places.get(0);
            try{
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                mPlace.setAddress(place.getAddress().toString());
               // mPlace.setAttributions(place.getAttributions().toString());
                mPlace.setId(place.getId());
                mPlace.setLatLng(place.getLatLng());
                mPlace.setRating(place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                mPlace.setWebsiteurl(place.getWebsiteUri().toString());

            }catch(NullPointerException e){

            }
            hidesoftkeyboard();
            moveCamera(new LatLng(place.getViewport().getCenter().latitude,place.getViewport().getCenter().longitude),DEFAULT_ZOOM,mPlace.getName());

            places.release();
        }
    };
}
