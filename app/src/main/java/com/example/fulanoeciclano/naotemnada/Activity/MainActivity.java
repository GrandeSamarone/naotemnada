package com.example.fulanoeciclano.naotemnada.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fulanoeciclano.naotemnada.Adapter.TabAdapter;
import com.example.fulanoeciclano.naotemnada.Page_Usuario.Activity_Usuario;
import com.example.fulanoeciclano.naotemnada.R;
import com.example.fulanoeciclano.naotemnada.RecicleView.wifiAdapterRec;
import com.example.fulanoeciclano.naotemnada.fragments.GeralFragment;
import com.example.fulanoeciclano.naotemnada.fragments.LocalizacaoFragment;
import com.example.fulanoeciclano.naotemnada.fragments.MapsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private wifiAdapterRec adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private Intent pagcadastrar;
    private TextView nomedousuario;
    private Intent intentaddwifi;
    private  FloatingActionButton fab;

    private FirebaseAuth mFirebaseAuth;
    //icones pretos
    int[] tabIcons_black = {
            R.drawable.ic_public_black_24dp,
            R.drawable.ic_my_location_black_24dp,
            R.drawable.ic_map_black_24dp
    };
    //icones brancos
    int[] tabIcons_white={
            R.drawable.ic_public_white_24dp,
            R.drawable.ic_my_location_whit_24dp,
            R.drawable.ic_map_whit_24dp
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomedousuario =findViewById(R.id.WifiLegenda);




               //botao + wifi
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentaddwifi = new Intent(MainActivity.this, Activity_Usuario.class);
                startActivity(intentaddwifi);

               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
            }
        });

        //Toolbar
       toolbar = (Toolbar) findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Wifi");
        setSupportActionBar(toolbar);

        //view pager
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        //tablayout
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));
        //viewPage selecao dos menu,Mudanca de cores
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            public void onPageSelected(int position) {

                switch (position){
                    case 0:{
                        tabLayout.getTabAt(0).setIcon(tabIcons_white[0]);
                        tabLayout.getTabAt(1).setIcon(tabIcons_black[1]);
                        tabLayout.getTabAt(2).setIcon(tabIcons_black[2]);
                        break;
                    }
                    case 1 :{
                        tabLayout.getTabAt(0).setIcon(tabIcons_black[0]);
                        tabLayout.getTabAt(1).setIcon(tabIcons_white[1]);
                        tabLayout.getTabAt(2).setIcon(tabIcons_black[2]);
                        break;
                    }
                    case 2 :{
                        tabLayout.getTabAt(0).setIcon(tabIcons_black[0]);
                        tabLayout.getTabAt(1).setIcon(tabIcons_black[1]);
                        tabLayout.getTabAt(2).setIcon(tabIcons_white[2]);
                        break;
                    }
                }
            }


            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    //icones
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons_white[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons_black[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons_black[2]);

    }




       //fragments
    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFrag(new GeralFragment(), "ONE");
        adapter.addFrag(new LocalizacaoFragment(), "TWO");
        adapter.addFrag(new MapsFragment(), "THREE");
        viewPager.setAdapter(adapter);


    }
 //Menu
    public boolean onCreateOptionsMenu(Menu menu) {
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_logado, menu);
            return true;
        }else{
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return true;
        }
    }

    //menu do toolbar
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cadastrar:
               pagcadastrar = new Intent(MainActivity.this,Autenticacao.class);
               startActivity(pagcadastrar);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
