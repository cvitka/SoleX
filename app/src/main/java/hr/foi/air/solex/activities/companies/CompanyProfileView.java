package hr.foi.air.solex.activities.companies;

import com.example.webservice.models.mcompanies.Company;
import com.example.webservice.models.profile_screen_project.ProfileScreenProject;

import java.util.List;

/**
 * Created by Asus on 4.12.2016..
 */

public interface CompanyProfileView {
    public void DataArrived(Company company);
    public void HighlihtedProjectsArrived(List<ProfileScreenProject> list);
}
