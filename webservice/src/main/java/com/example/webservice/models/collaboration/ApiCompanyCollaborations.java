package com.example.webservice.models.collaboration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiCompanyCollaborations {
        @SerializedName("developeriId")
        @Expose
        private String devID;

        @SerializedName("ime")
        @Expose
        private String devName;

        @SerializedName("prezime")
        @Expose
        private String devSurname;

        @SerializedName("naziv")
        @Expose
        private String projectName;

        public String getDevID() {
            return devID;
        }

        public void setDevID(String devID) {
            this.devID = devID;
        }

        public String getDevName() {
            return devName;
        }

        public void setDevName(String devName) {
            this.devName = devName;
        }

        public String getDevSurname() {
            return devSurname;
        }

        public void setDevSurname(String devSurname) {
            this.devSurname = devSurname;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
}
