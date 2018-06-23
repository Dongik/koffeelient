package site.dongik.goffee.network

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import site.dongik.goffee.R
import android.location.LocationManager
import android.support.v4.content.ContextCompat
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.Response
import site.dongik.goffee.app.Config
import site.dongik.goffee.view.GoffeeActivity


internal class PushService : FirebaseMessagingService() {
//
//    private lateinit var fusedLocationClient: FusedLocationProviderClient

    val TAG = "PushService"
    lateinit private var disposable: Disposable

    private val ApiServe by lazy {
        DongikApiService.create()
    }


    //this way is not private
    private fun postLocation(){

//        launch(CommonPool)


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationProvider = LocationManager.NETWORK_PROVIDER
            val lastKnownLocation = locationManager.getLastKnownLocation(locationProvider)
            launch{
//                val result
                async(CommonPool){
//                    result = ApiServe.postLocation(Config.userId,lastKnownLocation)
                }.await()


            }
            disposable = ApiServe.postLocation(Config.userId,lastKnownLocation)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> Toast.makeText(this,"good location", Toast.LENGTH_SHORT).show() },
                            { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                    )
        }else{

        }
    }
    private fun getDistanceFromHere(la:String,lo:String){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationProvider = LocationManager.NETWORK_PROVIDER
            val lastKnownLocation = locationManager.getLastKnownLocation(locationProvider)
        }else{

        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.from)

        // Check if message contains a data payload.
        if (remoteMessage.data.isEmpty())return

        val hostLocation = Location("")
        hostLocation.latitude = remoteMessage.data.get("latitude") as Double
        hostLocation.longitude = remoteMessage.data.get("longitude") as Double
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationProvider = LocationManager.NETWORK_PROVIDER
            val lastKnownLocation = locationManager.getLastKnownLocation(locationProvider)
            if(lastKnownLocation.distanceTo(hostLocation)< Config.maxDistance){
                sendNotification("goood")
            }
        }else{

        }

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            sendNotification(remoteMessage.data.toString())
        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body!!)
            sendNotification(remoteMessage.notification!!.body!!)
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]


    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private fun handleNow() {
        Log.d(TAG, "Short lived task is done.")
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, GoffeeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    companion object {

        private val TAG = "FcmService"
    }
}