package com.example.webservice.Companies;

import java.sql.Blob;


public interface WSHandlerCompany {
    void onDataCome(int id, String name, String address, String email, Blob picture);
}
