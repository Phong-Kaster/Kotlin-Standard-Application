package com.example.kotlinstandardapplication.Widgetpage

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.kotlinstandardapplication.R


/**
 * @since 27-03-2023
 */
class WidgetConfigurationActivity : AppCompatActivity() {

    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    private lateinit var editText: EditText
    private lateinit var buttonSave: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_button_configuration)

        editText = findViewById(R.id.editText)
        buttonSave = findViewById(R.id.buttonSave)

        /*1. Get the App Widget ID from the intent that launched the activity*/
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }
        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        /*2. Set the activity result to RESULT_CANCELED*/
        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(Activity.RESULT_CANCELED, resultValue)

        setupEvent()
    }

    /**
     * @since 27-03-2023
     * listener when users click on button Save
     */
    @SuppressLint("RemoteViewLayout")
    private fun setupEvent()
    {
        buttonSave.setOnClickListener{
            val message = editText.text

            /*3. Configure the widget according to the user’s preferences*/
            val views = RemoteViews(this.packageName, R.layout.layout_widget_button_configuration)
            views.setCharSequence(R.id.buttonWidgetConfiguration, "setText", message)


            /*We have to set onClick immediately to widget can be active right after configuration*/
            val intent = Intent(this, WidgetActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            views.setOnClickPendingIntent(R.id.buttonWidgetConfiguration, pendingIntent)


            /*4. When the configuration is complete, get an instance of the AppWidgetManager by calling getInstance(Context)*/
            val appWidgetManager = AppWidgetManager.getInstance(this)


            /*5. Update the widget with a RemoteViews layout by calling updateAppWidget(int,RemoteViews)*/
            appWidgetManager.updateAppWidget(appWidgetId, views)


            /*6. Create the return intent, set it with the activity result, and finish the activity:*/
            val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            setResult(Activity.RESULT_OK, resultValue)
            finish()

        }/*end of Button Save*/
    }
}