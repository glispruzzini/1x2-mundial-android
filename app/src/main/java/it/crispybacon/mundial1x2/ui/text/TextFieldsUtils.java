package it.crispybacon.mundial1x2.ui.text;

import android.support.v7.widget.AppCompatEditText;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Jameido on 16/06/2018.
 */
public class TextFieldsUtils {

    public static void disableCopyPaste(final AppCompatEditText editText) {
        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem item) {
                return false;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });

        editText.setLongClickable(false);
        editText.setTextIsSelectable(false);
    }
}
