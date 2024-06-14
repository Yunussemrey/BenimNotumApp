package com.yunusemre.notapp.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.yunusemre.notapp.R;
import com.yunusemre.notapp.databinding.CardTasarimBinding;
import com.yunusemre.notapp.data.entity.Notlar;
import com.yunusemre.notapp.ui.fragment.AnaSayfaFragmentDirections;
import com.yunusemre.notapp.ui.viewmodel.AnaSayfaViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarAdapter.CardTasarimTutucu> {
    private Context mContext;

    private List<Notlar> notlarList;
    private AnaSayfaViewModel viewModel;
    SharedPreferences sp;
    SharedPreferences sp2;






    public NotlarAdapter(Context mContext, List<Notlar> notlarList,AnaSayfaViewModel viewModel, SharedPreferences sp, SharedPreferences sp2) {
        this.mContext = mContext;
        this.notlarList = notlarList;
        this.viewModel = viewModel;
        this.sp = sp;
        this.sp2 = sp2;


    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{

        private CardTasarimBinding tasarimBinding;
        public CardTasarimTutucu(CardTasarimBinding tasarimBinding) {
            super(tasarimBinding.getRoot());
            this.tasarimBinding = tasarimBinding;
        }
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        CardTasarimBinding binding = CardTasarimBinding.inflate(layoutInflater,parent,false);

        return new CardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        sp = mContext.getSharedPreferences("Renk",Context.MODE_PRIVATE);
        sp2 = mContext.getSharedPreferences("font",Context.MODE_PRIVATE);
        String renk = sp.getString("renk","#000000");
        String renk2 = sp.getString("renk2","#000000");
        int font = sp2.getInt("font", R.font.abeezee);
        int font2 = sp2.getInt("font2",R.font.abeezee);
        Typeface typeface = ResourcesCompat.getFont(mContext,font);
        Typeface typeface1 = ResourcesCompat.getFont(mContext,font2);
        Notlar notlar = notlarList.get(position);
        CardTasarimBinding t = holder.tasarimBinding;
        t.textView4.setText(notlar.getNot_baslik());
        t.textViewNot.setText(notlar.getNot_icerik());
       // t.textTarih.setText(notlar.getTarih()); // tarih
        t.textView4.setTextColor(Color.parseColor(renk));
        t.textViewNot.setTextColor(Color.parseColor(renk2));
        t.textView4.setTypeface(typeface);
        t.textViewNot.setTypeface(typeface1);
        t.deleteNote.setOnClickListener(v -> {
            Snackbar.make(v,notlar.getNot_baslik()+" silinsin mi?",Snackbar.LENGTH_LONG).setAction("Evet",v1 -> {
                viewModel.sil(notlar.getNot_id());
            }).show();
        });
        t.editNote.setOnClickListener(v -> {
            Snackbar.make(v,notlar.getNot_baslik()+" düzenlemek ister misiniz?",Snackbar.LENGTH_LONG).setAction("Evet",v1 -> {
                AnaSayfaFragmentDirections.NotGuncelleGecis gecis = AnaSayfaFragmentDirections.notGuncelleGecis(notlar);
                Navigation.findNavController(v).navigate(gecis);
            }).show();
        });
        t.cardViewNotOkuma.setOnClickListener(v -> {
            AnaSayfaFragmentDirections.NotOkumaGecis gidis = AnaSayfaFragmentDirections.notOkumaGecis(notlar);
            Navigation.findNavController(v).navigate(gidis);
        });






    }

    @Override
    public int getItemCount() {
        return notlarList.size();
    }


}
