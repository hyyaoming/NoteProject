package note.lym.org.noteproject.model.http;


import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import note.lym.org.noteproject.model.bean.Belle;
import note.lym.org.noteproject.model.bean.Joke;
import note.lym.org.noteproject.model.bean.NewsDetailBean;
import note.lym.org.noteproject.model.bean.NewsList;
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

    @GET("/api/data/福利/10/{page}")
    Flowable<Belle> getBelleData(@Path("page") int page);

    @GET("http://c.m.163.com/nc/article/headline/T1348647909107/{id}-20.html")
    Flowable<NewsList> getNews(@Path("id") int id );

    @GET("http://c.m.163.com/nc/article/{newsId}/full.html")
    Flowable<Map<String, NewsDetailBean>> getNewsDetail(@Path("newsId") String newsId);

    @GET("https://route.showapi.com/341-3")
    Flowable<Joke> getJokes(@QueryMap Map<String,String> map);

    @GET("http://route.showapi.com/341-1")
    Flowable<TextJoke> getTextJoke(@QueryMap Map<String,String> map);
}