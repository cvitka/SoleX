package com.example.webservice.Developers;

import java.sql.Blob;

public interface WSHandlerDeveloper {
    void onDataComeDeveloper(int id, String name, String surname, String address, String email, String number, String years, Blob picture);
}
