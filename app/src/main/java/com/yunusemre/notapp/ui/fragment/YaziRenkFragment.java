package com.yunusemre.notapp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.yunusemre.notapp.R;
import com.yunusemre.notapp.databinding.AlertDialogBaslikRenkBinding;
import com.yunusemre.notapp.databinding.AlertDialogWriteFontBinding;
import com.yunusemre.notapp.databinding.FragmentYaziRenkBinding;


public class YaziRenkFragment extends Fragment {
    private FragmentYaziRenkBinding binding;
    SharedPreferences sp;
    SharedPreferences sp2;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editor2;
    AlertDialogBaslikRenkBinding dialogBinding;
    AlertDialogBaslikRenkBinding dialogBinding2;
    AlertDialog alertDialog;
    AlertDialog alertDialog2;
    AlertDialogWriteFontBinding bindingFont;
    AlertDialogWriteFontBinding bindingFont2;
    AlertDialog alertDialogFont;
    AlertDialog alertDialogFont2;
    Typeface typeface;
    Typeface typeface2;
    private AdView banner6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentYaziRenkBinding.inflate(inflater,container,false);

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // banner reklam
        banner6 = binding.adView6;
        AdRequest adRequest = new AdRequest.Builder().build();
        banner6.loadAd(adRequest);



        sp = requireActivity().getSharedPreferences("Renk", Context.MODE_PRIVATE);
        sp2 = requireActivity().getSharedPreferences("font",Context.MODE_PRIVATE);
        editor = sp.edit();
        editor2 = sp2.edit();


            // renkler xml e ulaşım

        // başlık için renk
        binding.btnRenk.setOnClickListener(v -> {
                try {
                    dialogBinding = AlertDialogBaslikRenkBinding.inflate(getLayoutInflater());

                    AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());


                    if (alertDialog != null && alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }

                    alert.setView(dialogBinding.getRoot());

                    alertDialog = alert.create();
                    alertDialog.show();

                    // sp

                    // butonları bul
                    dialogBinding.btnGray.setOnClickListener(v1 -> {
                        editor.putString("renk","#A68B6A");
                        editor.apply();
                        alertDialog.dismiss();
                        String gray = "#A68B6A";
                        binding.textViewExample.setTextColor(Color.parseColor(gray));
                    });

                    dialogBinding.btnRed.setOnClickListener(v1 -> {
                        editor.putString("renk","#D90404");
                        editor.apply();
                        alertDialog.dismiss(); // alert dialogu kapatır.
                        String red = "#D90404";
                        binding.textViewExample.setTextColor(Color.parseColor(red));
                    });
                    dialogBinding.btnTuruncu.setOnClickListener(v1 -> {
                        editor.putString("renk","#F24405");
                        editor.apply();
                        alertDialog.dismiss();
                        String orange = "#F24405";
                        binding.textViewExample.setTextColor(Color.parseColor(orange));
                    });
                    dialogBinding.btnMor.setOnClickListener(v1 -> {
                        editor.putString("renk","#9520B5");
                        editor.apply();
                        alertDialog.dismiss();
                        String purple = "#9520B5";
                        binding.textViewExample.setTextColor(Color.parseColor(purple));
                    });
                    dialogBinding.btnYellow.setOnClickListener(v1 -> {
                        editor.putString("renk","#E6CE16");
                        editor.apply();
                        alertDialog.dismiss();
                        String yellow = "#E6CE16";
                        binding.textViewExample.setTextColor(Color.parseColor(yellow));
                    });
                    dialogBinding.btnYesil.setOnClickListener(v1 -> {
                        editor.putString("renk","#89D995");
                        editor.apply();
                        alertDialog.dismiss();
                        String green = "#89D995";
                        binding.textViewExample.setTextColor(Color.parseColor(green));
                    });
                    dialogBinding.buttonBrown.setOnClickListener(v1 -> {
                        editor.putString("renk","#5B4639");
                        editor.apply();
                        alertDialog.dismiss();
                        String brown = "#5B4639";
                        binding.textViewExample.setTextColor(Color.parseColor(brown));
                    });
                    dialogBinding.buttonVisne.setOnClickListener(v1 -> {
                        editor.putString("renk","#A60053");
                        editor.apply();
                        alertDialog.dismiss();
                        String visne = "#A60053";
                        binding.textViewExample.setTextColor(Color.parseColor(visne));
                    });
                    dialogBinding.buttonLacivert.setOnClickListener(v1 -> {
                        editor.putString("renk","#2F4468");
                        editor.apply();
                        alertDialog.dismiss();
                        String lacivert = "#2F4468";
                        binding.textViewExample.setTextColor(Color.parseColor(lacivert));
                    });
                    dialogBinding.buttonBlack.setOnClickListener(v1 -> {
                        editor.putString("renk","#0D0D0D");
                        editor.apply();
                        alertDialog.dismiss();
                        String black = "#0D0D0D";
                        binding.textViewExample.setTextColor(Color.parseColor(black));
                    });
                }catch (Exception e) {
                    e.printStackTrace();
                }



        });
            // başlık renk deneme
        String renk = sp.getString("renk","#000000");
        binding.textViewExample.setTextColor(Color.parseColor(renk));



        binding.btnRenk2.setOnClickListener(v -> {
            try {
                //notlar için renk
                dialogBinding2 = AlertDialogBaslikRenkBinding.inflate(getLayoutInflater());

                AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());

                if (alertDialog2 != null && alertDialog2.isShowing()) {
                    alertDialog2.dismiss();
                }

                alert.setView(dialogBinding2.getRoot());

                alertDialog2 = alert.create();
                alertDialog2.show();

                dialogBinding2.buttonBlack.setOnClickListener(v1 -> {
                    editor.putString("renk2","#0D0D0D");
                    editor.apply();
                    alertDialog2.dismiss();
                    String black2 = "#0D0D0D";
                    binding.textViewExample2.setTextColor(Color.parseColor(black2));
                });
                dialogBinding2.buttonVisne.setOnClickListener(v1 -> {
                    editor.putString("renk2","#A60053");
                    editor.apply();
                    alertDialog2.dismiss();
                    // örnek metinde renk gösterme
                    String visne2 = "#A60053";
                    binding.textViewExample2.setTextColor(Color.parseColor(visne2));
                });
                dialogBinding2.btnGray.setOnClickListener(v1 -> {
                    editor.putString("renk2","#A68B6A");
                    editor.apply();
                    alertDialog2.dismiss();
                    String gray2 = "#A68B6A";
                    binding.textViewExample2.setTextColor(Color.parseColor(gray2));
                });
                dialogBinding2.btnYesil.setOnClickListener(v1 -> {
                    editor.putString("renk2","#89D995");
                    editor.apply();
                    alertDialog2.dismiss();
                    String green2 = "#89D995";
                    binding.textViewExample2.setTextColor(Color.parseColor(green2));
                });
                dialogBinding2.buttonBrown.setOnClickListener(v1 -> {
                    editor.putString("renk2","#5B4639");
                    editor.apply();
                    alertDialog2.dismiss();
                    String brown2 = "#5B4639";
                    binding.textViewExample2.setTextColor(Color.parseColor(brown2));
                });
                dialogBinding2.buttonLacivert.setOnClickListener(v1 -> {
                    editor.putString("renk2","#2F4468");
                    editor.apply();
                    alertDialog2.dismiss();
                    String lacivert2 = "#2F4468";
                    binding.textViewExample2.setTextColor(Color.parseColor(lacivert2));
                });
                dialogBinding2.btnMor.setOnClickListener(v1 -> {
                    editor.putString("renk2","#9520B5");
                    editor.apply();
                    alertDialog2.dismiss();
                    String purple2 = "#9520B5";
                    binding.textViewExample2.setTextColor(Color.parseColor(purple2));
                });
                dialogBinding2.btnRed.setOnClickListener(v1 -> {
                    editor.putString("renk2","#D90404");
                    editor.apply();
                    alertDialog2.dismiss();
                    String red2 = "#D90404";
                    binding.textViewExample2.setTextColor(Color.parseColor(red2));
                });
                dialogBinding2.btnYellow.setOnClickListener(v1 -> {
                    editor.putString("renk2","#E6CE16");
                    editor.apply();
                    alertDialog2.dismiss();
                    String yellow2 = "#E6CE16";
                    binding.textViewExample2.setTextColor(Color.parseColor(yellow2));
                });
                dialogBinding2.btnTuruncu.setOnClickListener(v1 -> {
                    editor.putString("renk2","#F24405");
                    editor.apply();
                    alertDialog2.dismiss();
                    String orange2 = "#F24405";
                    binding.textViewExample2.setTextColor(Color.parseColor(orange2));
                });
            }catch (Exception e) {
                e.printStackTrace();
            }

        });

        String renk2 = sp.getString("renk2","#000000");
        binding.textViewExample2.setTextColor(Color.parseColor(renk2));



        // başlık için font 1 değiştirme
        binding.btnWriteFont.setOnClickListener(v -> {
            try {
                bindingFont = AlertDialogWriteFontBinding.inflate(getLayoutInflater());

                AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());

                if (alertDialogFont != null && alertDialogFont.isShowing()) {
                    alertDialogFont.dismiss();
                }

                alert.setView(bindingFont.getRoot());

                alertDialogFont = alert.create();
                alertDialogFont.show();

                //yazı stilleri
                bindingFont.btnFont1.setOnClickListener(v1 -> {
                    editor2.putInt("font",R.font.acme);
                    editor2.apply();
                    alertDialogFont.dismiss();
                    typeface = ResourcesCompat.getFont(requireContext(),R.font.acme);
                    binding.textViewExample.setTypeface(typeface);

                });
                bindingFont.btnFont2.setOnClickListener(v1 -> {
                    editor2.putInt("font",R.font.akaya_telivigala);
                    editor2.apply();
                    alertDialogFont.dismiss();
                    typeface = ResourcesCompat.getFont(requireContext(),R.font.akaya_telivigala);
                    binding.textViewExample.setTypeface(typeface);
                });
                bindingFont.btnFont3.setOnClickListener(v1 -> {
                    editor2.putInt("font",R.font.abhaya_libre);
                    editor2.apply();
                    alertDialogFont.dismiss();
                    typeface = ResourcesCompat.getFont(requireContext(),R.font.abhaya_libre);
                    binding.textViewExample.setTypeface(typeface);
                });
                bindingFont.btnFont4.setOnClickListener(v1 -> {
                    editor2.putInt("font",R.font.aclonica);
                    editor2.apply();
                    alertDialogFont.dismiss();
                    typeface = ResourcesCompat.getFont(requireContext(),R.font.aclonica);
                    binding.textViewExample.setTypeface(typeface);
                });
                bindingFont.btnFont5.setOnClickListener(v1 -> {
                    editor2.putInt("font",R.font.abeezee);
                    editor2.apply();
                    alertDialogFont.dismiss();
                    typeface = ResourcesCompat.getFont(requireContext(),R.font.abeezee);
                    binding.textViewExample.setTypeface(typeface);
                });
            }catch (Exception e) {
                e.printStackTrace();
            }


        });

        int font = sp2.getInt("font",R.font.abeezee);
        typeface = ResourcesCompat.getFont(requireContext(),font);
        binding.textViewExample.setTypeface(typeface);


            // notlar için font
        binding.btnWriteFont2.setOnClickListener(v -> {
            try {
                bindingFont2 = AlertDialogWriteFontBinding.inflate(getLayoutInflater());

                AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());

                if (alertDialogFont2 != null && alertDialogFont2.isShowing()) {
                    alertDialogFont2.dismiss();
                }

                alert.setView(bindingFont2.getRoot());

                alertDialogFont2 = alert.create();
                alertDialogFont2.show();
                // butonlar
                bindingFont2.btnFont1.setOnClickListener(v1 -> {
                    editor2.putInt("font2",R.font.acme);
                    editor2.apply();
                    alertDialogFont2.dismiss();
                    typeface2 = ResourcesCompat.getFont(requireContext(),R.font.acme);
                    binding.textViewExample2.setTypeface(typeface2);
                });
                bindingFont2.btnFont2.setOnClickListener(v1 -> {
                    editor2.putInt("font2",R.font.akaya_telivigala);
                    editor2.apply();
                    alertDialogFont2.dismiss();
                    typeface2 = ResourcesCompat.getFont(requireContext(),R.font.akaya_telivigala);
                    binding.textViewExample2.setTypeface(typeface2);
                });
                bindingFont2.btnFont3.setOnClickListener(v1 -> {
                    editor2.putInt("font2",R.font.abhaya_libre);
                    editor2.apply();
                    alertDialogFont2.dismiss();
                    typeface2 = ResourcesCompat.getFont(requireContext(),R.font.abhaya_libre);
                    binding.textViewExample2.setTypeface(typeface2);
                });
                bindingFont2.btnFont4.setOnClickListener(v1 -> {
                    editor2.putInt("font2",R.font.aclonica);
                    editor2.apply();
                    alertDialogFont2.dismiss();
                    typeface2 = ResourcesCompat.getFont(requireContext(),R.font.aclonica);
                    binding.textViewExample2.setTypeface(typeface2);
                });
                bindingFont2.btnFont5.setOnClickListener(v1 -> {
                    editor2.putInt("font2",R.font.abeezee);
                    editor2.apply();
                    alertDialogFont2.dismiss();
                    typeface2 = ResourcesCompat.getFont(requireContext(),R.font.abeezee);
                    binding.textViewExample2.setTypeface(typeface2);
                });

            }catch (Exception e) {
                e.printStackTrace();
            }


        });

        int font2 = sp2.getInt("font2",R.font.abeezee);
        typeface2 = ResourcesCompat.getFont(requireContext(),font2);
        binding.textViewExample2.setTypeface(typeface2);









        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}