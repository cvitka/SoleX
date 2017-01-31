package hr.foi.air.solex.models.mdevelopers;


public interface DeveloperInteractor {
    /**Dohvati podatke za developera */
    public void getDeveloperData(int id);
    /**Azuriraj podatke developeta */
    public void updateDeveloperData(int id, String name,String surname, String address, String email,String number,String years,String image);
    /**Javi da su podatci stigli */
    public void setScalarListener(DeveloperScalarListener listener);
    /**Javi da je napravljeno azuriranje */
    public void setUpdateListener(DeveloperUpdateListener listener);
}
