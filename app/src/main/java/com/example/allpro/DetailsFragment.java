package com.example.allpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    private ImageView ivStadiumImage, btnBack; // btnBack الآن ImageView
    private TextView tvStadiumName, tvOpeningDate, tvSurfaceType, tvBiggestMatch,
            tvFamousPlayer, tvAverageAttendance, tvMaxAttendance;

    private Button btnOpenMap, btnGoToAllNew;

    public DetailsFragment() {}

    public static DetailsFragment newInstance(String name, String openingDate, String surfaceType,
                                              String biggestMatch, String famousPlayer,
                                              String averageAttendance, String maxAttendance,
                                              String photoUrl, String address) {

        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();

        args.putString("name", name);
        args.putString("openingDate", openingDate);
        args.putString("surfaceType", surfaceType);
        args.putString("biggestMatch", biggestMatch);
        args.putString("famousPlayer", famousPlayer);
        args.putString("averageAttendance", averageAttendance);
        args.putString("maxAttendance", maxAttendance);
        args.putString("photoUrl", photoUrl);
        args.putString("address", address);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // ربط عناصر الواجهة
        ivStadiumImage = view.findViewById(R.id.ivStadiumImage);
        tvStadiumName = view.findViewById(R.id.tvStadiumName);
        tvOpeningDate = view.findViewById(R.id.tvOpeningDate);
        tvSurfaceType = view.findViewById(R.id.tvSurfaceType);
        tvBiggestMatch = view.findViewById(R.id.tvBiggestMatch);
        tvFamousPlayer = view.findViewById(R.id.tvFamousPlayer);
        tvAverageAttendance = view.findViewById(R.id.tvAverageAttendance);
        tvMaxAttendance = view.findViewById(R.id.tvMaxAttendance);

        btnOpenMap = view.findViewById(R.id.btnOpenMap);
        btnGoToAllNew = view.findViewById(R.id.btnGoToAllNew);
        btnBack = view.findViewById(R.id.btnBack); // ImageView الآن

        if (getArguments() != null) {

            tvStadiumName.setText(getArguments().getString("name"));
            tvOpeningDate.setText("Opening Date: " + getArguments().getString("openingDate"));
            tvSurfaceType.setText("Surface Type: " + getArguments().getString("surfaceType"));
            tvBiggestMatch.setText("Biggest Match: " + getArguments().getString("biggestMatch"));
            tvFamousPlayer.setText("Famous Player: " + getArguments().getString("famousPlayer"));
            tvAverageAttendance.setText("Average Attendance: " + getArguments().getString("averageAttendance"));
            tvMaxAttendance.setText("Max Attendance: " + getArguments().getString("maxAttendance"));

            String photoUrl = getArguments().getString("photoUrl");

            if (photoUrl != null && !photoUrl.isEmpty()) {
                Picasso.get()
                        .load(photoUrl)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivStadiumImage);
            } else {
                ivStadiumImage.setImageResource(R.drawable.ic_launcher_foreground);
            }

            // زر الخريطة
            btnOpenMap.setOnClickListener(v -> {
                String address = getArguments().getString("address");

                if (address != null && !address.isEmpty()) {
                    MapDialogFragment mapFragment = MapDialogFragment.newInstance(address);
                    mapFragment.show(getParentFragmentManager(), "mapDialog");
                }
            });

            // زر الصفحة الرئيسية (AllNewFragment)
            btnGoToAllNew.setOnClickListener(v -> {
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayout, new AllNewFragment());
                ft.addToBackStack(null);
                ft.commit();
            });

            // زر الرجوع
            btnBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());
        }

        return view;
    }
}
