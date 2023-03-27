package com.example.kotlinstandardapplication.Widgetpage


import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.kotlinstandardapplication.MainActivity
import com.example.kotlinstandardapplication.R

/**
 * @since 27-03-2023
 */
class WidgetButton : AppWidgetProvider() {

    /**
     * @since 27-03-2023
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        /*super.onUpdate(context, appWidgetManager, appWidgetIds)*/
        for( index in appWidgetIds)
        {
            val intent = Intent(context, WidgetActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            val views = RemoteViews( context.packageName, R.layout.layout_widget_button)
            views.setOnClickPendingIntent(R.id.buttonWidget, pendingIntent)

            appWidgetManager.updateAppWidget(index, views)
        }
    }
}