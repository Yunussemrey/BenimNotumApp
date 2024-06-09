package com.yunusemre.notapp.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;
import com.yunusemre.notapp.R;
import com.yunusemre.notapp.databinding.FragmentAyarlarBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.BuildConfig;

@AndroidEntryPoint
public class AyarlarFragment extends Fragment {
    private FragmentAyarlarBinding binding;
    private AdView banner5;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = FragmentAyarlarBinding.inflate(inflater, container, false);

            binding.hakkimda.setOnClickListener(v -> {
                Navigation.findNavController(v).navigate(R.id.hakkimdaGecis);
            });

           binding.fontColor.setOnClickListener(v -> {
               Navigation.findNavController(v).navigate(R.id.yaziRenkGecis);
           });

        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        binding.ayarlarTarih.setText(date); //günün tarihi

        int versionCode = BuildConfig.VERSION_CODE;

        binding.version.setText("Version "+versionCode);


        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        banner5 = binding.adView5;
        AdRequest adRequest = new AdRequest.Builder().build();
        banner5.loadAd(adRequest);









        return binding.getRoot();
    }





    @Override
    public void onResume() {
        super.onResume();
    }
}