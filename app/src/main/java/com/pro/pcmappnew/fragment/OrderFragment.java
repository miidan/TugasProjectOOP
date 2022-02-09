package com.pro.pcmappnew.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.pro.pcmappnew.OrderActivity;
import com.pro.pcmappnew.R;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class OrderFragment extends Fragment {
    String text0 ="<p align=\"justify\">Untuk saat ini kami hanya melakukan pembayaran melalui PayPal, atau malului transfer bank maupun ATM.";
    String text1 ="<p align=\"justify\">PetaniCM akan mengembalikan 100% uang pemesanan ke pemesan jika petani kami tidak menyelesaikan pesanan (2 hari atau 2 × 24 jam, termasuk hari libur) setelah tanggal deadline yang disepakati antara pemesan dan petani.";
    String text2 ="Berikut adalah rule - rule mengenai pemesanan pelayanan:<br><ul><li>Setelah anda men-submit pemesanan, kami akan menguhubungi anda untuk mengkonfirmasi terkait pemesanan yang anda submit kepada kami selambat-lambatnya 1x24 jam</li>" +
            "<li>Pemesan tidak dapat mengcancel apabila status pemesanan sudah \"On Process\"</li>"+
            "<li>......</li></ul>";
    private Button btnorder;
    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order,
                container, false);
        HtmlTextView htmlview = (HtmlTextView) rootView.findViewById(R.id.text_html1);
        htmlview.setHtml(text1,new HtmlHttpImageGetter(htmlview));
        HtmlTextView htmlview0 = (HtmlTextView) rootView.findViewById(R.id.text_html0);
        htmlview0.setHtml(text0,new HtmlHttpImageGetter(htmlview0));
        HtmlTextView htmlview2 = (HtmlTextView) rootView.findViewById(R.id.text_html2);
        htmlview2.setHtml(text2,new HtmlHttpImageGetter(htmlview2));
        btnorder = (Button) rootView.findViewById(R.id.btnorder);

        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);

            }
        });

        return rootView;

    }

}