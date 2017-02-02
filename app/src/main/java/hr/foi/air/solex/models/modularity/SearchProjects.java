package hr.foi.air.solex.models.modularity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SearchProjects implements Parcelable {
    private List<String > skills;
    private int percentage;
    protected SearchProjects(Parcel in) {
        skills = in.createStringArrayList();
        percentage = in.readInt();
    }

    public static final Creator<SearchProjects> CREATOR = new Creator<SearchProjects>() {
        @Override
        public SearchProjects createFromParcel(Parcel in) {
            return new SearchProjects(in);
        }

        @Override
        public SearchProjects[] newArray(int size) {
            return new SearchProjects[size];
        }
    };

    public SearchProjects() {
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(skills);
        dest.writeInt(percentage);
    }
}
