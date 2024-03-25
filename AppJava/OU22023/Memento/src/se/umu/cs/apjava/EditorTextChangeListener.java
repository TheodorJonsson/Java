public interface EditorTextChangeListener {
    /**
     * State of a text model has changed content.
     * @param editorText the model that changed content
     */
    void stateUpdated(NoteInterface editorText);
}
