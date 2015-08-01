package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jokesdisplayer.JokeActivity;
import com.example.jokesdisplayer.JokeActivityFragment;

public class MainFragment extends Fragment {
    protected ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mProgressBar = (ProgressBar)root.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
        return root;
    }

    protected void launchJoke() {
        mProgressBar.setVisibility(View.VISIBLE);
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        // check if online
        if (isConnected) {
            new JokeRetriever(new JokeRetriever.OnTaskCompleted() {
                @Override
                public void onTaskCompleted(String result) {
                    // check if result empty and show error message, server may be down
                    if (result.isEmpty()) {
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), getString(R.string.error_failed_fetching_joke), Toast.LENGTH_SHORT).show();
                    } else {
                        Intent jokeIntent = new Intent(getActivity(), JokeActivity.class);
                        jokeIntent.putExtra(JokeActivityFragment.JOKE_INTENT_EXTRA, result);
                        startActivity(jokeIntent);
                        mProgressBar.setVisibility(View.GONE);
                    }
                }
            }).execute();
        } else {
            // show message that there is not internet connection
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getString(R.string.error_no_network_connection), Toast.LENGTH_SHORT).show();
        }
    }

}
