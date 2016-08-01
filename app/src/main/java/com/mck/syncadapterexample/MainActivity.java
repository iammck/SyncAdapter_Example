package com.mck.syncadapterexample;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * MainActivity for the syncAdapter example. The SyncAdapter framework
 * requires an account with the system. The declared account type value
 * must match what is in res/xml/authenticator as well as res/xml/syncadapter.
 * To set up the account, in onCreate() the account is created inevitably
 * by using addAccountExplicitly()
 *
 * There are several ways of performing sync: on server updated, provider update,
 * update periodically, or on demand. While it is suggested to not do so on
 * demand, this example does so for simplicity.
 *
 *
 */
public class MainActivity extends AppCompatActivity {
    // Constants
    public static final String AUTHORITY = "com.mck.syncadapterexample.provider";
    public static final String ACCOUNT_TYPE = "com.mck.syncadapterexample";
    public static final String ACCOUNT = "dummy_acct";
    private static final String TAG = "MainActivity";

    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create the system account for the SyncAdapter
        account = createSyncAccount();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // request that SyncAdapter perform a Sync right now.
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(account, AUTHORITY, bundle);
    }

    private Account createSyncAccount() {
        // create the account instance with account name and type
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        // get an instance of account Manager
        AccountManager accountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        // add the account with no pwd or user data. note that this will
        // need permission AUTHENTICATE_ACCOUNTS
        if (accountManager.addAccountExplicitly(newAccount, null, null)){
            Log.v(TAG, "Successful account creation.");
        } else {
            Log.v(TAG, "Unsuccessful account creation.");
        }
        return account;
    }
}