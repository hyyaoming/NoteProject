package note.lym.org.noteproject.app;

import java.io.File;

public class Constants {


    public static final String PATH_DATA = NoteApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/note.lym.org.noteproject.app.NetCache";
    public static final String NET_ERROR = "网络异常";
    public static final String TAG = "OnlyhiAPP";
    public static final String Async = "Async";

    public static final int NUM_OF_PAGE = 20;

    public static final String RESULT_COUNT = "10";

    //================= TYPE ==================== //
    public static final int TYPE_A = 101;
    public static final int TYPE_B = 102;
    public static final int TYPE_C = 103;



    //================= KEY ==================== //

    //================= Intent ================= //

    //================= sp_key ================= //
    public static final String TOKEN = "token";
    public static final String PHONE = "phone";
    public static final String PASSWORD = "Password";

    /**
     * show_api_id
     */
    public static final String SHOW_API_ID = "37893";
    /**
     * show_api_key
     */
    public static final String SHOW_API_KEY = "82294d27890648a69d314cbc3072b489";
}
