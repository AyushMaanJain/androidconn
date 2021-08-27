package com.sm.realonekart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;

public class Products extends ArrayList<ProductData>{
    String catagory="";
    public Products(String catagory) {
        this.catagory = catagory;
    }

}
class ProductData {

   String[] images=new String[4];
   String name="";
   int id=0;
   float oldprice=0;
   float offered=0;

    public String getImage(int index) {
        return images[index];
    }

    public void setImage(String images,int index) {
        this.images[index] = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {

        this.oldprice =Float.parseFloat(oldprice)/100;
        Log.e("-??-",this.oldprice+"::"+oldprice);
    }

    public float getOffered() {
        return offered;
    }

    public void setOffered(String offered) {

        this.offered = Float.parseFloat(offered)/100;
        Log.e("-??-",this.offered+"::"+offered);
    }
}
