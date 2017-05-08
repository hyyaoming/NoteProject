package note.lym.org.noteproject.model.http;


import java.util.List;

import io.reactivex.Flowable;
import note.lym.org.noteproject.model.bean.Belle;
import note.lym.org.noteproject.model.bean.xxxData;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NoteApis {

    //测试环境
    String HOST = "http://gank.io/";


    @GET("api/data/Android/{size}/{page}")
    Flowable<HttpResponse<List<xxxData>>> getData(@Path("size") int size, @Path("page") int page);

    @GET("/api/data/福利/10/{page}")
    Flowable<Belle> getBelleData(@Path("page") int page);

}