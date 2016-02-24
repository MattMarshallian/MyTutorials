package com.example.tutorial.marcin.manggha1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.tutorial.marcin.mylibrary.general.containers.FeatureCoverFlow;

import java.util.ArrayList;

public class AllExhibitsCarouselActivity extends AppCompatActivity {

    private FeatureCoverFlow mCoverFlow;
    private CoverFlowAdapter mAdapter;
    private ArrayList<ExhibitionEntry> mData = new ArrayList<>(0);
    private TextSwitcher mTitle;
    private TextSwitcher mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exhibits_carousel);

        mData.add(new ExhibitionEntry(R.drawable.gejsze, R.string.gejsze, "To są fajne japońskie laseczki. Niezłe sztuki ogólnie rzecz biorąc, a pod tapetą i tak nikt nie widzi ich wieku."));
        mData.add(new ExhibitionEntry(R.drawable.iaidoka_seiza, R.string.Iaidoka_seiza, "To jest jakiś japoński macho. Gościu jest spoko o ile się nie wkurzy."));
        mData.add(new ExhibitionEntry(R.drawable.gejsza, R.string.gejsza, "Japońskie laski zawsze są spoko. Nawet taka w staromodnym stylu ma swój nieodparty urok. Trzeba tylko uważać na macho."));
        mData.add(new ExhibitionEntry(R.drawable.samuraj_color, R.string.samuraj_color, "Ten gość ma dziwny ubiór, ale ogólnie jest bardzo spoko. Z powodu tego dziwnego ubioru musi się czuć bardzo nieszczęśliwy. Chyba że ma w pobliżu jakąś laseczkę."));

        mTitle = (TextSwitcher) findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(AllExhibitsCarouselActivity.this);
                TextView textView = (TextView) inflater.inflate(R.layout.item_title, null);
                return textView;
            }
        });

        mDescription = (TextSwitcher) findViewById(R.id.description);
        mDescription.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(AllExhibitsCarouselActivity.this);
                TextView textView = (TextView) inflater.inflate(R.layout.item_description, null);
                return textView;
            }
        });




        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        Animation desc_in = AnimationUtils.loadAnimation(this, R.anim.appear_in);
        Animation desc_out = AnimationUtils.loadAnimation(this, R.anim.appear_out);
        mDescription.setInAnimation(desc_in);
        mDescription.setOutAnimation(desc_out);

        mAdapter = new CoverFlowAdapter(this);
        mAdapter.setData(mData);
        mCoverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        mCoverFlow.setAdapter(mAdapter);

        mCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start new activity
                selectExibition(position);
            }
        });

        mCoverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(getResources().getString(mData.get(position).titleResId));
                mDescription.setText(mData.get(position).description);
            }

            @Override
            public void onScrolling() {
                mTitle.setText("");
                // mDescription.setText("");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coverflow_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void selectExibition(int position) {
        Intent intent = new Intent(this, SingleExibitActivity.class);

        switch (position) {
            case 0:
                intent.putExtra("passed_param_1", "Ślad");
                intent.putExtra("zone", 1);
                break;

            case 1:
                intent.putExtra("passed_param_1", "Takayuki Hara");
                intent.putExtra("zone", 2);
                break;

            case 2:
                intent.putExtra("passed_param_1", "Estetyka QR kodu");
                intent.putExtra("zone", 3);
                break;

            case 3:
                intent.putExtra("passed_param_1", "Aktorzy, lalki i gra cieni");
                intent.putExtra("zone", 4);
                break;

            default:
                break;
        }

        intent.putExtra("exhibit", position);
        startActivity(intent);
    }
}
