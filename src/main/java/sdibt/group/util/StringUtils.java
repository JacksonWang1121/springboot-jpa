package sdibt.group.util;

/**
 * @author wangjisen
 * @date 2019/3/28 12:51
 */
public class StringUtils {

    /**
     * 验证字符串是否为空
     * @param param 输入的字符串
     * @return
     */
    public static boolean isNotBlank(String param) {
        if (!("".equals(param) || param==null)) {
            return true;
        }
        return false;
    }

}
