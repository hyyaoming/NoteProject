package note.lym.org.noteproject.model.http.parameters;

import java.util.HashMap;

import note.lym.org.noteproject.app.constant.Constants;

/**
 * doc  拼接api参数包装成map
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public class ApiParameters {
    private static final String TYPE = "type";
    private static final String PAGE = "page";
    private static final String SHOW_APP_ID = "showapi_appid";
    private static final String SHOW_API_SIGN = "showapi_sign";
    private static final String T_ID = "tid";
    private static final String MAX_RESULT = "max_result";
    private static final String ROW_S = "rows";


    /**
     * 后去妹子图片参数map
     *
     * @param type 类型
     * @param page 页码
     * @return 返回map数据
     */
    public static HashMap<String, String> getLookGirlParametersMap(String type, String page) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TYPE, type);
        map.put(PAGE, page);
        map.put(SHOW_APP_ID, Constants.SHOW_API_ID);
        map.put(SHOW_API_SIGN, Constants.SHOW_API_KEY);
        return map;
    }

    /**
     * 获取签名和appIid参数map
     *
     * @return 返回map信息
     */
    public static HashMap<String, String> getAppSignMap() {
        HashMap<String, String> signMap = new HashMap<>();
        signMap.put(SHOW_APP_ID, Constants.SHOW_API_ID);
        signMap.put(SHOW_API_SIGN, Constants.SHOW_API_KEY);
        return signMap;
    }


    /**
     * 获取健康咨询列表参数map
     *
     * @return 返回map信息
     */
    public static HashMap<String, String> getHealthListMap(int page, String healthId) {
        HashMap<String, String> map = new HashMap<>();
        map.put(SHOW_APP_ID, Constants.SHOW_API_ID);
        map.put(SHOW_API_SIGN, Constants.SHOW_API_KEY);
        map.put(PAGE, String.valueOf(page));
        map.put(T_ID, healthId);
        return map;
    }


    /**
     * 获取gif图片列表map
     *
     * @return 返回map信息
     */
    public static HashMap<String, String> getJokeOrGifListMap(int page, int maxResult) {
        HashMap<String, String> map = new HashMap<>();
        map.put(SHOW_APP_ID, Constants.SHOW_API_ID);
        map.put(SHOW_API_SIGN, Constants.SHOW_API_KEY);
        map.put(PAGE, String.valueOf(page));
        map.put(MAX_RESULT, String.valueOf(maxResult));
        return map;
    }

    /**
     * 获取不得姐参数列表
     *
     * @return 返回map信息
     */
    public static HashMap<String, String> getMaySisterMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put(SHOW_APP_ID, Constants.SHOW_API_ID);
        map.put(SHOW_API_SIGN, Constants.SHOW_API_KEY);
        map.put(TYPE, "29");
        return map;
    }

    /**
     * 获取小姐姐分类信息map
     *
     * @param type   类型
     * @param page   页码
     * @param result 结果
     * @return 返回map参数信息
     */
    public static HashMap<String, String> getSisterClassifyMap(int type, int page, int result) {
        HashMap<String, String> map = new HashMap<>();
        map.put(SHOW_APP_ID, Constants.SHOW_API_ID);
        map.put(SHOW_API_SIGN, Constants.SHOW_API_KEY);
        map.put(TYPE, String.valueOf(type));
        map.put(PAGE, String.valueOf(page));
        map.put(ROW_S, String.valueOf(result));
        return map;
    }


}
