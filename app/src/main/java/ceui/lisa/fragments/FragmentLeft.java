package ceui.lisa.fragments;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import ceui.lisa.R;
import ceui.lisa.activities.CoverActivity;
import ceui.lisa.activities.Shaft;

public class FragmentLeft extends BaseFragment {

    public static final String[] TITLES = new String[]{"推荐作品", "热门标签"};

    @Override
    void initLayout() {
        mLayoutID = R.layout.fragment_left;
    }

    @Override
    View initView(View v) {
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        ImageView head = v.findViewById(R.id.head);
        ViewGroup.LayoutParams headParams = head.getLayoutParams();
        headParams.height = Shaft.statusHeight;
        head.setLayoutParams(headParams);
        //toolbar.setPadding(0, Shaft.statusHeight, 0, 0);
        toolbar.setNavigationOnClickListener(view -> {
            if(getActivity() instanceof CoverActivity){
                ((CoverActivity)getActivity()).getDrawer().openDrawer(Gravity.START);
            }
        });
        ViewPager viewPager = v.findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(i == 0) {
                    return new FragmentRecmdIllust();
                }else if(i == 1){
                    return new FragmentHotTag();
                }else {
                    //只有左右两页，所以这个else应该不会触发
                    return new FragmentBlank();
                }
            }

            @Override
            public int getCount() {
                return TITLES.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return TITLES[position];
            }
        });
        TabLayout tabLayout = v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }

    @Override
    void initData() {

    }
}
