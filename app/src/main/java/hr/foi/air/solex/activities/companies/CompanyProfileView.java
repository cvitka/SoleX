package hr.foi.air.solex.activities.companies;

import hr.foi.air.solex.models.mcompanies.Company;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

import java.util.List;

/**
 * Created by Asus on 4.12.2016..
 */

public interface CompanyProfileView {
    public void DataArrived(Company company);
    public void HighlihtedProjectsArrived(List<ProfileScreenProject> list);
    public void allSkillsListArrived(List<String> list);
    public void companySkillsListArrived(List<String> list);
}
