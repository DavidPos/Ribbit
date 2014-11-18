package sailloft.ribbit;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by David's  on 11/18/2014.
 */
public class RibbitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "ks64XVIoheO9yiqXG98F946Q4IsVSfXY5Icj6c9F", "ykRHcHJBRmPzD01WAh2JMkYfCCSq8GYrwWbKdpSl");


    }

}
