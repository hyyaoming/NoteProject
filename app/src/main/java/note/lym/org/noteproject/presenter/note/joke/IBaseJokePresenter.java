package note.lym.org.noteproject.presenter.note.joke;

/**
 * 获取笑话数据
 *
 * @author yaoming.li
 * @since 2017-05-11 16:04
 */
public interface IBaseJokePresenter {
    void getJokeData(int maxResult, int page);
}
