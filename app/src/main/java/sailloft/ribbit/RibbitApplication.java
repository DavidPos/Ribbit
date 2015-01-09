package sailloft.ribbit;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import sailloft.ribbit.ui.MainActivity;
import sailloft.ribbit.utilis.ParseConstants;

/**
 * Created by David's  on 11/18/2014.
 */
public class RibbitApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Jx2GIPElJ6KmRe4xMlbuULeJ3";
    private static final String TWITTER_SECRET = "d8Uq7xZiNpFSqVxOSJdf61WMJ7HNrGglJbSeJJMF9k4GT4XMIt";
    @Override
    public void onCreate() {
        super.onCreate();

        final TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);

        Fabric.with(this, new Twitter(authConfig));
        Parse.initialize(this, "ks64XVIoheO9yiqXG98F946Q4IsVSfXY5Icj6c9F", "ykRHcHJBRmPzD01WAh2JMkYfCCSq8GYrwWbKdpSl");

        PushService.setDefaultPushCallback(this, MainActivity.class);

        ParseInstallation.getCurrentInstallation().saveInBackground();


    }
    public static void updateParseInstallation(ParseUser user){
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put(ParseConstants.KEY_USER_ID, user.getObjectId());
        installation.saveInBackground();
    }
}
