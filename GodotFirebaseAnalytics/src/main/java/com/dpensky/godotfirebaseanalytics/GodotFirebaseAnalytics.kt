package com.dpensky.godotfirebaseanalytics

import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import org.godotengine.godot.Dictionary
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.UsedByGodot


class GodotFirebaseAnalytics(godot: Godot?) : GodotPlugin(godot) {
    private var firebaseAnalytics: FirebaseAnalytics? = null

    init {
        // Initialize Firebase (google-services.json will be handled by Godot's build)
        val activity = getActivity()
        if (activity != null) {
            FirebaseApp.initializeApp(activity)
            firebaseAnalytics = FirebaseAnalytics.getInstance(activity)
        }
    }

    @UsedByGodot
    override fun getPluginName(): String {
        return "FirebaseAnalytics"
    }

    @UsedByGodot
    @Deprecated("Deprecated in Java")
    override fun getPluginMethods(): MutableList<String?> {
        return mutableListOf<String?>("logEvent", "setUserId", "setUserProperty")
    }

    // ──────────────────────────────────────────────────────────────
    // Exposed methods (call these directly from GDScript)
    // ──────────────────────────────────────────────────────────────
    @UsedByGodot
    fun logEvent(name: String, params: Dictionary?) {
        val bundle = Bundle()

        if (params != null) {
            val keys = params._keys
            for (key in keys) {
                when (val value = params[key]) {
                    is Int, is Long -> {
                        bundle.putLong(key, (value as Number).toLong())
                    }

                    is Double, is Float -> {
                        bundle.putDouble(key, (value as Number).toDouble())
                    }

                    is String -> {
                        bundle.putString(key, value)
                    }
                }
                // Add more types if you need (boolean, etc.)
            }
        }

        firebaseAnalytics!!.logEvent(name, bundle)
    }

    @UsedByGodot
    fun setUserId(id: String?) {
        firebaseAnalytics!!.setUserId(id)
    }

    @UsedByGodot
    fun setUserProperty(name: String, value: String?) {
        firebaseAnalytics!!.setUserProperty(name, value)
    }
}
