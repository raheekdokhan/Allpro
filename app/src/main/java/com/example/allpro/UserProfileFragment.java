package com.example.allpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager2.widget.ViewPager2;

public class UserProfileFragment extends Fragment {

    private ImageView imageProfile;
    private TextView textUsername;
    private Button buttonEditProfile;
    private Button buttonAdmin;
    private Button buttonInvite;

    private TextView statTickets, statMatches, statFavorites;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        // Views
        imageProfile = view.findViewById(R.id.profile_image);
        textUsername = view.findViewById(R.id.textUsername);
        buttonEditProfile = view.findViewById(R.id.btn_edit_profile);
        buttonAdmin = view.findViewById(R.id.btn_admin);
        buttonInvite = view.findViewById(R.id.btn_invite);

        statTickets = view.findViewById(R.id.stat_tickets);
        statMatches = view.findViewById(R.id.stat_matches);
        statFavorites = view.findViewById(R.id.stat_favorites);


        // Load user data from Firebase
        loadUsernameFromFirebase();

        // زر Edit Profile
        buttonEditProfile.setOnClickListener(v -> getParentFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new ProfileFragment())
                .addToBackStack(null)
                .commit());

        // زر Admin
        buttonAdmin.setOnClickListener(v -> getParentFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new AdminFragment())
                .addToBackStack(null)
                .commit());

        // زر Invite Friends
        buttonInvite.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome Stadium app!");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        // يمكنك إضافة إعداد ViewPager2 + Tabs هنا إذا أردت
        // setupViewPager();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadUsernameFromFirebase();
    }

    private void loadUsernameFromFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            if (name == null || name.isEmpty()) {
                name = user.getEmail(); // استخدام البريد كبديل إذا الاسم فارغ
            }
            textUsername.setText(name);
        } else {
            textUsername.setText("User Name");
        }

        // يمكن تحديث الإحصائيات هنا من قاعدة البيانات أو Firebase
        statTickets.setText("12");
        statMatches.setText("5");
        statFavorites.setText("3");
    }

}
