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

    /**
     * 号牌总共有5位
     */
    static final int TOTAL_NUMBER_SIZE = 5;

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

    public void parse(int format, char[] digit, char[] alph) {
        if (format >= AVAILABLE_RESOURCES_FORMAT.length)
            return;

        if (format != -1) {

        } else {

        }
    }

    private String match(int pos, char[] digit, char[] alpha) {
        int alphaNum = 0, digitNum = 0;

        final char[] target = AVAILABLE_RESOURCES_FORMAT[pos].toCharArray();
        char[] results = new char[5];
        for (int i = 0; i < TOTAL_NUMBER_SIZE; i++) {
            char c = target[i];
            if (c == '#') {
                // 字母与当前输入不符合，格式不正确不用继续匹配
                if (alphaNum >= alpha.length) {
                    return null;
                }

                results[i] = alpha[alphaNum++];
            } else {
                // 数字与当前输入不符合，格式不正确不用继续匹配
                if (digitNum >= digit.length)
                    return null;

                results[i] = digit[digitNum++];
            }
        }

        return new String(results);
    }
}
