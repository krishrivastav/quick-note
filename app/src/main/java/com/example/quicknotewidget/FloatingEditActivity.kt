package com.example.quicknotewidget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.EditText

class FloatingEditActivity : Activity() {

    private lateinit var editNote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_floating_edit)

        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        editNote = findViewById(R.id.edit_note)

        val prefs = getSharedPreferences("NotePrefs", Context.MODE_PRIVATE)
        val savedText = prefs.getString("note_content", "")
        editNote.setText(savedText)
        editNote.setSelection(editNote.text.length)
        editNote.requestFocus()
    }

    override fun onPause() {
        super.onPause()
        val text = editNote.text.toString()

        getSharedPreferences("NotePrefs", Context.MODE_PRIVATE)
            .edit()
            .putString("note_content", text)
            .apply()

        val intent = Intent(this, NoteWidgetProvider::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val ids = AppWidgetManager.getInstance(application)
                .getAppWidgetIds(ComponentName(application, NoteWidgetProvider::class.java))
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        }
        sendBroadcast(intent)
        overridePendingTransition(0, 0)
        finish()
    }
}
