package com.pro.pcmappnew.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.pro.pcmappnew.JoinActivity;
import com.pro.pcmappnew.R;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinFragment extends Fragment {
    private Button btnjoin;
    String text0 ="<br><ul><li>Jika anda belum berpengalaman dalam farming, anda akan diberikan pelatihan online khusus melalui Discord oleh farmer-expert.</li>\n" +
            "<li>Anda akan mendapat proteksi dibawah komunitas PetaniCM</li>\n" +
            "<li>Tidak ada target penghasilan, selama anda dapat menyelesaikan pesanan pemesan yang sudah anda ambil</li>\n" +
            "<li>TIDAK ADA FEE dalam setiap pemesanan</li>\n" +
            "<li>Farmer terhubung langsung dengan pembeli</li></ul>";
    String text1 ="<br><ul><li>Jujur dan dapat berkerja sama</li>\n" +
            "<li> lihat poin diatas</li>\n" +
            "<li> lihat poin diatas</li>\n" +
            "<li> lihat poin diatas</li>\n" +
            "<li> lihat poin diatas</li></ul>";
    public JoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_join,
                container, false);
        HtmlTextView htmlview = (HtmlTextView) rootView.findViewById(R.id.text_html1);
        htmlview.setHtml(text1,new HtmlHttpImageGetter(htmlview));
        HtmlTextView htmlview0 = (HtmlTextView) rootView.findViewById(R.id.text_html0);
        htmlview0.setHtml(text0,new HtmlHttpImageGetter(htmlview0));

        btnjoin = (Button) rootView.findViewById(R.id.btnjoin);

        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JoinActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
