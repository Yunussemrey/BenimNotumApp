package com.yunusemre.notapp.di;

import android.content.Context;

import androidx.room.Room;

import com.yunusemre.notapp.data.entity.Notlar;
import com.yunusemre.notapp.data.repo.NotlarDaoRepository;
import com.yunusemre.notapp.room.NotlarDao;
import com.yunusemre.notapp.room.Veritabani;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.Single;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public NotlarDaoRepository providerNotlarDaoRepository(NotlarDao notDaoI){ // repo ya notlar dao yu bağladığımız için burada istedi.
        return new NotlarDaoRepository(notDaoI);
    }

        // yukarıda ki repo yu çalıştırması için NotlarDao fonk. tanımladık...
    @Provides
    @Singleton
    public NotlarDao providerNotlarDao(@ApplicationContext Context context){
        Veritabani vt = Room.databaseBuilder(context, Veritabani.class,"not_1.sqlite") // veritabanı dosyamı alıp telefona kopyalaması için
                .createFromAsset("not_1.sqlite").build(); // bu kodla kopyalıyor. hem kopyalıyor hem erişiyor!!

        return vt.getNotlarDao() ;


    }

}
