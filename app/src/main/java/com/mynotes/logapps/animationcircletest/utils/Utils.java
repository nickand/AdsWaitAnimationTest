package com.mynotes.logapps.animationcircletest.utils;

import android.content.Context;

public class Utils {

    public static int pxFromDp(final Context context, final float dp) {
        return (int)(dp * context.getResources().getDisplayMetrics().density);
    }
}
