package com.singgo.cn.timewindows.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.singgo.cn.timewindows.R;
import com.singgo.cn.timewindows.base.BaseFragment;
import com.singgo.cn.timewindows.widget.CenterViewPager;
import com.singgo.cn.timewindows.widget.CenterViewPagerAdapter;
import com.singgo.cn.timewindows.widget.ZoomOutPageTransformer;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import noman.weekcalendar.WeekCalendar;
import noman.weekcalendar.listener.OnDateClickListener;
import noman.weekcalendar.listener.OnWeekChangeListener;


/**
 * Created by hxz on 16/9/8 14:14.
 */
public class MainFragment extends BaseFragment {

    @BindView(R.id.weekCalendar)
    WeekCalendar weekCalendar;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.cv_viewpager)
    CenterViewPager cvViewpager;

    @Override
    public void initView(View view) {
        super.initView(view);
        init();
        initPager();
    }

    private void init() {

        DateTime date = new DateTime();
        tvDate.setText(date.year().getAsText() + "年" + date.getMonthOfYear() + "月");
        weekCalendar.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(DateTime dateTime) {
                //AppContext.showToast(MainActivity.this,dateTime.toString());
                cvViewpager.setCurrentItem(1,true);

            }

        });
        weekCalendar.setOnWeekChangeListener(new OnWeekChangeListener() {
            @Override
            public void onWeekChange(DateTime d, boolean forward) {
                tvDate.setText(d.year().getAsText() + "年" + d.getMonthOfYear() + "月");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    private void initPager() {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            View card = LayoutInflater.from(getActivity()).inflate(R.layout.view_card,null);
            card.setTag(i);
            views.add(card);
        }
        CenterViewPagerAdapter adapter = new CenterViewPagerAdapter(getActivity(), views);
        cvViewpager.setAdapter(adapter);
        cvViewpager.enableCenterLockOfChilds();
        cvViewpager.setPageTransformer(true, new ZoomOutPageTransformer());

        cvViewpager.setOnPageChangeListener(new CenterViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //weekCalendar.moveToPrevious();
                //weekCalendar.setSelectedDate(new DateTime("2015-01-01"));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

}
