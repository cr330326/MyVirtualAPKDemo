package com.example.allen.myvirtualapk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;
import com.didi.virtualapk.internal.LoadedPlugin;
import com.didi.virtualapk.internal.PluginContentResolver;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.textView);
        String cpuArch;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            cpuArch = Build.SUPPORTED_ABIS[0];
        } else {
            cpuArch = Build.CPU_ABI;
        }
        textView.setText(cpuArch);
        Log.d("ryg", "onCreate cpu arch is "+ cpuArch);
        this.loadPlugin(this);
        Log.d("ryg", "onCreate classloader is "+ getClassLoader());
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.button) {
            final String pkg = "com.example.allen.mytest";
            if (PluginManager.getInstance(this).getLoadedPlugin(pkg) == null) {
                Toast.makeText(this, "plugin [com.example.allen.mytest] not loaded", Toast.LENGTH_SHORT).show();
                return;
            }

            // test Activity
            Intent intent = new Intent();
            intent.setClassName(pkg, "com.example.allen.mytest.MainActivity");
            startActivity(intent);
        } else if (v.getId() == R.id.about) {
            showAbout();
        }
    }

    private void loadPlugin(Context base) {
        PluginManager pluginManager = PluginManager.getInstance(base);
        File apk = new File(Environment.getExternalStorageDirectory(), "test.apk");
        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("详细信息");
        builder.setTitle("关于");
        builder.setNegativeButton("好的", null);
        builder.show();
    }
}
