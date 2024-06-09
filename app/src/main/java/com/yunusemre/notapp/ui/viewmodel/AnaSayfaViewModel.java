package com.yunusemre.notapp.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yunusemre.notapp.data.entity.Notlar;
import com.yunusemre.notapp.data.repo.NotlarDaoRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnaSayfaViewModel extends ViewModel {
    public NotlarDaoRepository notDao;
    public MutableLiveData<List<Notlar>> notlarListe;
    @Inject
    public AnaSayfaViewModel(NotlarDaoRepository notDao){
        this.notDao = notDao;
        notlarYukle();
        notlarListe = notDao.notlarListe;
    }
    public void sil(int not_id){
        notDao.sil(not_id);
    }

    public void notlarYukle(){
        notDao.notlarYukle();
    }
}
