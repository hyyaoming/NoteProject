package note.lym.org.noteproject.model.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * @author yaoming.li
 * @since 2017-04-26 09:52
 */
public class Note extends DataSupport implements Serializable {

    public int noteId;
    public String noteName;
    public String content;
    public String date;


}
