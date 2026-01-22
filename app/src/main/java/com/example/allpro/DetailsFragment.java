package com.example.allpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    private TextView tvStadiumName, tvOpeningDate, tvSurfaceType, tvBiggestMatch,
            tvFamousPlayer, tvAverageAttendance, tvMaxAttendance;

    public DetailsFragment() {
        // Required empty public constructor
    }

    // إنشاء الـ Fragment مع تمرير البيانات عبر Bundle
    public static DetailsFragment newInstance(String name, String openingDate, String surfaceType,
                                              String biggestMatch, String famousPlayer,
                                              String averageAttendance, String maxAttendance) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("openingDate", openingDate);
        args.putString("surfaceType", surfaceType);
        args.putString("biggestMatch", biggestMatch);
        args.putString("famousPlayer", famousPlayer);
        args.putString("averageAttendance", averageAttendance);
        args.putString("maxAttendance", maxAttendance);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // ربط العناصر
        tvStadiumName = view.findViewById(R.id.tvStadiumName);
        tvOpeningDate = view.findViewById(R.id.tvOpeningDate);
        tvSurfaceType = view.findViewById(R.id.tvSurfaceType);
        tvBiggestMatch = view.findViewById(R.id.tvBiggestMatch);
        tvFamousPlayer = view.findViewById(R.id.tvFamousPlayer);
        tvAverageAttendance = view.findViewById(R.id.tvAverageAttendance);
        tvMaxAttendance = view.findViewById(R.id.tvMaxAttendance);

        // استلام البيانات من Bundle
        if (getArguments() != null) {
            tvStadiumName.setText(getArguments().getString("name"));
            tvOpeningDate.setText("Opening Date: " + getArguments().getString("openingDate"));
            tvSurfaceType.setText("Surface Type: " + getArguments().getString("surfaceType"));
            tvBiggestMatch.setText("Biggest Match: " + getArguments().getString("biggestMatch"));
            tvFamousPlayer.setText("Famous Player: " + getArguments().getString("famousPlayer"));
            tvAverageAttendance.setText("Average Attendance: " + getArguments().getString("averageAttendance"));
            tvMaxAttendance.setText("Max Attendance: " + getArguments().getString("maxAttendance"));
        }

        return view;
    }
}

