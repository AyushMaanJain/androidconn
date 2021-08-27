package com.sm.realonekart;

import android.content.Intent;
import android.os.Bundle;
import org.json.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.PersistableBundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Loading_screen extends AppCompatActivity {
//html
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        TextView status=findViewById(R.id.status_text);
        Products latest=new Products("latest");
        Products deal=new Products("deal");
        Products trend=new Products("trend");
        Products featured=new Products("featured");
        Intent in=new Intent(this,MainActivity.class);
        //startActivity(in);

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean success=false;
                HttpURLConnection urlConnection = null;
                byte[] b="<div class=\"row equalize-box large-columns-5 medium-columns-3 small-columns-2 row-small row-full-width has-shadow row-box-shadow-1 row-box-shadow-2-hover slider row-slider slider-nav-reveal slider-nav-push\" data-flickity-options='{\"imagesLoaded\": true, \"groupCells\": \"100%\", \"dragThreshold\" : 5, \"cellAlign\": \"left\",\"wrapAround\": true,\"prevNextButtons\": true,\"percentPosition\": true,\"pageDots\": false, \"rightToLeft\": false, \"autoPlay\" : false}'>".getBytes();
                byte[] b1="<div".getBytes();
                byte[] b2="</div>".getBytes();

                byte[] d1="<noscript><img width=\"1\" height=\"1\" src=\"".getBytes();
                byte[] d2="<noscript><img width=\"300\" height=\"300\" src=\"".getBytes();
                byte[] d3="<p class=\"category uppercase is-smaller no-text-overflow product-cat op-7\">".getBytes();
                byte[] d4="class=\"woocommerce-LoopProduct-link woocommerce-loop-product__link\">".getBytes();

                byte[] p1="<del aria-hidden=\"true\"><span class=\"woocommerce-Price-amount amount\"><bdi><span class=\"woocommerce-Price-currencySymbol\">&#8377;</span>".getBytes();
                byte[] p2="<ins><span class=\"woocommerce-Price-amount amount\"><bdi><span class=\"woocommerce-Price-currencySymbol\">&#8377;</span>".getBytes();

                byte[] l1="<p class=\"name product-title woocommerce-loop-product__title\"><a href=\"".getBytes();
                byte[] l2="cart=".getBytes();

                int target=0,t1=0,t2=0,ti=0,tii=0,tv=0,tvi=0,tx=0,txi=0,tl=0,tli=0;
                int divCount=0;
                int recordPath=0,recordcont=1;
                boolean record=false;
                boolean listener=false;
                try {
                    URL url = new URL("https://realonkart.com/");
                    urlConnection= (HttpURLConnection) url.openConnection();

                    BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    int i;
                    String s="";
                    ProductData pd=new ProductData();
                    //status.setText("fetching data");
                    while((i=in.read())!=-1){
                        if(target==b.length){
                            listener=true;
                            //System.out.println("start recording");
                            target=0;
                        }
                        if(ti==d1.length||tii==d2.length){
                            ti=0;tii=0;
                            record=true;
                            recordPath=1;
                        }
                        if(tv==d3.length){
                            tv=0;
                            record=true;
                            recordPath=2;
                        }
                        if(tvi==d4.length){
                            tvi=0;
                            record=true;
                            recordPath=4;
                        }
                        if(tx==p1.length){
                            tx=0;
                            record=true;
                            recordPath=5;
                        }
                        if(txi==p2.length){
                            txi=0;
                            record=true;
                            recordPath=6;
                        }
                        if(tl==l1.length){
                            tl=0;
                            record=true;
                            recordPath=3;
                        }
                        if(tli==l2.length){
                            tli=0;
                            record=true;
                            recordPath=7;
                        }

                        if(t1==b1.length){
                            divCount++;
                            t1=0;
                            //System.out.println(divCount);
                        }

                        if(t2==b2.length){
                            divCount--;
                            t2=0;
                            //System.out.println(divCount);
                        }
                        if(divCount==-1){
                            listener=false;
                            divCount=0;
                            recordcont++;
                            //System.out.println("Stop recording");

                        }

                        char c=(char) i;

                        if(listener){

                            if(record){
                                if(c=='"'||c=='<'){
                                    record=false;
                                    switch (recordPath){
                                        case 1:pd.setImage(s);
                                            break;
                                        case 2:s=s.replace("&amp;", "AND");pd.setTag(s);
                                            break;
                                        case 3:pd.setLink(s);
                                            break;
                                        case 4:pd.setName(s);
                                            break;
                                        case 5:pd.setOldprice(s);
                                            break;
                                        case 6:pd.setOffered(s);
                                            break;
                                        case 7:{pd.setId(s);
                                        switch (recordcont){
                                            case 1:latest.add(pd);
                                                break;
                                            case 2:deal.add(pd);
                                                break;
                                            case 3:trend.add(pd);
                                                break;
                                            case 4:featured.add(pd);
                                        }
                                        pd=new ProductData();
                                        }

                                    }
                                    s="";
                                }else{
                                    s+=c;
                                }
                            }

                            if(c==d4[tvi]){tvi++;}else{tvi=0;}
                            if(c==d3[tv]){tv++;}else{tv=0;}
                            if(c==p2[txi]){txi++;}else{txi=0;}
                            if(c==p1[tx]){tx++;}else{tx=0;}
                            if(c==l2[tli]){tli++;}else{tli=0;}
                            if(c==l1[tl]){tl++;}else{tl=0;}
                            if(c==d2[tii]){tii++;}else{tii=0;}
                            if(c==d1[ti]){ti++;}else{ti=0;}
                            if(c==b1[t1]){t1++;}else{t1=0;}
                            if(c==b2[t2]){t2++;}else{t2=0;}
                        }else{
                            if(c==b[target]){target++;}else{target=0;}}
                    }

                    success=true;
                    status.setText("connected");
                }    catch (Exception ex) {
                    //status.setText("connection error\n"+ex.toString());
                    Log.e("?????",ex.toString());
                } finally {
                    urlConnection.disconnect();
                    if(success){
                        DataContainer.latest=latest;
                        DataContainer.trending=trend;
                        DataContainer.deal=deal;
                        DataContainer.featured=featured;
                       startActivity(in);
                    }else{
                        status.setText("Connection problem");
                    }
                }
            }
        }).start();


    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
Intent in=new Intent(this,MainActivity.class);
       new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL("https://realonkart.com/wp-json/wc/store/products");
                    HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();

                    JsonReader reader=new JsonReader(new InputStreamReader(urlConnection.getInputStream()));
                    Products ps=new Products("all");
reader.beginArray();int i=0;
while (reader.hasNext()) {
    //Log.e("??", "yet"+i);
    ProductData pd=new ProductData();
    reader.beginObject();
    while (reader.hasNext()) {
         String name=reader.nextName();
         //Log.e("??",name);
if(name.equals("id")){
    pd.setId(reader.nextInt());
    //Log.e("??",reader.nextInt()+"");
}
else if(name.equals("name")) {
    pd.setName(reader.nextString() + "");
    //Log.e("??", reader.nextString());
}
else if(name.equals("prices")){

    reader.beginObject();
    while (reader.hasNext()){
        String priceName=reader.nextName();
        if(priceName.equals("price")){
            pd.setOffered(reader.nextString());
        }
        else if(priceName.equals("regular_price")){
           pd.setOldprice(reader.nextString());
        }
        else{
        reader.skipValue();
    }}
    reader.endObject();
}
else if(name.equals("images")){
    reader.beginArray();int x=0;
    while(reader.hasNext()){
        if(x<4){
        reader.beginObject();
        while (reader.hasNext()){
            String imageName=reader.nextName();
            if(imageName.equals("src"))
           pd.setImage(reader.nextString(),x);
            //Log.e("-??-",reader.nextString());
            else
            reader.skipValue();
        }
        reader.endObject();x++;}
        else {reader.skipValue();}
    }
    reader.endArray();
}
else{
    //Log.e("??","ignore the "+name);
    reader.skipValue();
        }
    }
    reader.endObject();i++;
    ps.add(pd);
    Log.e("next","product");
}reader.endArray();
DataContainer.custom=ps;
startActivity(in);
                }catch (Exception e){
                    Log.e("??",e.toString());
                }
            }
        }).start();

    }
}