package com.example.medic.HelloPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.medic.AuthActivity;
import com.example.medic.R;

import java.util.ArrayList;
import java.util.List;

import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

public class OnBoardActivity extends AppCompatActivity {

    ViewPager onBoardListElement;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        onBoardListElement = findViewById(R.id.viewPager);
        next = findViewById(R.id.btnSkip);
        ScrollingPagerIndicator indicator = findViewById(R.id.indicator);

        onBoardOneFragment fragment1 = new onBoardOneFragment().newInstance(R.drawable.fragmentimage1);
        onBoardTwoFragment fragment2 = new onBoardTwoFragment().newInstance(R.drawable.fragmentimage2);
        onBoardThreeFragment fragment3 = new onBoardThreeFragment().newInstance(R.drawable.fragmentimage3);

        List<Fragment> onBoardList = new ArrayList<>();
        onBoardList.add(fragment1);
        onBoardList.add(fragment2);
        onBoardList.add(fragment3);

        OnBoardAdapter adapter = new OnBoardAdapter(getSupportFragmentManager(), onBoardList);
        onBoardListElement.setAdapter(adapter);
        indicator.attachToPager(onBoardListElement);
        onBoardListElement.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(onBoardListElement.getCurrentItem() == 2) {
                    next.setText("Завершить");
                } else {
                    next.setText("Пропустить");
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2) {
                    next.setText("Завершить");
                } else {
                    next.setText("Пропустить");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(onBoardListElement.getCurrentItem() == 2) {
                    next.setText("Завершить");
                } else {
                    next.setText("Пропустить");
                }
            }
        });
        next.setOnClickListener(v -> {
            nextBtn(onBoardListElement.getCurrentItem());
        });

    }

    public void nextBtn(int position) {
        if(position == 2) {
            Intent auth = new Intent(this, AuthActivity.class);
            startActivity(auth);
            finish();
        }
        onBoardListElement.beginFakeDrag();
        onBoardListElement.fakeDragBy(-800f);
        onBoardListElement.endFakeDrag();
    }

}