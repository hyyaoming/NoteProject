package note.lym.org.noteproject.model.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 图片收藏
 *
 * @author yaoming.li
 * @since 2017-05-17 10:28
 */
public class Collect extends DataSupport implements Serializable {
    public String isCollect;
    public String url;
}
