package note.lym.org.noteproject.model.http;


public class HttpResponse<T> {

    public boolean error;
    public T results;


//    //token失效
//    public static final int TOKEN_LOSE = 112;
//
//    private boolean hasError;
//    private int code;
//    private String message;
//    private T data;
//
//
//    public boolean isHasError() {
//        return hasError;
//    }
//
//    public void setHasError(boolean hasError) {
//        this.hasError = hasError;
//    }
//
//    public String getMessage() {
//        if (code == TOKEN_LOSE) {
//            UIUtils.startLoginActivity(App.getInstance());
//        }
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }

}