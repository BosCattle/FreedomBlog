package org.jiangtao.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jiangtao.freedomblog.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SortctivityFragment extends Fragment {

    public SortctivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sortctivity, container, false);
    }
}
