package com.sm.realonekart;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Catagory extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    ImageButton drawerController,backButton;
    ImageView searcher,acc,cart;
    RelativeLayout head1;
    LinearLayout head;
    EditText search;
    boolean isSearching=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);

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


        GridLayout gridLayout=findViewById(R.id.grid_lay);

        { Intent inn=new Intent(this,Searched.class);
            inn.putExtra("Search","iphone");
            Card c=new Card(this,R.drawable.apple_logo,inn);
            gridLayout.addView(c);
        }
        { Intent inn=new Intent(this,Searched.class);
            inn.putExtra("Search","redmi");
            Card c=new Card(this,R.drawable.redmi_logo,inn);
            gridLayout.addView(c);
        }
        {   Intent inn=new Intent(this,Searched.class);
            inn.putExtra("Search","samsung");
            Card c=new Card(this,R.drawable.samasung_logo,inn);
            gridLayout.addView(c);
        }
        {    Intent inn=new Intent(this,Searched.class);
            inn.putExtra("Search","oppo");
            Card c=new Card(this,R.drawable.oppo_logo,inn);
            gridLayout.addView(c);
        }
        {    Intent inn=new Intent(this,Searched.class);
            inn.putExtra("Search","vivo");
            Card c=new Card(this,R.drawable.vivo_logo,inn);
            gridLayout.addView(c);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        Intent sea=new Intent(this,Searched.class);
        switch (id){
            case R.id.cases:
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
    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"???",Toast.LENGTH_LONG).show();
    }
}