package com.sm.realonekart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
ImageButton drawerController,backButton;
ImageView searcher,acc,cart;
RelativeLayout head1;
LinearLayout head;
EditText search;
boolean isSearching=false;
         @Override
    protected void onCreate(Bundle savedInstanceState) {
             int w=getApplicationContext().getResources().getDisplayMetrics().widthPixels;
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);

             backButton=findViewById(R.id.head_back);
             searcher=findViewById(R.id.searcher);
             acc=findViewById(R.id.account);
             cart=findViewById(R.id.cart);
             head1=findViewById(R.id.app_head);
             head=findViewById(R.id.app_head1);
             search=findViewById(R.id.search_p);
             Intent sea=new Intent(this,Searched.class);

             search.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                 @Override
                 public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                     String s=v.getText().toString();
                     //Toast.makeText(getApplicationContext(),"looking for "+s+" case",Toast.LENGTH_LONG).show();
                     sea.putExtra("Search",s);
                     startActivity(sea);
                     head1.setVisibility(View.VISIBLE);
                     head.setVisibility(View.INVISIBLE);
                     search.setText("");
                     isSearching=false;
                     return true;
                 }
             });
             searcher.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     head1.setVisibility(View.INVISIBLE);
                     head.setVisibility(View.VISIBLE);
                     isSearching=true;
                 }
             });
             backButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     head1.setVisibility(View.VISIBLE);
                     head.setVisibility(View.INVISIBLE);
                     search.setText("");
                     isSearching=false;
                 }
             });
             Intent in=new Intent(this,LogOrSign.class);
             acc.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     startActivity(in);
                 }
             });
             cart.setOnClickListener(this);

             ImageView im1=findViewById(R.id.sc1);
             ImageView im2=findViewById(R.id.sc2);
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         URL url = new URL("https://realonkart.com/wp-content/uploads/2020/12/Free_Shippinng-1920x520-1920x520-4-1400x379.jpg");
                         Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                         im1.setImageBitmap(Bitmap.createScaledBitmap(image,w-20,500,false));
                     } catch(Exception e) {
                         Log.e("?????",e.toString());
                     }
                 }
             }).start();
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         URL url = new URL("https://realonkart.com/wp-content/uploads/2020/12/Prepaid_Discount-1920x520-1920x520-1-1400x379.jpg");
                         Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                         im2.setImageBitmap(Bitmap.createScaledBitmap(image,w-20,500,false));
                     } catch(Exception e) {
                         Log.e("?????",e.toString());
                     }
                 }
             }).start();


             LinearLayout ll = findViewById(R.id.latest);
             for(ProductData pd:DataContainer.custom){
                 ll.addView(new Card(this,pd));
             }

             LinearLayout ll1 = findViewById(R.id.best_deal);
             for(ProductData pd:DataContainer.custom){
                 ll1.addView(new Card(this,pd));
             }

             LinearLayout ll2 = findViewById(R.id.trending);
             for(ProductData pd:DataContainer.custom){
                 ll2.addView(new Card(this,pd));
             }

             LinearLayout ll3 = findViewById(R.id.featured);
             for(ProductData pd:DataContainer.custom){
                 ll3.addView(new Card(this,pd));
             }

       DrawerLayout drawer = findViewById(R.id.drawer_layout);
       NavigationView nav=findViewById(R.id.nav_view);
       nav.setNavigationItemSelectedListener(this);
        drawerController=findViewById(R.id.drawer);
        drawerController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

         }

    /*private void setupCarousel() {
        Carousel carousel=findViewById(R.id.car);
        int num=colors.length;
        if(carousel!=null){
            carousel.setAdapter(new Carousel.Adapter() {
                @Override
                public int count() {
                    return num;
                }

                @Override
                public void populate(View view, int index) {
                    if(view instanceof MaterialCardView){
                        view.setBackgroundColor(colors[index]);
                    }
                }

                @Override
                public void onNewItem(int index) {

                }
            });
        }
    }*/

    @Override
    public void onBackPressed() {
        if(isSearching){
            head1.setVisibility(View.VISIBLE);
            head.setVisibility(View.INVISIBLE);
            search.setText("");
            isSearching=false;
        }else{
            finishAffinity();
        }

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"generally",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        Intent sea=new Intent(this,Searched.class);
        switch (id){
            case R.id.cases:{Intent in=new Intent(this,Catagory.class);startActivity(in);}
            break;
            case R.id.apple:{sea.putExtra("Search","iphone");
                startActivity(sea);}
            break;
            case R.id.samsung:
            case R.id.oppo:
            case R.id.vivo:
            case R.id.redmi:{
                String s=item.getTitle().toString();
                sea.putExtra("Search",s);
                startActivity(sea);}
                break;
            default: this.onClick(null);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}