package com.example.jokesdisplayer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public static final String JOKE_INTENT_EXTRA = "JOKE_EXTRA";

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);
        String joke = getActivity().getIntent().getStringExtra(JOKE_INTENT_EXTRA);
        ((TextView) rootView.findViewById(R.id.txtview_joke)).setText(joke);
        return rootView;
    }
}
