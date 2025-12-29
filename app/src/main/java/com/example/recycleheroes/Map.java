package com.example.recycleheroes;



import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.BitmapUtils;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.mapbox.android.core.permissions.PermissionsListener;

public class Map extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener, NetworkService.OnTaskCompleted {

    private MapView mapView;
    public static final String CATEGORY_KEY = "category";
    private String category;

    private MapboxMap boxmap;





    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if(result) {
                Toast.makeText(Map.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Map.this, "Permission not granted!", Toast.LENGTH_SHORT).show();
            }

        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra(CATEGORY_KEY)) {
            category = intent.getStringExtra(CATEGORY_KEY);
        } else {
            // Default category if not provided
            category = "ALL";
        }
        Mapbox.getInstance(this, "sk.eyJ1IjoidGhpbG94ZW4iLCJhIjoiY2xwaTdhN2xqMGZ0azJrdDM3MWc4bWIyYyJ9.JrWCJ5uKQMJxYjyGzgH2Bg");
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        boxmap = mapboxMap;

        String message = "ALL";
        NetworkService networkService = new NetworkService(this);// Set the listener
        networkService.execute(category);

        mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {
            style.addImage("Logo", BitmapUtils.getBitmapFromDrawable(getResources().getDrawable(R.drawable.logo)));
            // Add a marker to a specific location
            LatLng markerLatLng = new LatLng(53.54615, 9.95727);


            // ... (Andere Stileinstellungen)

            // Enable the location component
            if (PermissionsManager.areLocationPermissionsGranted(this)) {
                mapboxMap.getLocationComponent().activateLocationComponent(
                        LocationComponentActivationOptions.builder(this, style).build());

                // Enable to make the component visible
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                     //public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mapboxMap.getLocationComponent().setLocationComponentEnabled(true);
            } else {
                // Request location permission
                PermissionsManager permissionsManager = new PermissionsManager((PermissionsListener) this);
                permissionsManager.requestLocationPermissions(this);
            }
        });



    }

    // Implement PermissionsListener methods



    private Marker marker;

    private double calculateDistance(LatLng point1, LatLng point2) {
        final int R = 6371; // Radius der Erde in Kilometern
        double lat1 = Math.toRadians(point1.getLatitude());
        double lon1 = Math.toRadians(point1.getLongitude());
        double lat2 = Math.toRadians(point2.getLatitude());
        double lon2 = Math.toRadians(point2.getLongitude());

        double latDistance = lat2 - lat1;
        double lonDistance = lon2 - lon1;

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    private void addMarkerWithInfoWindow(MapboxMap mapboxMap, LatLng latLng, String title, String snippet) {


        mapboxMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(snippet)
                .icon(IconFactory.getInstance(this).fromResource(R.drawable.logomini)));


        mapboxMap.setOnMarkerClickListener(marker -> {
            LatLng markerLatLng = new LatLng(marker.getPosition().getLatitude(), marker.getPosition().getLongitude());
            double distance = calculateDistance(mapboxMap.getCameraPosition().target, markerLatLng);

            if (distance < 50) {
                showPopupDialog(marker.getTitle(), marker.getSnippet());
                return true;
            }
            return false;
        });
    }
    private void showPopupDialog(String title, String snippet) {
        // Erstelle ein AlertDialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Setze den Titel und den Text des Dialogs
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(snippet);

        // Füge einen Schließen-Button hinzu
        alertDialogBuilder.setPositiveButton("Schließen", (dialog, which) -> dialog.dismiss());

        // Zeige den Dialog an
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void addMultipleMarkers(MapboxMap mapboxMap, String response) {
        // Erstelle eine Liste von Marker-Positionen (LatLng)

        String jsonResponse= "[{ \"id\": 1, \"userId\": 123, \"latitude\": 40.7128, \"longitude\": -74.0060 , \"type\": \"Glass\", \"category\": \"Container\", \"images\": [\"image_url_1.jpg\", \"image_url_2.jpg\"], \"creationDate\": \"2023-12-01T10:30:00Z\" },{ \"id\": 2, \"userId\": 456,  \"latitude\": 34.0522, \"longitude\": -118.2437 , \"type\": \"Plastic\", \"category\": \"Container\", \"images\": [\"image_url_3.jpg\", \"image_url_4.jpg\"], \"creationDate\": \"2023-12-02T12:45:00Z\" },{ \"id\": 3, \"userId\": 789, \"latitude\": 51.5074, \"longitude\": -0.1278 , \"type\": \"Paper\", \"category\": \"Container\", \"images\": [\"image_url_5.jpg\", \"image_url_6.jpg\"], \"creationDate\": \"2023-12-03T15:20:00Z\" }]";

        DataService dataService = new DataService();
        List<RecyclingPointModel> recyclingPoints = dataService.parseJsonResponse(response);




        // Füge weitere Koordinaten hinzu, wie benötigt

        // Füge für jede Marker-Position einen Marker hinzu
        for (RecyclingPointModel recyclingPoint : recyclingPoints) {
            LatLng position = new LatLng(recyclingPoint.latitude, recyclingPoint.longitude);
            addMarkerWithInfoWindow(mapboxMap, position, recyclingPoint.category, recyclingPoint.type);
        }
    }




   /* private void addMarker(MapboxMap mapboxMap, LatLng latLng) {
        SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, mapboxMap.getStyle());
        symbolManager.setIconAllowOverlap(true);
        symbolManager.setIconIgnorePlacement(true);

        SymbolOptions symbolOptions = new SymbolOptions()
                .withLatLng(latLng)
                .withIconImage("Logo")
                .withIconSize(0.05f);

        symbolManager.create(symbolOptions);
    }


*/



    // Add MapView lifecycle methods
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

    }
    @Override
    public void onTaskCompleted(String result) {
        addMultipleMarkers(boxmap, result);
    }
}
