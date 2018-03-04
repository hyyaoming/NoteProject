package note.lym.org.noteproject.model.http.helper;

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
import retrofit2.http.QueryMap;

/**
 * doc  请求相关
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public interface HttpHelper {

    //---------------------------- 图片相关-----------------------

    /**
     * 获取福利图片
     *
     * @param page  页码
     * @param count 请求条数
     * @return 返回Belle
     */
    Flowable<Belle> getBelleData(int page, int count);

    /**
     * 获取gif图片
     *
     * @param map 获取gif图片的参数,用户map包装起来
     * @return 返回joke
     */
    Flowable<Joke> getJokes(Map<String, String> map);

    /**
     * 获取图片列表分类
     *
     * @param map 获取图片列表分类参数，用map包装起来
     * @return 图片列表
     */
    Flowable<SisterList> getSisterListData(Map<String, String> map);

    /**
     * 获取图片列表
     *
     * @param map 获取图片列表参数，用map包装起来
     * @return 返回图片列表
     */
    Flowable<SisterClassList> getSisterClassifyList(Map<String, String> map);

    /**
     * 获取具体类别的图片
     *
     * @param map 获取具体类别的参数列表,用map包装起来
     * @return 返回具体类别的图片
     */
    Flowable<MoreType> getPersonToLife(Map<String, String> map);

    //---------------------- 咨询相关api---------------------------

    /**
     * 获取网易新闻咨询
     *
     * @param id 获取新闻参数 id
     * @return 返回新闻
     */
    Flowable<NewsList> getNews(int id);

    /**
     * 获取网易头条详情
     *
     * @param newsId 获取网易新闻详情，newId
     * @return 返回Map包装后的数据
     */
    Flowable<Map<String, NewsDetailBean>> getNewsDetail(String newsId);

    /**
     * 获取笑话大全
     *
     * @param map 获取笑话大全map参数信息
     * @return 返回笑话大全信息
     */
    Flowable<TextJoke> getTextJoke(Map<String, String> map);

    /**
     * 获取健康分类资讯
     *
     * @param map 获取健康咨询分类map参数信息
     * @return 返回健康分类咨询信息
     */
    Flowable<Health> getHealthClassifyList(Map<String, String> map);


    /**
     * 获取健康咨询列表
     *
     * @param map 获取健康咨询列表map参数信息
     * @return 返回健康咨询信息
     */
    Flowable<HealthList> getHealthListData(Map<String, String> map);


    /**
     * 获取健康分类资讯详情
     *
     * @param map 获取咨询详情map参数信息
     * @return 返回咨询详情信息
     */
    Flowable<HealthDetail> getHealthDetailData(Map<String, String> map);

    /**
     * 获取不得姐数据
     *
     * @param map 获取不得姐数据map参数信息
     * @return 返回不得姐信息
     */
    Flowable<MaySisterData> getMaySisterData(Map<String, String> map);

    /**
     * 获取具体图片类别
     *
     * @param map map
     * @return 返回具体图类别
     */
    Flowable<LookerGirl> getLookerGirl(Map<String, String> map);


}
