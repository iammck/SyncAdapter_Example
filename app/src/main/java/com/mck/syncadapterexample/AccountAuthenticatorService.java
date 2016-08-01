package com.mck.syncadapterexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * An AccountAuthenticator instance needs be accessible via a service.
 * Clients are able to interact with the authenticator through a binder.
 * Created by Michael on 7/31/2016.
 */
public class AccountAuthenticatorService extends Service {

    // keep a hold on the authenticator
    private AccountAuthenticator authenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        authenticator = new AccountAuthenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return authenticator.getIBinder();
    }
}
