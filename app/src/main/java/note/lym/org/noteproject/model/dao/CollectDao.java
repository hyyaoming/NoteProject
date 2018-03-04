package note.lym.org.noteproject.model.dao;

import org.litepal.crud.DataSupport;

import java.util.List;

import note.lym.org.noteproject.model.bean.Collect;

/**
 * doc  图片收藏相关数据库操作
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public class CollectDao {
    private CollectDao() {

    }

    /**
     * find all collect by findRule
     *
     * @param findRule find collect is rule
     * @return db all collect
     */
    public static List<Collect> findAllCollectById(String... findRule) {
        return DataSupport.where(findRule).find(Collect.class);
    }

    /**
     * find collect by findRule
     *
     * @param findRule find collect is rule by first
     * @return db all collect but is return first
     */
    public static Collect findCollectById(String... findRule) {
        return DataSupport.where(findRule).findFirst(Collect.class);
    }

    /**
     * insert collect to db
     *
     * @param collect the collect is insert to db
     * @return insert collect to db whether success
     */
    public static boolean insertCollect(Collect collect) {
        return collect.save();
    }
}
