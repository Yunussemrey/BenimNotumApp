package com.yunusemre.notapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunusemre.notapp.R;
import com.yunusemre.notapp.databinding.FragmentHakkimdaBinding;


public class HakkimdaFragment extends Fragment {
        private FragmentHakkimdaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHakkimdaBinding.inflate(inflater,container,false);


        binding.textViewhakkimda.setText(" 'Benim Notum' uygulaması tamamen güvenilir depolama hizmeti veren " +
                "basit arayüze sahip ve modern kullanıma olanak tanıyan şık ve sade görünümüyle kullanıcılara android cihazlarında " +
                "verilerini kaydetme imkanı sunar. "+ " Türk Android Geliştiricisi tarafından Türkiye' deki ve diğer ülkelerde ki kullanıcılar için " +
                "Android cihazlarında istedikleri özel bilgilerini(verilerini) hiçbir maddi veya manevi unsur karşılığında 3. taraf kişi veya kurumlarla veri paylaşımı " +
                "olmayan not tutma uygulaması 'Benim Notum' geliştirilmiştir.");



        return binding.getRoot();
    }
}