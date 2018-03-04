package note.lym.org.noteproject.model.dao;

import org.litepal.crud.DataSupport;

import java.util.List;

import note.lym.org.noteproject.model.bean.Note;

/**
 * doc 便签相关工具类
 *
 * @author yaoming.li
 * @since 2018/3/4
 */

public class NoteDao {

    private NoteDao() {

    }

    /**
     * the note is insert db
     *
     * @param note note
     * @return insert note whether success
     */
    public static boolean insertNote(Note note) {
        return note.save();
    }

    /**
     * return db is all note
     *
     * @return all note
     */
    public static List<Note> findAllNotes() {
        return DataSupport.findAll(Note.class);
    }

}
