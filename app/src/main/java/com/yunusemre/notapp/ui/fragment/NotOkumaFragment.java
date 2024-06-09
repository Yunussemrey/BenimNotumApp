package com.yunusemre.notapp.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
import com.yunusemre.notapp.data.entity.Notlar;
import com.yunusemre.notapp.databinding.AlertDialogBaslikRenkBinding;
import com.yunusemre.notapp.databinding.FragmentNotOkumaBinding;
import com.yunusemre.notapp.ui.viewmodel.NotOkumaViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotOkumaFragment extends Fragment {
    private NotOkumaViewModel viewModel;

   private FragmentNotOkumaBinding binding;
   private AdView banner3;
   SharedPreferences sp;
   SharedPreferences sp2;
   SharedPreferences.Editor editor;
   SharedPreferences.Editor editor2;
   Typeface typeface;
   Typeface typeface2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                binding = FragmentNotOkumaBinding.inflate(inflater, container, false);

                sp = requireActivity().getSharedPreferences("Renk", Context.MODE_PRIVATE);
                sp2 = requireActivity().getSharedPreferences("font",Context.MODE_PRIVATE);
                editor = sp.edit();
                editor2 = sp2.edit();
                String renk = sp.getString("renk", "#000000"); // başlık kaydolan rengi getir
                String renk2 = sp.getString("renk2","#000000"); // not kaydolan içeriği getir

                int font = sp2.getInt("font",R.font.abeezee);

                int font2 = sp2.getInt("font2",R.font.abeezee);





                NotOkumaFragmentArgs bundle = NotOkumaFragmentArgs.fromBundle(getArguments());
                Notlar okunanNot = bundle.getNotOkuma();

                binding.baslikOku.setText(okunanNot.getNot_baslik().toUpperCase());
                binding.notOku.setText(okunanNot.getNot_icerik());



                // başlık renklerini göster
            binding.baslikOku.setTextColor(Color.parseColor(renk));
            // not renklerini göster
            binding.notOku.setTextColor(Color.parseColor(renk2));


            // yazı stillerini gösterme

        typeface = ResourcesCompat.getFont(requireContext(),font);
        binding.baslikOku.setTypeface(typeface);

        typeface2 = ResourcesCompat.getFont(requireContext(),font2);
        binding.notOku.setTypeface(typeface2);





                binding.notDuzenle.setOnClickListener(v -> {
                    NotOkumaFragmentDirections.OkumaGuncelleGidis duzenle = NotOkumaFragmentDirections.okumaGuncelleGidis(okunanNot);
                    Navigation.findNavController(v).navigate(duzenle);
                });



        MobileAds.initialize(requireContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        banner3 = binding.adView3;
        AdRequest adRequest = new AdRequest.Builder().build();
        banner3.loadAd(adRequest);



        OnBackPressedCallback geriTusu = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
               Navigation.findNavController(requireView()).navigate(R.id.okumaToAnasayfa);

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),geriTusu);



        return binding.getRoot();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NotOkumaViewModel.class);
    }
}