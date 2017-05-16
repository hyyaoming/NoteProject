package note.lym.org.noteproject.model.http;


import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import note.lym.org.noteproject.model.bean.Belle;
import note.lym.org.noteproject.model.bean.Health;
import note.lym.org.noteproject.model.bean.HealthDetail;
import note.lym.org.noteproject.model.bean.HealthList;
import note.lym.org.noteproject.model.bean.Joke;
import note.lym.org.noteproject.model.bean.MaySisterData;
import note.lym.org.noteproject.model.bean.NewsDetailBean;
import note.lym.org.noteproject.model.bean.NewsList;
import note.lym.org.noteproject.model.bean.SisterClassList;
import note.lym.org.noteproject.model.bean.SisterList;
import note.lym.org.noteproject.model.bean.TextJoke;
import note.lym.org.noteproject.model.bean.xxxData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface NoteApis {

    //测试环境
    String HOST = "http://gank.io/";


    @GET("api/data/Android/{size}/{page}")
    Flowable<HttpResponse<List<xxxData>>> getData(@Path("size") int size, @Path("page") int page);

    /**
     * 获取福利图片
     */
    @GET("/api/data/福利/10/{page}")
    Flowable<Belle> getBelleData(@Path("page") int page);

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
     * 获取gif图片
     */
    @GET("https://route.showapi.com/341-3")
    Flowable<Joke> getJokes(@QueryMap Map<String, String> map);

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
     * 获取不得姐数据
     */
    @GET("http://route.showapi.com/255-1")
    Flowable<MaySisterData> getMaySisterData(@QueryMap Map<String, String> map);
}