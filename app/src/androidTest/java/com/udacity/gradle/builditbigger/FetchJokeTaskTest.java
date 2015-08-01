package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

/**
 * Created by carlos on 7/30/15.
 */
public class FetchJokeTaskTest extends AndroidTestCase {

    public void testFetchJoke() {
        new JokeRetriever(new JokeRetriever.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String result) {
                assertTrue(!result.isEmpty());
            }
        }).execute();

    }
}
