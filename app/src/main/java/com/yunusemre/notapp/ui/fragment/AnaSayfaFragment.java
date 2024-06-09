package com.yunusemre.notapp.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;
import com.yunusemre.notapp.R;
import com.yunusemre.notapp.databinding.FragmentAnaSayfaBinding;
import com.yunusemre.notapp.data.entity.Notlar;
import com.yunusemre.notapp.ui.adapter.NotlarAdapter;
import com.yunusemre.notapp.ui.viewmodel.AnaSayfaViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnaSayfaFragment extends Fragment {
    private FragmentAnaSayfaBinding binding;
        private AnaSayfaViewModel viewModel;
        public MutableLiveData<Notlar> notlarList;
        SharedPreferences sp;
        SharedPreferences sp2;
         private AdView banner;
         private InterstitialAd mInterstitialAd;
         ActivityResultLauncher<String> permissionLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = FragmentAnaSayfaBinding.inflate(inflater,container,false);

            sp = getActivity().getSharedPreferences("Tarih", Context.MODE_PRIVATE);

            //izin kontrol
            registerLauncher();

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // banner reklam
        banner = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);

        //geçiş reklamı

        InterstitialAd.load(getContext(),"ca-app-pub-3475820063501035/4457613899", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                        mInterstitialAd = interstitialAd;

                    }

                });




        binding.noteAdd.setOnClickListener(v -> {



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // 33 sonrası

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_MEDIA_IMAGES)){
                        Snackbar.make(requireView(),"Uygulamanın cihazda veri depolaması için izin gerekli.",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //izin iste request
                                permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                            }
                        }).show();
                    }else {
                        //izin iste request
                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                    }

                }else {
                    //yazmaya git

                    if (mInterstitialAd != null){
                        mInterstitialAd.show(getActivity());
                    }
                    Navigation.findNavController(v).navigate(R.id.notKayitGecis);


                }

            }else {


                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)){
                        Snackbar.make(getView(),"Uygulamanın cihazda veri depolaması için izin gerekli.",Snackbar.LENGTH_INDEFINITE).setAction("İzin ver", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //izin iste request
                                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                            }
                        }).show();
                    }else {
                        //izin iste request
                        permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }

                }else {
                    //yazmaya git
                    if (mInterstitialAd != null){
                        mInterstitialAd.show(getActivity());
                    }
                    Navigation.findNavController(v).navigate(R.id.notKayitGecis);

                }


            }





        });

        binding.settings.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.ayarGecis);
        });




        binding.rv.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.notlarListe.observe(getViewLifecycleOwner(),notListesi -> {
            NotlarAdapter adapter = new NotlarAdapter(requireContext(),notListesi,viewModel,sp,sp2);
            binding.rv.setAdapter(adapter);

        });



        OnBackPressedCallback geriTusu = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                        setEnabled(false);
                        requireActivity().finishAffinity();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),geriTusu);



        return binding.getRoot();
    }





    private void registerLauncher(){
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result){
                    if (mInterstitialAd != null){
                        mInterstitialAd.show(getActivity());
                    }
                    Navigation.findNavController(getView()).navigate(R.id.notKayitGecis);
                }else {
                    Toast.makeText(getContext(),"Not kaydetmek için izin vermelisiniz.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnaSayfaViewModel.class);


    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.notlarYukle();




    }

    @Override
    public void onPause() {
        super.onPause();

    }
}