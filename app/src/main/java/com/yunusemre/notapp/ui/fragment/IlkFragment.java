package com.yunusemre.notapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunusemre.notapp.R;
import com.yunusemre.notapp.databinding.FragmentIlkBinding;


public class IlkFragment extends Fragment {
    private FragmentIlkBinding binding;
    private int süre = 500; // 5 saniye
    private Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIlkBinding.inflate(inflater,container,false);

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Navigation.findNavController(requireView()).navigate(R.id.ilkToAnasayfa);
            }
        }, süre);

        return binding.getRoot();
    }
}