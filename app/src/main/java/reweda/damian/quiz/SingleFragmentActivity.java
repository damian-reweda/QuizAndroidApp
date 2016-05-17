package reweda.damian.quiz;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Class provide flexible solution for creating new Fragments
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    BroadcastReceiver broadcastReceiver;
    ProgressDialog lostInternetConnectionDialog;

    //A protected method that returns the ID of the layout that the activity will inflate.
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        if (getResources().getBoolean(R.bool.portrait_only)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        lostInternetConnectionDialog = new ProgressDialog(this);

        internetConnectionListener();
        initializeInnerFragment();

    }

    private void initializeInnerFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    private void internetConnectionListener() {

        if (broadcastReceiver == null) {

            broadcastReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {

                    String status = NetworkUtil.getConnectivityStatusString(context);

                    String noInternet = "noInternet";
                    String mobileAcess = "mobileAcess";
                    String wifiAcess = "wifiAcess";

                    if (status == noInternet) {
                        lostInternetConnectionDialog.setTitle(R.string.lost_connection);
                        lostInternetConnectionDialog.setMessage(getString(R.string.turn_wifi_or_mobile));
                        lostInternetConnectionDialog.setCancelable(false);
                        lostInternetConnectionDialog.show();
                    } else if (status == mobileAcess) {
                        if (lostInternetConnectionDialog.isShowing()) {
                            lostInternetConnectionDialog.dismiss();
                        }
                    } else if (status == wifiAcess) {
                        if (lostInternetConnectionDialog.isShowing()) {
                            lostInternetConnectionDialog.dismiss();
                        }
                    }
                }

            };

            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(broadcastReceiver, intentFilter);
        }


    }
}
