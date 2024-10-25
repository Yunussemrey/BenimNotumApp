package com.yunusemre.notapp.ui.fragment;

import static androidx.browser.customtabs.CustomTabsClient.getPackageName;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yunusemre.notapp.R;
import com.yunusemre.notapp.databinding.FragmentGirisBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dagger.multibindings.ElementsIntoSet;


public class GirisFragment extends Fragment {
    private FragmentGirisBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private FirebaseAuth auth;
    private FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentGirisBinding.inflate(inflater,container,false);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
       preferences = getActivity().getSharedPreferences("Giris", Context.MODE_PRIVATE);
       editor = preferences.edit();

       binding.btnKayit.setOnClickListener(v -> {
           String ePosta = binding.ePostaEditText.getText().toString();
           String ad = binding.adEditText.getText().toString();
           String sifre = binding.sifreEditText.getText().toString();
            if (ad.isEmpty() || sifre.isEmpty() || ePosta.isEmpty()){
                Toast.makeText(getContext(), "Lütfen gerekli alanları doldurun", Toast.LENGTH_SHORT).show();
            }else {
               auth.createUserWithEmailAndPassword(ePosta,sifre).addOnCompleteListener(task -> {
                  if (task.isSuccessful()) {
                      Toast.makeText(getContext(), "Kayıt işlemi başarılı", Toast.LENGTH_SHORT).show();
                      Navigation.findNavController(v).navigate(R.id.girisToAnasayfa);
                  }
               }).addOnFailureListener(e -> {
                   Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
               });

                editor.putString("kayitAd",ad);
                editor.apply();


            }

       });


       binding.btnGiris.setOnClickListener(v -> {

           String sifre = binding.sifreEditText.getText().toString();
            String email = binding.ePostaEditText.getText().toString();
           auth.signInWithEmailAndPassword(email,sifre).addOnCompleteListener(task -> {
              if (task.isSuccessful()) {
                  Toast.makeText(getContext(), "Giriş yapıldı", Toast.LENGTH_SHORT).show();
                  Navigation.findNavController(v).navigate(R.id.girisToAnasayfa);
              }
           }).addOnFailureListener(e -> {
               Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
           });


       });

       binding.sifreYenile.setOnClickListener(v -> {
        auth.sendPasswordResetEmail(user.getEmail()).addOnCompleteListener(task -> {
           if (task.isSuccessful()) {
               Toast.makeText(getContext(), "Şifre doğrulama e-postası gönderildi.", Toast.LENGTH_SHORT).show();
           }
        }).addOnFailureListener(e -> {
            Toast.makeText(getContext(), "gönderilemedi", Toast.LENGTH_SHORT).show();
        });
       });
        binding.hesapSifirla.setOnClickListener(v -> {
            auth.signOut();
            Toast.makeText(getContext(), "sıfırlandı", Toast.LENGTH_SHORT).show();
            binding.ePostaEditText.setVisibility(View.VISIBLE);
            binding.btnKayit.setVisibility(View.VISIBLE);
            binding.btnGiris.setVisibility(View.GONE);
            editor.remove("kayitAd");
            editor.apply();

        });

       // tarih ayarı !!
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        String currentDate = sdf.format(calendar.getTime());

        binding.dateText.setText(currentDate);


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

    @Override
    public void onResume() {
        super.onResume();
        String kayitliAd = preferences.getString("kayitAd","");
       if (user != null) {
           binding.btnKayit.setVisibility(View.GONE);
           binding.btnGiris.setVisibility(View.VISIBLE);
           binding.sifreYenile.setVisibility(View.VISIBLE);
           binding.adTextView.setText(kayitliAd);
           binding.ePostaEditText.setText(user.getEmail());
           binding.ePostaEditText.setVisibility(View.GONE);

       }else {
           binding.btnKayit.setVisibility(View.VISIBLE);
           binding.sifreYenile.setVisibility(View.GONE);
           binding.btnGiris.setVisibility(View.GONE);
           binding.adTextView.setVisibility(View.GONE);
       }


    }
}