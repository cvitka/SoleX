package hr.foi.air.solex.models.profile_screen_project;

/** Listener za istaknute projekte*/
public interface AddHighlightListener {
    void onHighlightsAdd();
    void onHighlightsAddFailure(String message);
}
