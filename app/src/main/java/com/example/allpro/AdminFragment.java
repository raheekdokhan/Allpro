package com.example.allpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AdminFragment extends Fragment {

    public AdminFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // استخدم هنا XML الخاص بالـ AdminFragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // هنا نجيب الأزرار من الـ XML
        Button btnAdd = view.findViewById(R.id.btnAdd);
        Button btnAll = view.findViewById(R.id.btnAll);

        // الانتقال إلى صفحة إضافة عنصر جديد (StadiumFragment)
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new StadiumFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // الانتقال إلى صفحة عرض جميع العناصر (AllFragment)
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new AllFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}


