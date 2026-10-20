# 📝 QuickNote Widget

A lightweight, zero-bloat Android home screen note widget designed for instant inline-style editing. 

Because Android's `RemoteViews` architecture prevents software keyboards from attaching directly to home screen widgets, **QuickNote Widget** uses a seamless, borderless floating overlay (`Activity`) that pops the keyboard straight up over your wallpaper the instant you tap the widget—without ever opening a full-screen app or cluttering your Recent Apps switcher.

---

## ✨ Features

* **Instant Wallpaper Editing:** Tap the widget and start typing immediately—no splash screens, no app transitions, and no navigation menus.
* **Auto-Save on Dismiss:** Simply tap anywhere outside the note box or press the back gesture to automatically save your note and update the widget.
* **Material 3 Dark Surface:** Styled with Google's neutral dark charcoal palette (`#1E1F20` surface and `#E3E3E3` text) and `24dp` rounded corners to blend natively into modern Android home screens.
* **Zero Background Drain:** Uses pure Android SDK classes (`Activity`, `AppWidgetProvider`, `SharedPreferences`) with zero external dependencies, background services, or internet permissions.
* **100% Cloud-Compiled:** Built and released automatically via GitHub Actions—no local Android Studio setup required.

---

## 📲 Installation & Usage

1. Go to the [**Releases**](../../releases) section of this repository.
2. Download the latest `QuickNoteWidget.apk` from the **Assets** dropdown and install it on your Android device.
   *(Note: If Google Play Protect shows an "Unrecognized app developer" prompt for the debug build, tap **More details** -> **Install anyway**).*
3. Go to your phone's home screen, **long-press on an empty space**, and select **Widgets**.
4. Find **QuickNote Widget** in the list and drag it onto your wallpaper.
5. **Tap the widget** to pop up the keyboard and type your note. Tap anywhere outside the box to save and close.

---

## 🛠️ How It Works

```text
[Home Screen Widget (TextView)]
       │
       ├── (User Taps Widget) ──► Launches FloatingEditActivity (Dialog Theme, No Animation)
       │                                │
       │                                ├── Auto-focuses EditText & pops up Soft Keyboard
       │                                └── User taps outside / presses Back (onPause)
       │                                           │
       ◄── Broadcasts APPWIDGET_UPDATE ◄───────────┴── Saves text to Shared
Preferences
