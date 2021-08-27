package com.sm.realonekart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Searched extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    ImageButton drawerController,backButton;
    ImageView searcher,acc,cart;
    RelativeLayout head1;
    LinearLayout head;
    EditText search;
    boolean isSearching=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int w=getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        Intent i=getIntent();
        String data=i.getStringExtra("Search");
        Products p=new Products("filtered");
        Products all=DataContainer.custom;
        for(ProductData pd:all){
            if(pd.getName().toLowerCase().contains(data.toLowerCase())){
                p.add(pd);
            }
        }
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawerController=findViewById(R.id.drawer);
        backButton=findViewById(R.id.head_back);
        searcher=findViewById(R.id.searcher);
        head1=findViewById(R.id.app_head);
        head=findViewById(R.id.app_head1);
        search=findViewById(R.id.search_p);
        acc=findViewById(R.id.account);
        cart=findViewById(R.id.cart);
        NavigationView nav=findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);

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
        drawerController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
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
        try {
        ViewSwitcher v = findViewById(R.id.mainView);
            v.showNext();
    }catch (Exception e){
        Log.e("????????????",e.toString());
    }
        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    LinearLayout column=null;
    LinearLayout row=findViewById(R.id.row);

Log.e("???????",(p.size()+1)/2+" number of row");
for(int x=0;x<p.size();x+=2){
    column=new LinearLayout(getApplicationContext());
    for(int j=0;j<2;j++){
        int pos=x+j;
        if(pos<p.size()){
            Log.e("????",pos+"--");
            column.addView(new Card(getApplicationContext(),p.get(pos)));
        }
            }
    row.addView(column);
}

    }


    @Override
    public void onBackPressed() {
        if(isSearching){
                    head1.setVisibility(View.VISIBLE);
                    head.setVisibility(View.INVISIBLE);
                    search.setText("");
                    isSearching=false;
        }else{
        super.onBackPressed();}
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"???",Toast.LENGTH_LONG).show();
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