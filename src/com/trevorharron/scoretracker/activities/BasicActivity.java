package com.trevorharron.scoretracker.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.EditText;

import com.trevorharron.scoretracker.R;

/**
 * Created by trevorharron on 3/1/14.
 */
public abstract class BasicActivity extends Activity{

    protected void showError(final int errorMsgId){
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(errorMsgId)
                .setTitle(R.string.title_error)
                .show();
    }

    protected boolean blankOrDefault(final EditText edit, final int defaultId){
        return edit.getText().toString().isEmpty() ||
                edit.getText().toString().equals(getString(defaultId));
    }
}
