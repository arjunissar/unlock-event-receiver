package com.example.graphics.unlockbrtest;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

/**
 * Created by graphics on 9/20/2016.
 */
public class SettingsFragment extends PreferenceFragment  implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String UNLOCK_KEY = "pref_key_privacy_unlock";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Log.v("ON SHARED CHANGED:",key);

        switch (key) {
            case UNLOCK_KEY :
                boolean isUnlockEnabled = sharedPreferences.getBoolean(UNLOCK_KEY, true);
                if (isUnlockEnabled) {
                    ComponentName receiver = new ComponentName(getActivity(), UnlockReceiver.class);
                    PackageManager pm = getActivity().getPackageManager();
                    pm.setComponentEnabledSetting(receiver,
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                            PackageManager.DONT_KILL_APP);
                }else {
                    ComponentName receiver = new ComponentName(getActivity(), UnlockReceiver.class);
                    PackageManager pm = getActivity().getPackageManager();
                    pm.setComponentEnabledSetting(receiver,
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);
                }
                break;

            default: break;
        }
    }
}
