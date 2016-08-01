# SyncAdapter Example

This is an example following the [google developer training post](https://developer.android.com/training/sync-adapters/index.html) which covers how to implement a SyncAdapter as well as the other components needed to plug a sync adapter into the framework.

While it is possible to implement network sync code, using this framework allows developers to take advantage of several features:
-  Plug-in architecture
-  Automated execution
-  Automated network checking
-  Improved battery performance
-  Account management and authentication

It should be noted that sync adapters run asynchronously. Data is transferred regularly and efficiently, but not instantaneously. If one needs to do real-time data transfer, another approach such as AsyncTask or IntentService should be used.

SyncAdapters can be triggered into performing sync through a few approaches.
-   When server data changes
-   When content provider data changes
-   Periodically
-   On demand

Server data changes recognized/captured using GCM then running a call to `ContentResolver.requestSync()`, While content provider changes can be caught with content observers that trigger sync with the same call.

Periodical sync can take place by making an `addPeriodicSync()` call to the contentResolver or using an Alarm then triggering the sync with requestSync() call.

On demand can be made any time by simply calling `ContentResolver.requestSync()` and passing the expected information as bundle extras. Documentation suggests not performing on demand sync.

This example makes sync requests on demand during the activities onResume() method.
