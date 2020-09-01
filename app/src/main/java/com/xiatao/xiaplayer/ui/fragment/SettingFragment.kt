package com.xiatao.xiaplayer.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.xiatao.xiaplayer.R
import com.xiatao.xiaplayer.ui.activity.AboutActivity

class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        val key = preference?.key
        if("about".equals(key)){
            context?.startActivity(Intent(context,AboutActivity::class.java))
        }
        return super.onPreferenceTreeClick(preference)
    }
}