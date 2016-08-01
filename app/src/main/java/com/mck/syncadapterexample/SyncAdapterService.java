package com.mck.syncadapterexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * SyncAdapter needs to be accessible via bound service to the
 * Framework.
 *
 * Created by Michael on 7/31/2016.
 */
public class SyncAdapterService extends Service {

    private static final Object SyncAdapterLock = new Object();
    private SyncAdapter syncAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (SyncAdapterLock){
            if (syncAdapter == null){
                syncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        synchronized (SyncAdapterLock){        }
        return syncAdapter.getSyncAdapterBinder();
    }
}
