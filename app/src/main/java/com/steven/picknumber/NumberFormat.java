package com.steven.picknumber;

/**
 * 可供选号资源：
 * 川A△□□□□、
 * 川A△△□□□、
 * 川A□△□□□、
 * 川A□□△□□、
 * 川A□□□△□、
 * 川A△□□□△、
 * 川A□□□△△、
 * 川A△□△□□、
 * 川A□△△□□、
 * 川A△□□△□、
 * 川A□△□△□、
 * 川A□△□□△
 * （△为除"I、O"外的24个字母、□为阿拉伯数字"0-9"）
 * <p>
 * Created by Steven.Xc.Tian on 16/11/1.
 */

public class NumberFormat {
    private NumberFormat() {
        // init
        minDigitSize = minAlphabeticSize = AVAILABLE_RESOURCES_FORMAT[0].length();
        maxDigitSize = maxAlphabeticSize = 0;


        for (String string : AVAILABLE_RESOURCES_FORMAT) {
            int flagA = 0, flagD = 0;

            for (char c : string.toCharArray()) {
                if (c == 36) {
                    // '$'
                    flagD++;
                } else if (c == 35) {
                    // '#'
                    flagA++;
                }
            }

            // 更新最小数字个数
            minDigitSize = Math.min(minDigitSize, flagD);
            // 更新最小字母个数
            minAlphabeticSize = Math.min(minAlphabeticSize, flagA);
            // 更新最大数字个数
            maxDigitSize = Math.max(maxDigitSize, flagD);
            // 更新最大字母个数
            maxAlphabeticSize = Math.max(maxAlphabeticSize, flagA);
        }


    }

    public static NumberFormat getInstance() {
        if (mNumberFormat == null) {
            mNumberFormat = new NumberFormat();
        }

        return mNumberFormat;
    }

    private static NumberFormat mNumberFormat = null;
    /**
     * #:除'I','O'以外的24个字母
     * $:0-9的数字
     */
    static final String[] AVAILABLE_RESOURCES_FORMAT = {
            "#$$$$",
            "##$$$",
            "$#$$$",
            "$$#$$",
            "$$$#$",
            "#$$$#",
            "$$$##",
            "#$#$$",
            "$##$$",
            "#$$#$",
            "$#$#$",
            "$#$$#",
    };

    private int minDigitSize;
    private int maxDigitSize;
    private int minAlphabeticSize;
    private int maxAlphabeticSize;

    /**
     * @return 号牌中最小的数字个数
     */
    public int getMinDigitSize() {
        return minDigitSize;
    }

    /**
     * @return 号牌中最大的数字个数
     */
    public int getMaxDigitSize() {
        return maxDigitSize;
    }

    /**
     * @return 号牌中最小的字母个数
     */
    public int getMinAlphabeticSize() {
        return minAlphabeticSize;
    }

    /**
     * @return 号牌中最大的字母个数
     */
    public int getMaxAlphabeticSize() {
        return maxAlphabeticSize;
    }
}
