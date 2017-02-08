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
            Class<?> clazz = XposedHelpers.findClass("com.google.android.gms.common.GoogleApiAvailability", lpparam.classLoader);
            Method[] methods = XposedHelpers.findMethodsByExactParameters(clazz, int.class, Context.class);
            XposedHelpers.findAndHookMethod(clazz, methods[0].getName(), Context.class, XC_MethodReplacement.returnConstant(0));
            XposedBridge.log("Hook GoogleApiAvailability Check Success" + lpparam.packageName + ": " + clazz + "." + methods[0].getName());
        } catch (XposedHelpers.ClassNotFoundError ignored) {
        }
    }
}
