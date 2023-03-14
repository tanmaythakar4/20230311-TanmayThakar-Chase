package com.dynamo.jpmc.weatherapp;

import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import com.dynamo.jpmc.weatherapp.model.WeatherForecast;
import com.dynamo.jpmc.weatherapp.model.WsLocation;
import com.dynamo.jpmc.weatherapp.ui.current.WeatherViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.menuItemSearch);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMenuItem.collapseActionView();
                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    geocoder.getFromLocationName(query, 1, list -> updateCurrentAddress(list));
                } else {
                    try {
                        updateCurrentAddress(geocoder.getFromLocationName(query, 1));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    /**
     * Update the Current Location {@link WsLocation} to
     * fetch new {@link WeatherForecast}
     * @param list data received from geocoding
     */
    private void updateCurrentAddress(List<Address> list) {
        if (!list.isEmpty()) {
            Address address = list.get(0);
            StringBuilder city = new StringBuilder(address.getLocality()).append(" ").append(address.getCountryName().toString());
            viewModel.updateCurrentLocation(new WsLocation(address.getLatitude(), address.getLongitude(), city.toString()));
        } else {
            Toast.makeText(MainActivity.this, "Please enter correct city", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuItemRefresh) {
            viewModel.refreshWeather();
            return true;
        }
        return false;
    }
}