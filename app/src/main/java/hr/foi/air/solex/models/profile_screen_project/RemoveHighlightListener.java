package hr.foi.air.solex.models.profile_screen_project;

/**Listener za brisanje projekata iz istaknutih */
public interface RemoveHighlightListener {
    void onRemove();
    void onRemoveFailure(String message);
}
