package com.yunusemre.notapp.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;
import com.yunusemre.notapp.R;
import com.yunusemre.notapp.databinding.FragmentNotGuncelleBinding;
import com.yunusemre.notapp.data.entity.Notlar;
import com.yunusemre.notapp.ui.viewmodel.NotGuncelleViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotGuncelleFragment extends Fragment {

   private FragmentNotGuncelleBinding binding;
   private NotGuncelleViewModel viewModel;
   private AdView banner4;
   String  not_baslik;
   String not_icerik;
   String not_tarih;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = FragmentNotGuncelleBinding.inflate(inflater,container,false);


            NotGuncelleFragmentArgs bundle = NotGuncelleFragmentArgs.fromBundle(getArguments());
            Notlar gelenNot = bundle.getNotlar();

            binding.editTextBaslikGuncel.setText(gelenNot.getNot_baslik());
            binding.editTextNotGuncel.setText(gelenNot.getNot_icerik());

        String tarih = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        binding.tarihText.setText(tarih);
        binding.buttonGuncelle.setOnClickListener(v -> {
             not_baslik = binding.editTextBaslikGuncel.getText().toString();
             not_icerik = binding.editTextNotGuncel.getText().toString();
             not_tarih = binding.tarihText.getText().toString();
            viewModel.guncelle(gelenNot.getNot_id(),not_baslik,not_icerik,not_tarih);
           Navigation.findNavController(v).navigate(R.id.guncelleToAnasayfa);

        });

        MobileAds.initialize(requireContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
        banner4 = binding.adView4;
        AdRequest adRequest = new AdRequest.Builder().build();
        banner4.loadAd(adRequest);


        return binding.getRoot();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NotGuncelleViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}