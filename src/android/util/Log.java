package android.util;

public class Log {

    public static void i(String tag, String msg) {
        System.out.println("i: " + tag + " - " + msg);
    }

    public static void e(String tag, String msg) {
        System.out.println("e: " + tag + " - " + msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        System.out.println("e: " + tag + " - " + msg + ", e: " + e.toString());
    }

    public static void w(String tag, String msg) {
        System.out.println("w: " + tag + " - " + msg);
    }

    public static void w(String tag, String msg, Throwable e) {
        System.out.println("w: " + tag + " - " + msg + ", e: " + e.toString());
    }

    public static void v(String tag, String msg) {
        System.out.println("v: " + tag + " - " + msg);
    }

    public static void d(String tag, String msg) {
        System.out.println("d: " + tag + " - " + msg);
    }
}
