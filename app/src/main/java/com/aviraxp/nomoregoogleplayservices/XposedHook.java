package com.aviraxp.nomoregoogleplayservices;

import android.content.Context;

import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedHook implements IXposedHookLoadPackage {
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        try {
            Class<?> clazz1 = XposedHelpers.findClass("com.google.android.gms.common.GoogleApiAvailability", lpparam.classLoader);
            Method[] methods1 = XposedHelpers.findMethodsByExactParameters(clazz1, int.class, Context.class);
            XposedHelpers.findAndHookMethod(clazz1, methods1[0].getName(), Context.class, XC_MethodReplacement.returnConstant(0));
            XposedBridge.log("Hook GoogleApiAvailability Check Success" + lpparam.packageName + ": " + clazz1 + "." + methods1[0].getName());
        } catch (XposedHelpers.ClassNotFoundError ignored) {
        }

        try {
            Class<?> clazz2 = XposedHelpers.findClass("com.google.android.gms.common.GoogleServicesUtil", lpparam.classLoader);
            Method[] methods2 = XposedHelpers.findMethodsByExactParameters(clazz2, int.class, Context.class);
            XposedHelpers.findAndHookMethod(clazz2, methods2[0].getName(), Context.class, XC_MethodReplacement.returnConstant(0));
            XposedBridge.log("Hook Deprecated GoogleApiAvailability Check Success" + lpparam.packageName + ": " + clazz2 + "." + methods2[0].getName());
        } catch (XposedHelpers.ClassNotFoundError ignored) {
        }
    }
}
