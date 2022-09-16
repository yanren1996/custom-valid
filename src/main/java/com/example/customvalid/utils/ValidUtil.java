package com.example.customvalid.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidUtil {

    public enum CardType {
        IC("[A-Z][12][0-9]{8}"),
        RC_NEW("[A-Z][89][0-9]{8}"),
        RC_OLD("[A-Z][A-D][0-9]{8}");

        private String regex;

        CardType(String regex) {
            this.regex = regex;
        }

        public String value() {
            return this.regex;
        }
    }

    // en的index 0~25，對應證號英文10~35(index + 10 = 要轉換的數字)
    private static final String en = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
    // 證號轉換後11碼數字的個別權重，數字*權重 加總後應是10的倍數
    private static final int[] weights = {1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1};

    public static void main(String[] args) {
        System.out.println(validId("C177680715", CardType.IC));
        System.out.println(validId("A800000014", CardType.RC_NEW));
        System.out.println(validId("AC37106605", CardType.RC_OLD));
    }

    public static boolean validId(String id, ValidUtil.CardType... cardTypes) {
        id = id.toUpperCase();
        for (ValidUtil.CardType type : cardTypes) {
            if (id.matches(type.value())) {
                return validNumber(convertEn(id));
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
        if (id.matches(CardType.RC_OLD.value())) {
            String secondEn = "" + id.charAt(1);
            result = result.replaceFirst(secondEn, String.valueOf(en.indexOf(secondEn) % 10));
        }

        return result;
    }

    /**
     * Step2:傳入證號轉換後的11位數字，檢查是否符合
     */
    private static boolean validNumber(String str) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * Character.getNumericValue(str.charAt(i));
        }

        return (sum % 10) == 0;
    }

}
