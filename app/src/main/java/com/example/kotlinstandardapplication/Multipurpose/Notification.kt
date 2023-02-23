package com.example.kotlinstandardapplication.Multipurpose

import android.app.Activity
import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.kotlinstandardapplication.R


/**
 * @author Phong-Kaster
 * @since 23-02-2023
 */
class Notification {

    companion object{
        private const val CHANNEL_ID = "kotlin-standard-application"
        /**
         * @since 23-02-2023
         * Create the NotificationChannel, but only on API 26+ because
         * the NotificationChannel class is new and not in the support library
         */
        fun createNotificationChannel(context: Context)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                val name = context.getString(R.string.app_name)
                val descriptionText = context.getString(R.string.app_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }


                // Register the channel with the system
                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }/*end createNotificationChannel*/

        /**
         * @since 23-02-2023
         * @param context is the current context of activity or fragment
         * @param activity is the activity that holding the context
         * @param textContent is the context of notification
         * @param Notification Id is the ID of notification, by default, it equals notificationId = 1896
         */
        fun setupNotification(context: Context,
                              activity: Activity,
                              textContent: String)
        {
            val notificationId = (0 until  2147483647).random()
            val textTitle = context.getString(R.string.app_name)
            val bigText = NotificationCompat
                .BigTextStyle()
                .bigText("$textContent with ID: $notificationId")


            // Create an explicit intent for an Activity in your app
            val intent = Intent(context, activity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_swastika)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(bigText)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            val notificationManager = NotificationManagerCompat.from(context)
            val notificationContent = builder.build()

            notificationManager.notify(notificationId, notificationContent)
        }/*end setupNotification*/


        /**
         * @since 23-02-2023
         * create notification with buttons
         */
        fun setupNotificationWithButton(context: Context,
                                        activity: Activity,
                                        textContent: String)
        {
            val notificationId = (0 until  2147483647).random()

            // create an intent for snooze application
            val snoozeIntent = Intent(context, activity::class.java).apply {
                action = Intent.ACTION_SCREEN_OFF
                putExtra(EXTRA_NOTIFICATION_ID, 0) }
            val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent,  PendingIntent.FLAG_IMMUTABLE)

            // Create an explicit intent for an Activity in your app
            val intent = Intent(context, activity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val openPendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            // build the notification
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_swastika)
                .setContentTitle("My notification")
                .setContentText(textContent)
                .setContentIntent(openPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_snooze, context.getString(R.string.snooze),
                    snoozePendingIntent)

            // show the notification
            val notificationManager = NotificationManagerCompat.from(context)
            val notificationContent = builder.build()
            notificationManager.notify(notificationId, notificationContent)
        }/*setupNotificationWithButton*/

        /**
         * @since 23-02-2023
         * setup notification with image
         */
        fun setupNotificationWithImage(context: Context,
                                       activity: Activity,
                                       textContent: String)
        {
            /*Step 1: set notification Id and text title*/
            val notificationId = (0 until  2147483647).random()
            val textTitle = context.getString(R.string.app_name)

            /*Step 2: declare bitmap and transfer bitmap to image*/
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.img_fuhrer)
            val image = NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap)
                .bigLargeIcon(null)

            /*Step 3: Create an explicit intent for an Activity in your app*/
            val intent = Intent(context, activity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val openPendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


            /*Step 4: build the notification*/
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_swastika)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setContentIntent(openPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(image)
                .setLargeIcon(bitmap)

            /*Step 5: show the notification*/
            val notificationManager = NotificationManagerCompat.from(context)
            val notificationContent = builder.build()
            notificationManager.notify(notificationId, notificationContent)
        }/*setup Notification With Image*/
    }
}