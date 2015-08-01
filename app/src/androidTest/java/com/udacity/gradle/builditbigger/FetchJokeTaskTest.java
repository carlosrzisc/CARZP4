package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Fetch joke from server unit test
 */
public class FetchJokeTaskTest extends AndroidTestCase {

    public void testFetchJoke() {
        // Found solution to test an async task from:
        // http://stackoverflow.com/questions/9774792/asynctask-onpostexecute-not-called-in-unit-test-case
        // the key is to use the CountDownLatch
        final CountDownLatch signal = new CountDownLatch(1);
        new JokeRetriever(new JokeRetriever.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String result) {
                assertTrue(!result.isEmpty());
                signal.countDown();// notify the count downlatch
            }
        }).execute();
        try {
            signal.await(11, TimeUnit.SECONDS);// wait for callback
        } catch (InterruptedException e1) {
            fail();
            e1.printStackTrace();
        }
    }
}
