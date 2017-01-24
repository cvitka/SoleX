package hr.foi.air.solex.activities.companies;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.projects.Project;
import hr.foi.air.solex.models.projects.ProjectInteractorImpl;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.AddNewProjectPresenter;
import hr.foi.air.solex.presenters.AddNewProjectPresenterImpl;

public class AddNewProjectActivity extends DrawerActivity implements AddNewProjectView {
    @BindView(R.id.btnSaveProject)
    Button btnSaveProject;

    @BindView(R.id.activity_new_project_etProjectName)
    EditText etProjectName;

    @BindView(R.id.activity_new_project_etDescription)
    EditText etDescription;

    @BindView(R.id.activity_new_project_tvProjectDate)
    TextView tvProjectDate;

    @BindView(R.id.activity_new_project_spProjectState)
    MaterialSpinner spinner;

    @BindView(R.id.activity_new_project_ivDateRange)
    ImageView ivDate;

    int stateData;
    Calendar myCalendar = Calendar.getInstance();
    Project mProject = new Project();
    private AddNewProjectPresenter mAddNewProjectPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_project;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        this.mAddNewProjectPresenter = new AddNewProjectPresenterImpl(this, new ProjectInteractorImpl());
        fillSpinner();
    }

    @OnClick(R.id.btnSaveProject)
    public void btnSaveProjectClick() {
        if (etProjectName.getText().toString().isEmpty() || etDescription.getText().toString().isEmpty() || tvProjectDate.getText().toString().isEmpty() || stateData == 0) {
            Toast.makeText(getApplicationContext(), "Fill in all data", Toast.LENGTH_LONG).show();
        } else {
            mProject.setName(etProjectName.getText().toString());
            mProject.setDescription(etDescription.getText().toString());
            mProject.setStateId(stateData);
            mProject.setCompanyId(User.getInstance().getId());

            mAddNewProjectPresenter.createNewProject(mProject);
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @OnClick(R.id.activity_new_project_ivDateRange)
    public void onClick() {
        new DatePickerDialog(AddNewProjectActivity.this, R.style.DatePicker, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mProject.setStartDate(sdf.format(myCalendar.getTime()));
        tvProjectDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void fillSpinner() {
        spinner.setItems("Chose state","Start project", "In progress", "Finished", "Paused", "Stopped");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                switch (position) {
                    case 0:
                        stateData = 0;
                        break;
                    case 1:
                        stateData = 1;
                        break;
                    case 2:
                        stateData = 2;
                        break;
                    case 3:
                        stateData = 3;
                        break;
                    case 4:
                        stateData = 4;
                        break;
                    case 5:
                        stateData = 5;
                        break;
                    default:
                        stateData = 0;
                }
            }
        });
    }

    @Override
    public void onCreation() {
        Toast.makeText(this, "The project has been created", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CompanyProjectsActivity.class);
        startActivity(intent);
    }
}
