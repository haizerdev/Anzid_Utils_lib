package com.anzid.utils.logs;

import com.anzid.utils.Constants;
import com.anzid.utils.utils.BuildUtilKt;

public class AnzidLog {

    private static Logger[] loggers;

    public static void initialize(Logger... loggers) {
        AnzidLog.loggers = loggers;
    }

    public static void v(String message) {
        v(message, null);
    }

    public static void d(String message) {
        d(message, null);
    }

    public static void i(String message) {
        i(message, null);
    }

    public static void w(String message) {
        w(message, null);
    }

    public static void e(String message) {
        e(message, null);
    }

    public static void wtf(String message) {
        wtf(message, null);
    }

    public static void v(Throwable t) {
        v(null, t);
    }

    public static void d(Throwable t) {
        d(null, t);
    }

    public static void i(Throwable t) {
        i(null, t);
    }

    public static void w(Throwable t) {
        w(null, t);
    }

    public static void e(Throwable t) {
        e(null, t);
    }

    public static void wtf(Throwable t) {
        wtf(null, t);
    }

    public static void v(String message, Throwable t) {
        if (loggers != null) {
            for (Logger logger : loggers) {
                logger.v(message, t);
            }
        } else android.util.Log.v(Constants.LOG_TAG, message, t);
    }

    public static void d(String message, Throwable t) {
        if (loggers != null) {
            for (Logger logger : loggers) {
                logger.d(message, t);
            }
        } else android.util.Log.d(Constants.LOG_TAG, message, t);
    }

    public static void i(String message, Throwable t) {
        if (loggers != null) {
            for (Logger logger : loggers) {
                logger.i(message, t);
            }
        } else android.util.Log.i(Constants.LOG_TAG, message, t);
    }

    public static void w(String message, Throwable t) {
        if (loggers != null) {
            for (Logger logger : loggers) {
                logger.w(message, t);
            }
        } else android.util.Log.w(Constants.LOG_TAG, message, t);
    }

    public static void e(String message, Throwable t) {
        if (loggers != null) {
            for (Logger logger : loggers) {
                logger.e(message, t);
            }
        } else android.util.Log.e(Constants.LOG_TAG, message, t);
    }

    public static void wtf(String message, Throwable t) {
        if (loggers != null) {
            for (Logger logger : loggers) {
                logger.wtf(message, t);
            }
        } else android.util.Log.wtf(Constants.LOG_TAG, message, t);
    }

    public static void blockUntilAllWritesFinished() {
        if (loggers != null) {
            for (Logger logger : loggers) {
                logger.blockUntilAllWritesFinished();
            }
        }
    }
}
