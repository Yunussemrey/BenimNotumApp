package com.yunusemre.notapp.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.yunusemre.notapp.data.repo.NotlarDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NotOkumaViewModel extends ViewModel {

        public NotlarDaoRepository notDao;

        @Inject
        public NotOkumaViewModel(NotlarDaoRepository notDao){
            this.notDao = notDao;
        }

        public void sil(int not_id){
            notDao.sil(not_id);
        }

    }

