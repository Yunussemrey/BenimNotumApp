package com.yunusemre.notapp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.yunusemre.notapp.R;
import com.yunusemre.notapp.databinding.FragmentNotKayitBinding;
import com.yunusemre.notapp.ui.viewmodel.NotKayitViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotKayitFragment extends Fragment {

    private FragmentNotKayitBinding binding;

    private NotKayitViewModel viewModel;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private AdView banner2;
    String not_baslik;
    String not_icerik;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = FragmentNotKayitBinding.inflate(inflater,container,false);

            //shared preferences tanımlama
            sp = requireActivity().getSharedPreferences("Tarih", Context.MODE_PRIVATE);
            editor = sp.edit();
        MobileAds.initialize(requireContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        banner2 = binding.adView2;
        AdRequest adRequest = new AdRequest.Builder().build();
        banner2.loadAd(adRequest);





                String tarih = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                binding.textTarih.setText(tarih);




            binding.buttonSave.setOnClickListener(v -> {
                 not_baslik = binding.editTextBaslik.getText().toString();
                 not_icerik = binding.editTextNot.getText().toString();
                    if (not_baslik.isEmpty()){
                        Snackbar.make(v,"Lütfen bir başlık giriniz",Snackbar.LENGTH_LONG).show();
                    }else {
                        viewModel.kaydet(not_baslik,not_icerik);
                        Navigation.findNavController(v).navigate(R.id.kayitToAnasayfa);
                    }
                    editor.putString("tarih",tarih);
                    editor.apply();


            });




        return binding.getRoot();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NotKayitViewModel.class);
    }
}