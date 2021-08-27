package com.sm.realonekart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Card extends LinearLayout{
int width=0;
ImageView iv;
TextView name,price,tag;
//String link="";
Button  buy;
Context context;
    public Card(Context context) {
        super(context);
        this.context=context;
        width=context.getResources().getDisplayMetrics().widthPixels;
        int cardWidth=(width/2)-90;
        LayoutParams lp=new LayoutParams(cardWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30,30,30,30);
        LayoutParams slp=new LayoutParams(cardWidth-40, cardWidth-40);
        LayoutParams flp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setPadding(20,20,20,20);
        this.setLayoutParams(lp);
        this.setBackgroundResource(R.drawable.card_bg);
        this.setOrientation(VERTICAL);

        iv=new ImageView(context);
        iv.setLayoutParams(slp);
        iv.setImageResource(R.drawable.ic_launcher_background);

        price=new TextView(context);
        price.setText("0");
        price.setGravity(Gravity.CENTER);
        price.setTextSize(15f);
        price.setTextColor(0xff353535);

        name=new TextView(context);
        name.setText("No Name");
        name.setGravity(Gravity.CENTER);
        name.setTextSize(20f);
        name.setTextColor(0xff151515);

        buy=new Button(context);
        buy.setText("Buy Now");
        buy.setBackgroundColor(0xffff0000);

        name.setLayoutParams(flp);
        price.setLayoutParams(flp);
        buy.setLayoutParams(flp);

        this.addView(iv);
        this.addView(name);
        this.addView(price);
        this.addView(buy);
    }
    public Card(Context context,ProductData pd) {
        super(context);
        this.context=context;
        width=context.getResources().getDisplayMetrics().widthPixels;
        int cardWidth=(width/2)-90;
        LayoutParams lp=new LayoutParams(cardWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(30,30,30,30);
        LayoutParams slp=new LayoutParams(cardWidth-40, cardWidth-40);
        LayoutParams flp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setPadding(20,20,20,20);
        this.setLayoutParams(lp);
        this.setBackgroundResource(R.drawable.card_bg);
        this.setOrientation(VERTICAL);

        iv=new ImageView(context);
        iv.setLayoutParams(slp);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(pd.images[0]);
                    Log.e("???",pd.getImage(0));
                    Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    iv.setImageBitmap(image);
                } catch(Exception e) {
                    Log.e("card 96??",e.toString());
                }

            }
        }).start();

        price=new TextView(context);
        price.setText(pd.offered+"");
        price.setGravity(Gravity.CENTER);
        price.setTextSize(15f);
        price.setTextColor(0xff353535);

        name=new TextView(context);
        name.setText(pd.name);
        name.setGravity(Gravity.CENTER);
        name.setTextSize(14f);
        name.setTextColor(0xff000000);
        
        tag=new TextView(context);
        //tag.setText(pd.tag);
        tag.setGravity(Gravity.CENTER);
        tag.setTextSize(12f);
        tag.setTextColor(0xff555555);

        buy=new Button(context);
        buy.setText("Buy Now");
        buy.setBackgroundColor(0xffeb1b23);
        buy.setTextColor(0xffffffff);
        name.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,200));
        price.setLayoutParams(flp);
        buy.setLayoutParams(flp);


        this.addView(iv);
        //this.addView(tag);
        this.addView(name);
        this.addView(price);
        this.addView(buy);
    }
    public Card(Context context, int ImageResources, Intent in){
        super(context);
        this.context=context;
        width=context.getResources().getDisplayMetrics().widthPixels;
        int cardWidth=(width/2)-60;
        GridLayout.LayoutParams lp=new GridLayout.LayoutParams(new LayoutParams(cardWidth, LayoutParams.WRAP_CONTENT));
        lp.setMargins(30,30,30,30);
        LayoutParams slp=new LayoutParams(cardWidth-20, cardWidth-20);
        LayoutParams flp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setPadding(10,10,10,10);
        this.setLayoutParams(lp);
        this.setBackgroundResource(R.drawable.card_bg);
        this.setOrientation(VERTICAL);

        iv=new ImageView(context);
        iv.setLayoutParams(slp);
        iv.setImageResource(ImageResources);
        this.addView(iv);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

context.startActivity(in);
            }
        });
    }

}
