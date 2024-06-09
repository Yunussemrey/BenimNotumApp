package com.yunusemre.notapp.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.yunusemre.notapp.data.repo.NotlarDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NotGuncelleViewModel extends ViewModel {
        public NotlarDaoRepository notDao;
        @Inject
        public NotGuncelleViewModel(NotlarDaoRepository notDao){
            this.notDao = notDao;
        }
    public void guncelle(int id,String baslik,String icerik){
        notDao.guncelle(id,baslik,icerik);

    }
}
