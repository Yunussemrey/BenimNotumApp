package com.yunusemre.notapp.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.yunusemre.notapp.data.entity.Notlar;
import com.yunusemre.notapp.room.NotlarDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NotlarDaoRepository { // bütün view modellerde tek bağımlılık burası

    public MutableLiveData<List<Notlar>> notlarListe = new MutableLiveData<>();

    private NotlarDao notDaoI;

    public NotlarDaoRepository(NotlarDao notDaoI){
        this.notDaoI = notDaoI;
    }

    public void kaydet (String baslik,String icerik, String tarih){ // notDao da Insert işlemi oluşturduk ve burada kaydette uyguladık.
        Notlar yeniNot = new Notlar(0,baslik,icerik,tarih);
        notDaoI.kaydet(yeniNot).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() { // buralara bişey yazmayacağız. bizim için kaydetme fonk. çalışması yeterli!
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void guncelle(int id,String baslik,String icerik, String tarih){
        Notlar guncelNot = new Notlar(id,baslik,icerik,tarih);
        notDaoI.guncelle(guncelNot).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() { // buralara bişey yazmayacağız. bizim için kaydetme fonk. çalışması yeterli!
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void sil(int not_id){
        Notlar silNot = new Notlar(not_id,"","","");
        notDaoI.sil(silNot).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() { // buralara bişey yazmayacağız. bizim için kaydetme fonk. çalışması yeterli!
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() { // işlem bittikten sonra kişileri yükle
                        notlarYukle();
                    }

                    @Override
                    public void onError(Throwable e) {}
                });

    }

    public void notlarYukle(){ // artık notları veritabanından yükleyeceğiz. o yüzden NotlarDao interface ile işlem yapacağız!
        /*ArrayList<Notlar> list = new ArrayList<>();
        Notlar not1 = new Notlar(1,"Banka Şifreleri","Garanti: 2521 İş Bankası: 4422");
        Notlar not2 = new Notlar(2,"Uygulamalar","Benim Notum,Hesapla....");
        Notlar not3 = new Notlar(3,"Sosyal Medya Şifreleri","İnstagram: 654655 Facebook: 565615");
        Notlar not4 = new Notlar(4,"Dersler","Mat,Fizik,Kimya,Bio");
        list.add(not1);
        list.add(not2);
        list.add(not3);
        list.add(not4);
        notlarListe.setValue(list);*/

        notDaoI.notlarıYukle().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Notlar>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(List<Notlar> notlars) { // veritabanından gelen bilgiyi temsil ediyor ve live data ya atıyoruz..
                notlarListe.setValue(notlars);
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }


}
