package me.xmao.MaoPoints.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools {
	public static int nowTimestamp(){  
	    return  (int) (System.currentTimeMillis() / 1000);
	} 
    public static void Feedback(final Exception e, final String message) {
        Feedback(e, false, message);
    }
    
    public static void Feedback(final Exception e, final boolean isShow, String message) {
        final StackTraceElement[] stacktrace = e.getStackTrace();
        String infoMessage = "";
        if (message == "") {
            message = "[\u5f02\u5e38\u4fe1\u606f] " + e.getMessage();
        }
        else {
            message = "[\u5f02\u5e38\u4fe1\u606f] " + message;
        }
        if (isShow) {
            StackTraceElement[] array;
            for (int length = (array = stacktrace).length, i = 0; i < length; ++i) {
                final StackTraceElement trace = array[i];
                message = String.valueOf(message) + "\n\t\u6765\u81ea " + trace.getClassName() + "(" + trace.getFileName() + ":" + trace.getLineNumber() + ")";
            }
            infoMessage = "\n\u8be6\u7ec6\u4fe1\u606f:\n\t" + e.getLocalizedMessage();
        }
        System.err.println(String.valueOf(message) + infoMessage);
    }
    
}
