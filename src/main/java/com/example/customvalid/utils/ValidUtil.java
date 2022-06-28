package com.example.customvalid.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidUtil {

    public static final String ID_REGEX = "[A-Z][12][0-9]{8}";
    public static final String RC_NEW_REGEX = "[A-Z][89][0-9]{8}";
    public static final String RC_OLD_REGEX = "[A-Z][A-D][0-9]{8}";
    public static final String ALL_REGEX = "[A-Z][1289A-D][0-9]{8}";

    // en的index 0~25，對應證號英文10~35(index + 10 = 要轉換的數字)
    private static final String en = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
    // 證號轉換後11碼數字，數字*權重 加總後應是10的倍數
    private static final int[] weights = {1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1};

    public static void main(String[] args) {
        System.out.println(validId("C177680715", ID_REGEX));
        System.out.println(validId("A800000014", RC_NEW_REGEX));
        System.out.println(validId("AC37106605", RC_OLD_REGEX));

        System.out.println(validId("C177680715", ALL_REGEX));
        System.out.println(validId("A800000014", ALL_REGEX));
        System.out.println(validId("AC37106605", ALL_REGEX));
    }

    public static boolean validId(String id, String... regex) {
        id = id.toUpperCase();
        for (String s : regex) {
            if (id.matches(s)) {
                return validConvert(convertEn(id));
            }
        }

        return false;
    }

    /**
     * Step1:把證號中的英文，依照轉換規則轉換為數字
     */
    private static String convertEn(String id) {
        String firstEn = "" + id.charAt(0);
        String result = id.replaceFirst(firstEn, String.valueOf(en.indexOf(firstEn) + 10));
        if (id.matches(RC_OLD_REGEX)) {
            String secondEn = "" + id.charAt(1);
            result = result.replaceFirst(secondEn, String.valueOf(en.indexOf(secondEn) % 10));
        }

        return result;
    }

    /**
     * Step2:傳入證號轉換後的11位數字，檢查是否符合
     */
    private static boolean validConvert(String str) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * Character.getNumericValue(str.charAt(i));
        }

        return (sum % 10) == 0;
    }

}
