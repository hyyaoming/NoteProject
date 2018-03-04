package note.lym.org.noteproject.model.http.api;

import java.util.Map;

import io.reactivex.Flowable;
import note.lym.org.noteproject.model.bean.Belle;
import note.lym.org.noteproject.model.bean.Joke;
import note.lym.org.noteproject.model.bean.LookerGirl;
import note.lym.org.noteproject.model.bean.MoreType;
import note.lym.org.noteproject.model.bean.SisterClassList;
import note.lym.org.noteproject.model.bean.SisterList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * doc  妹纸相关api
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface GirlApi {

    //域名
    String HOST = "http://gank.io/";

    /**
     * 获取福利图片
     */
    @GET("/api/data/福利/{count}/{page}")
    Flowable<Belle> getBelleData(@Path("page") int page, @Path("count") int count);


    /**
     * 获取gif图片
     */
    @GET("https://route.showapi.com/341-3")
    Flowable<Joke> getJokes(@QueryMap Map<String, String> map);

    /**
     * 获取图片列表分类
     */
    @GET("http://route.showapi.com/1208-1")
    Flowable<SisterList> getSisterListData(@QueryMap Map<String, String> map);

    /**
     * 获取图片列表
     */
    @GET("http://route.showapi.com/1208-2")
    Flowable<SisterClassList> getSisterClassifyList(@QueryMap Map<String, String> map);

    /**
     * 获取更多的图片列表
     */
    @GET("http://route.showapi.com/852-1")
    Flowable<MoreType> getPersonToLife(@QueryMap Map<String, String> map);

    /**
     * 获取具体类别的图片
     */
    @GET("http://route.showapi.com/852-2")
    Flowable<LookerGirl> getLookerGirl(@QueryMap Map<String, String> map);
}
