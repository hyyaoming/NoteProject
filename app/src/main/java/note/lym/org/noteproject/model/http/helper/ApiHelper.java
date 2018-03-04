package note.lym.org.noteproject.model.http.helper;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import note.lym.org.noteproject.model.bean.Belle;
import note.lym.org.noteproject.model.bean.Health;
import note.lym.org.noteproject.model.bean.HealthDetail;
import note.lym.org.noteproject.model.bean.HealthList;
import note.lym.org.noteproject.model.bean.Joke;
import note.lym.org.noteproject.model.bean.LookerGirl;
import note.lym.org.noteproject.model.bean.MaySisterData;
import note.lym.org.noteproject.model.bean.MoreType;
import note.lym.org.noteproject.model.bean.NewsDetailBean;
import note.lym.org.noteproject.model.bean.NewsList;
import note.lym.org.noteproject.model.bean.SisterClassList;
import note.lym.org.noteproject.model.bean.SisterList;
import note.lym.org.noteproject.model.bean.TextJoke;
import note.lym.org.noteproject.model.http.api.GirlApi;
import note.lym.org.noteproject.model.http.api.NewsApi;

/**
 * doc  api helper
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public class ApiHelper implements HttpHelper {

    private NewsApi mNewsApi;
    private GirlApi mGirlApi;

    @Inject
    public ApiHelper(NewsApi newsApi, GirlApi girlApi) {
        this.mGirlApi = girlApi;
        this.mNewsApi = newsApi;
    }

    @Override
    public Flowable<Belle> getBelleData(int page, int count) {
        return mGirlApi.getBelleData(page, count);
    }

    @Override
    public Flowable<Joke> getJokes(Map<String, String> map) {
        return mGirlApi.getJokes(map);
    }

    @Override
    public Flowable<SisterList> getSisterListData(Map<String, String> map) {
        return mGirlApi.getSisterListData(map);
    }

    @Override
    public Flowable<SisterClassList> getSisterClassifyList(Map<String, String> map) {
        return mGirlApi.getSisterClassifyList(map);
    }

    @Override
    public Flowable<MoreType> getPersonToLife(Map<String, String> map) {
        return mGirlApi.getPersonToLife(map);
    }

    @Override
    public Flowable<NewsList> getNews(int id) {
        return mNewsApi.getNews(id);
    }

    @Override
    public Flowable<Map<String, NewsDetailBean>> getNewsDetail(String newsId) {
        return mNewsApi.getNewsDetail(newsId);
    }

    @Override
    public Flowable<TextJoke> getTextJoke(Map<String, String> map) {
        return mNewsApi.getTextJoke(map);
    }

    @Override
    public Flowable<Health> getHealthClassifyList(Map<String, String> map) {
        return mNewsApi.getHealthList(map);
    }

    @Override
    public Flowable<HealthList> getHealthListData(Map<String, String> map) {
        return mNewsApi.getHealthListData(map);
    }

    @Override
    public Flowable<HealthDetail> getHealthDetailData(Map<String, String> map) {
        return mNewsApi.getHealthDetailData(map);
    }

    @Override
    public Flowable<MaySisterData> getMaySisterData(Map<String, String> map) {
        return mNewsApi.getMaySisterData(map);
    }

    @Override
    public Flowable<LookerGirl> getLookerGirl(Map<String, String> map) {
        return mGirlApi.getLookerGirl(map);
    }
}
