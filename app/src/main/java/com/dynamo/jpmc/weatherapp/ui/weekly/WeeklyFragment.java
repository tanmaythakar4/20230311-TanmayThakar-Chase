package com.dynamo.jpmc.weatherapp.ui.weekly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dynamo.jpmc.weatherapp.R;

/**
 * Created by tanmaythakar on 3/11/23.
 */
public class WeeklyFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weekly, container, false);
        return root;
    }
}
