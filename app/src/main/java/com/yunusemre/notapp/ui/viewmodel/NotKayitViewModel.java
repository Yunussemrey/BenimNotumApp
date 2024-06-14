package com.yunusemre.notapp.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.yunusemre.notapp.data.repo.NotlarDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NotKayitViewModel extends ViewModel {

    public NotlarDaoRepository notDao;
    @Inject
    public NotKayitViewModel(NotlarDaoRepository notDao){
        this.notDao = notDao;
    }
    public void kaydet (String baslik,String icerik,String tarih){
       notDao.kaydet(baslik,icerik,tarih);
    }
}
