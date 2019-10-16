package in.org.celesta.iitp.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Keyboard {
    public static void closeKeyboard(View view, Context context) {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null)
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_FORCED);
    }
}
