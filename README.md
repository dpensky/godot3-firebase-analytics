# Godot Firebase Analytics (Android)

A Godot plugin for Android that provides integration with Firebase Analytics.

## Features

- Log custom events with parameters.
- Set User ID.
- Set User Properties.

## Versions

- **Firebase BOM:** `34.11.0`
- **Godot:** `3.6.x` (Android plugin API v1)

## Installation

### 1. Build the Plugin
Run the following command in the project root to generate the `.aar` file:
```bash
./gradlew assembleRelease
```
The output file will be located at `GodotFirebaseAnalytics/build/outputs/aar/GodotFirebaseAnalytics-release.aar`.

### 2. Add to Godot Project
1. Enable **Android Build Template** in your Godot project.
2. Create the folder `res://android/plugins/` if it doesn't exist.
3. Copy `GodotFirebaseAnalytics-release.aar` and `FirebaseAnalytics.gdap` into the `res://android/plugins/` folder.
4. Place your `google-services.json` file in `res://android/build/`.

### 3. Export Settings
In Godot, go to **Project > Export...**, select your Android export preset, and under the **Plugins** section, check **FirebaseAnalytics**.

## Usage (GDScript)

```gdscript
var firebase_analytics = null

func _ready():
    if Engine.has_singleton("FirebaseAnalytics"):
        firebase_analytics = Engine.get_singleton("FirebaseAnalytics")
        print("Firebase Analytics plugin loaded")
    else:
        print("Firebase Analytics plugin not found")

func _on_button_pressed():
    if firebase_analytics:
        # Log a simple event
        firebase_analytics.logEvent("button_click", {"button_id": "start_game"})
        
        # Set User ID
        firebase_analytics.setUserId("user_12345")
        
        # Set User Property
        firebase_analytics.setUserProperty("premium_user", "true")
```

## API Reference

### `logEvent(name: String, params: Dictionary)`
Logs an app event. The dictionary supports `String`, `Int`, `Long`, `Double`, `Float`, and `Boolean` values.

### `setUserId(id: String)`
Sets the user ID for the current session.

### `setUserProperty(name: String, value: String)`
Sets a user property to a given value.
