package me.zyq.phonebook.springboot.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拼音工具类
 * @author djin
 */
public class PinYinUtil {
    private static final Logger logger = LoggerFactory.getLogger(PinYinUtil.class);
    private static final HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    static {
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    /**
     * 将汉字转换为全拼
     * @param src 输入字符串
     * @return 拼音字符串（非中文原样保留），null输入返回空字符串
     */
    public static String getPinYin(String src) {
        if (StringUtils.isBlank(src)) {
            return "";
        }

        StringBuilder pys = new StringBuilder();
        char[] hz = src.toCharArray();

        try {
            for (char c : hz) {
                if (isChineseCharacter(c)) {
                    String[] pyArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pyArray != null && pyArray.length > 0) {
                        pys.append(pyArray[0]);
                    }
                } else {
                    pys.append(c);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            logger.error("拼音转换异常: {}", src, e);
        }
        return pys.toString();
    }

    /**
     * 获取名字首字母（大写）
     * @param name 姓名
     * @return 首字母（非字母返回"#"），null/空输入返回"#"
     */
    public static String getFirstByName(String name) {
        if (StringUtils.isBlank(name)) {
            return "#";
        }

        String pinyin = getPinYin(name);
        if (StringUtils.isBlank(pinyin)) {
            return "#";
        }

        char firstChar = pinyin.charAt(0);
        return Character.isLetter(firstChar)
                ? String.valueOf(firstChar).toUpperCase()
                : "#";
    }

    /**
     * 判断是否为中文字符
     */
    private static boolean isChineseCharacter(char c) {
        return Character.toString(c).matches("[\\u4E00-\\u9FA5]");
    }

    public static void main(String[] args) {
        testCase(null);       // 空值测试
        testCase("");         // 空字符串测试
        testCase("  ");       // 空白字符测试
        testCase("曾小贤");    // 纯中文测试
        testCase("Chain123"); // 英文数字测试
        testCase("马云");      // 多音字测试
        testCase("A1张三");    // 混合测试
    }

    private static void testCase(String str) {
        System.out.printf("输入: %-10s | 全拼: %-15s | 首字母: %s%n",
                str == null ? "null" : "'" + str + "'",
                getPinYin(str),
                getFirstByName(str));
    }
}