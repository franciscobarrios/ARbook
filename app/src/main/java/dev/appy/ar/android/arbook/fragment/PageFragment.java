package dev.appy.ar.android.arbook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.appy.ar.android.arbook.R;

public class PageFragment extends Fragment {

    private TextView tvContent;
    private String mContent;

    public PageFragment() {
    }

    public PageFragment newInstance(String content) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TEXT", content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContent = getArguments().getString("TEXT");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvContent = view.findViewById(R.id.tv_content);
        if (mContent != null) {
            tvContent.setText(mContent);
        }
    }
}
