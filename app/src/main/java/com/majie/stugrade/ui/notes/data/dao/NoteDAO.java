package com.majie.stugrade.ui.notes.data.dao;

import com.google.inject.ImplementedBy;
import com.majie.stugrade.ui.notes.data.Note;
import com.majie.stugrade.ui.notes.data.dao.impl.sqlite.NoteSQLiteDAO;


import java.util.List;

@ImplementedBy(NoteSQLiteDAO.class)
public interface NoteDAO {

    List<Note> fetchAll();

    void insert(Note note);

    void update(Note note);

    void delete(Note note);
}