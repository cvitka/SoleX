package com.example.webservice.models.collaboration;

public class NeededCollaboration {
    private int suradnjaId;
    private int projectId;
    private String name;
    private String description;
    private int typeOfWork;
    private char statusSuradnje;
    private float naknada;
    private int strucnosti;
    private char status;

    public NeededCollaboration() {
    }

    public int getSuradnjaId() {
        return suradnjaId;
    }

    public void setSuradnjaId(int suradnjaId) {
        this.suradnjaId = suradnjaId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(int typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public char getStatusSuradnje() {
        return statusSuradnje;
    }

    public void setStatusSuradnje(char statusSuradnje) {
        this.statusSuradnje = statusSuradnje;
    }

    public float getNaknada() {
        return naknada;
    }

    public void setNaknada(float naknada) {
        this.naknada = naknada;
    }

    public int getStrucnosti() {
        return strucnosti;
    }

    public void setStrucnosti(int strucnosti) {
        this.strucnosti = strucnosti;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
