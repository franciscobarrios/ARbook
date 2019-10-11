package dev.appy.ar.android.arbook.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import dev.appy.ar.android.arbook.fragment.PageFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<String> content;

    public MainPagerAdapter(@NonNull FragmentManager fm, List<String> content) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.content = content;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new PageFragment().newInstance(content.get(0));
            case 1:
                return new PageFragment().newInstance(content.get(1));
            case 2:
                return new PageFragment().newInstance(content.get(2));
            case 3:
                return new PageFragment().newInstance(content.get(3));
            case 4:
                return new PageFragment().newInstance(content.get(4));
            case 5:
                return new PageFragment().newInstance(content.get(5));
            case 6:
                return new PageFragment().newInstance(content.get(6));
            case 7:
                return new PageFragment().newInstance(content.get(7));
            case 8:
                return new PageFragment().newInstance(content.get(8));
            case 9:
                return new PageFragment().newInstance(content.get(9));
            case 10:
                return new PageFragment().newInstance(content.get(10));
            case 11:
                return new PageFragment().newInstance(content.get(11));
            case 12:
                return new PageFragment().newInstance(content.get(12));
            case 13:
                return new PageFragment().newInstance(content.get(13));
            case 14:
                return new PageFragment().newInstance(content.get(14));
            case 15:
                return new PageFragment().newInstance(content.get(15));
            case 16:
                return new PageFragment().newInstance(content.get(16));
            case 17:
                return new PageFragment().newInstance(content.get(17));
            case 18:
                return new PageFragment().newInstance(content.get(18));
            case 19:
                return new PageFragment().newInstance(content.get(19));
            case 20:
                return new PageFragment().newInstance(content.get(20));
            default:
                return new PageFragment().newInstance(content.get(0));
        }
    }

    @Override
    public int getCount() {
        return content.size();
    }
}
