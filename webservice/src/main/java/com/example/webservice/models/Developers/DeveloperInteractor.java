package com.example.webservice.models.Developers;


public interface DeveloperInteractor {
    public void getDeveloperData(int id);
    public void updateDeveloperData(int id, String name, String address, String email);
    public void setScalarListener(DeveloperScalarListener listener);
    public void setUpdateListener(DeveloperUpdateListener listener);
}
