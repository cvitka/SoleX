package hr.foi.air.solex.loaders;

import com.example.webservice.models.Developers.WSHandlerDeveloper;
import com.example.webservice.models.Developers.Developer;

import java.sql.Blob;

import hr.foi.air.solex.activities.Listeners.DeveloperDataListener;

public class DLDeveloper implements WSHandlerDeveloper {

    DeveloperDataListener mDeveloperDataListener;

    public DLDeveloper(DeveloperDataListener developerDataListener) {
        this.mDeveloperDataListener = developerDataListener;
    }
    @Override
    public void onDataComeDeveloper(int id, String name, String surname, String address, String email, String number, String years, Blob picture) {
        Developer developer = new Developer();
        developer.setId(id);
        developer.setIme(name);
        developer.setPrezime(surname);
        developer.setAdresa(address);
        developer.setEmail(email);
        developer.setKontaktBroj(number);
        developer.setGodineIskustva(years);
        developer.setSlika(picture);
        mDeveloperDataListener.DataArrivedDeveloepr(developer);
    }
}
