package com.example.webservice.Companies;

import java.sql.Blob;

/**
 * Created by tomislav on 11/30/16.
 */

public interface WSHandlerCompany {
    void onDataCome(int id, String name, String address, String email, Blob picture);
}
