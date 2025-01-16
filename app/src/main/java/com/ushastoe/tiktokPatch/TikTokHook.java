package com.ushastoe.tiktokPatch;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.telephony.TelephonyManager;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class TikTokHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) {

        final String COUNTRY_ISO = "jp";
        final String OPERATOR = "44010";
        final String OPERATOR_NAME = "NTT DoCoMo";

        hookMethod("getSimCountryIso", COUNTRY_ISO);
        hookMethod("getNetworkCountryIso", COUNTRY_ISO);
        hookMethod("getSimOperator", OPERATOR);
        hookMethod("getNetworkOperator", OPERATOR);
        hookMethod("getSimOperatorName", OPERATOR_NAME);
        hookMethod("getNetworkOperatorName", OPERATOR_NAME);
    }

    private void hookMethod(String methodName, final String result) {
        findAndHookMethod(TelephonyManager.class, methodName, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
                param.setResult(result);
            }
        });
    }
}
