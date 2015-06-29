package com.tinygrip.android.ui.navigation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.tinygrip.android.R;
import com.tinygrip.android.ui.navigation.fragments.DiscoverFragment;
import com.tinygrip.android.ui.navigation.fragments.HomeFragment;
import com.tinygrip.android.ui.navigation.fragments.OfflineFragment;
import com.tinygrip.android.ui.navigation.fragments.ProfileFragment;


/**
 * The main navigation tab pager adapter.
 */
public class MainTabPagerAdapter extends FragmentPagerAdapter {
    private int[] imageResId = {
            R.drawable.ic_action_stars,
            R.drawable.ic_action_stars,
            R.drawable.ic_action_stars,
            R.drawable.ic_action_stars
    };

    private Context context;

    public MainTabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageResId.length;
    }

    @Override
    public Fragment getItem(int position) {
        // Lets load the Home fragment by default and override this fragment value for each of the
        // positions of the tabs (just in case there here is an issue home will be loaded)
        Fragment fragment = HomeFragment.newInstance();
        switch (position) {
            case 0:
                fragment = HomeFragment.newInstance();
                break;
            case 1:
                fragment = ProfileFragment.newInstance();
                break;
            case 2:
                fragment = DiscoverFragment.newInstance();
                break;
            case 3:
                fragment = OfflineFragment.newInstance();
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        // Generate title based on item position
        // https://github.com/codepath/android_guides/wiki/Google-Play-Style-Tabs-using-TabLayout#add-icons-to-tablayout
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}