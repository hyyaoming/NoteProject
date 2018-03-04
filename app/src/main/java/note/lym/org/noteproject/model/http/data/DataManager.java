package note.lym.org.noteproject.model.http.data;

import java.util.Map;

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
import note.lym.org.noteproject.model.http.helper.HttpHelper;

/**
 * doc  数据操作帮助类
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public class DataManager implements HttpHelper {
    private HttpHelper mHttpHelper;

    /**
     * 构造器
     *
     * @param httpHelper HttpHelper
     */
    public DataManager(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;
    }

    @Override
    public Flowable<Belle> getBelleData(int page, int count) {
        return mHttpHelper.getBelleData(page, count);
    }

    @Override
    public Flowable<Joke> getJokes(Map<String, String> map) {
        return mHttpHelper.getJokes(map);
    }

    @Override
    public Flowable<SisterList> getSisterListData(Map<String, String> map) {
        return mHttpHelper.getSisterListData(map);
    }

    @Override
    public Flowable<SisterClassList> getSisterClassifyList(Map<String, String> map) {
        return mHttpHelper.getSisterClassifyList(map);
    }

    @Override
    public Flowable<MoreType> getPersonToLife(Map<String, String> map) {
        return mHttpHelper.getPersonToLife(map);
    }

    @Override
    public Flowable<NewsList> getNews(int id) {
        return mHttpHelper.getNews(id);
    }

    @Override
    public Flowable<Map<String, NewsDetailBean>> getNewsDetail(String newsId) {
        return mHttpHelper.getNewsDetail(newsId);
    }

    @Override
    public Flowable<TextJoke> getTextJoke(Map<String, String> map) {
        return mHttpHelper.getTextJoke(map);
    }

    @Override
    public Flowable<Health> getHealthClassifyList(Map<String, String> map) {
        return mHttpHelper.getHealthClassifyList(map);
    }

    @Override
    public Flowable<HealthList> getHealthListData(Map<String, String> map) {
        return mHttpHelper.getHealthListData(map);
    }

    @Override
    public Flowable<HealthDetail> getHealthDetailData(Map<String, String> map) {
        return mHttpHelper.getHealthDetailData(map);
    }

    @Override
    public Flowable<MaySisterData> getMaySisterData(Map<String, String> map) {
        return mHttpHelper.getMaySisterData(map);
    }

    @Override
    public Flowable<LookerGirl> getLookerGirl(Map<String, String> map) {
        return mHttpHelper.getLookerGirl(map);
    }
}
