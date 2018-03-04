package note.lym.org.noteproject.model.http.api;

import java.util.Map;

import io.reactivex.Flowable;
import note.lym.org.noteproject.model.bean.Health;
import note.lym.org.noteproject.model.bean.HealthDetail;
import note.lym.org.noteproject.model.bean.HealthList;
import note.lym.org.noteproject.model.bean.MaySisterData;
import note.lym.org.noteproject.model.bean.NewsDetailBean;
import note.lym.org.noteproject.model.bean.NewsList;
import note.lym.org.noteproject.model.bean.TextJoke;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * doc 咨询相关api
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface NewsApi {
    /**
     * 获取网易头条
     */
    @GET("http://c.m.163.com/nc/article/headline/T1348647909107/{id}-20.html")
    Flowable<NewsList> getNews(@Path("id") int id);

    /**
     * 获取网易头条详情
     */
    @GET("http://c.m.163.com/nc/article/{newsId}/full.html")
    Flowable<Map<String, NewsDetailBean>> getNewsDetail(@Path("newsId") String newsId);


    /**
     * 获取笑话大全
     */
    @GET("http://route.showapi.com/341-1")
    Flowable<TextJoke> getTextJoke(@QueryMap Map<String, String> map);

    /**
     * 获取健康分类资讯
     */
    @GET("http://route.showapi.com/96-108")
    Flowable<Health> getHealthList(@QueryMap Map<String, String> map);

    /**
     * 获取健康分类资讯
     */
    @GET("http://route.showapi.com/96-109")
    Flowable<HealthList> getHealthListData(@QueryMap Map<String, String> map);

    /**
     * 获取健康分类资讯详情
     */
    @GET("http://route.showapi.com/96-36")
    Flowable<HealthDetail> getHealthDetailData(@QueryMap Map<String, String> map);

    /**
     * 获取不得姐数据
     */
    @GET("http://route.showapi.com/255-1")
    Flowable<MaySisterData> getMaySisterData(@QueryMap Map<String, String> map);
}
