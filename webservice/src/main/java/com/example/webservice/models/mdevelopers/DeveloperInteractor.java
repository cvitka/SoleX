package com.example.webservice.models.mdevelopers;


public interface DeveloperInteractor {
    public void getDeveloperData(int id);
    public void updateDeveloperData(int id, String name,String surname, String address, String email,String number,String years,String image);
    public void setScalarListener(DeveloperScalarListener listener);
    public void setUpdateListener(DeveloperUpdateListener listener);
}
