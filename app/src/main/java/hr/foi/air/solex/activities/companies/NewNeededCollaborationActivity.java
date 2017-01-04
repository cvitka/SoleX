package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.core.utils.Utility;
import com.example.webservice.models.collaboration.NeededCollaboration;
import com.example.webservice.models.collaboration.NeededCollaborationInteractorImpl;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.companies.ProjectManagementActivity;
import hr.foi.air.solex.presenters.NewNeededCollaborationPresenter;
import hr.foi.air.solex.presenters.NewNeededCollaborationPresenterImpl;
import me.drakeet.materialdialog.MaterialDialog;

public class NewNeededCollaborationActivity extends DrawerActivity implements NewNeededCollaborationView {

    @BindView(R.id.activity_new_needed_collaboration_btnAddCollaboration)
    Button btnAddCollaboration;

    @BindView(R.id.activity_new_needed_collaboration_etName)
    EditText etCollabName;

    @BindView(R.id.activity_new_needed_collaboration_etDescription)
    EditText etCollabDescription;

    //@BindView(R.id.activity_new_needed_collaboration_spItSkills)
    //MaterialSpinner spItSkills;
    @BindView(R.id.activity_new_needed_collaboration_btnAddNewSkill)
    ImageButton btnAddNewSkill;
    @BindView(R.id.activity_new_needed_collaboration_lvNeededSkills)
    ListView lvNeededSkills;
    @BindView(R.id.activity_new_needed_collaboration_actvNewSkill)
    AutoCompleteTextView actvNewSkill;


    @BindView(R.id.activity_new_needed_collaboration_spICooperationType)
    MaterialSpinner spICooperationType;

    @BindView(R.id.activity_new_needed_collaboration_scrollView)
    ScrollView scrollView;

    private int stateSkills;
    private int stateTypes;
    private String details;
    private int projekt;

    MaterialDialog mMaterialDialog;
    private NewNeededCollaborationPresenter mPresenter;
    NeededCollaboration mCollab = new NeededCollaboration();
    List<String> neededSkillsList;
    ArrayAdapter<String> neededSkillsAdapter;
    List<String> allSkills;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_needed_collaboration;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        this.mPresenter = new NewNeededCollaborationPresenterImpl(this,new NeededCollaborationInteractorImpl());


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                //projekt = null;
            } else {
                projekt= extras.getInt("projectId");
            }
        } else {
            projekt= (int) savedInstanceState.getSerializable("projectId");

        }
        //fillSpinnerSkills();
        fillSpinnerCooperationType();
        mPresenter.getAllSkills();
    }

    @Override
    public void onAllSkillsArrived(List<String> skills) {
        allSkills = skills;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, allSkills);
        actvNewSkill.setAdapter(adapter);

        neededSkillsList = new ArrayList<>();
        neededSkillsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, neededSkillsList);
        lvNeededSkills.setAdapter(neededSkillsAdapter);
    }

    @OnClick(R.id.activity_new_needed_collaboration_btnAddNewSkill)
    public void btnAddNewSkillClick(){
        String skill = actvNewSkill.getText().toString();
        if(!allSkills.contains(skill)){
            showToastLong(getResources().getString(R.string.error_adding_skill));
        }
        else if(neededSkillsList.contains(skill)) {
            showToastLong(getResources().getString(R.string.error_skill_exists));
        }
        else{
            //mCompanyProfilePresenter.addSkill(mThisCompany.getId(), skill);
            neededSkillsList.add(skill);
            neededSkillsAdapter.notifyDataSetChanged();
            actvNewSkill.setText("");
            Utility.setListViewHeightBasedOnChildren(lvNeededSkills);
        }
    }

    @OnClick(R.id.activity_new_needed_collaboration_btnAddCollaboration)
    public void btnAddCollaborationClick(){

        if (etCollabName.getText().toString().isEmpty() || etCollabDescription.getText().toString().isEmpty()  || stateTypes == 0) {
            Toast.makeText(getApplicationContext(), "Fill in all data!", Toast.LENGTH_LONG).show();
        }
        else {
            //int id= Integer.parseInt(projekt);
            //if (projekt.isEmpty()){
            //    id= mCollab.getProjectId();
            //}
            mCollab.setProjectId(projekt);
            mCollab.setName(etCollabName.getText().toString());
            mCollab.setDescription(etCollabDescription.getText().toString());
            mCollab.setTypeOfWork(spICooperationType.getSelectedIndex());
            mCollab.setNaknada(50);
            mCollab.setStrucnosti(neededSkillsList);
            mPresenter.addNeededCollaboration(mCollab);
        }
    }
/*
    private void fillSpinnerSkills() {
        spItSkills.setItems(".NET", "Java", "JavaScript", "PHP", "C#");
        spItSkills.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                switch (position) {
                    case 0:
                        stateSkills = 1;
                        break;
                    case 1:
                        stateSkills = 2;
                        break;
                    case 2:
                        stateSkills = 3;
                        break;
                    case 3:
                        stateSkills = 4;

                        break;
                    case 4:
                        stateSkills = 5;

                        break;
                    default:
                        stateSkills = 0;
                }
            }
        });
    }
/*/
    private void fillSpinnerCooperationType() {
        spICooperationType.setItems("Type A", "Type B", "Type C", "Type D", "Type E");
        spICooperationType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                switch (position) {
                    case 0:
                        details="Suradnik mora raditi u isto vrijeme kada i ostali radnici u firmi (Fiksno vrijeme svaki radni dan).";
                        fillDialog("Type A",details,1);
                        break;
                    case 1:
                        details="Suradnik mora svaki dan raditi, ali nije bitno u koliko sati.";
                        fillDialog("Type B",details,2);
                        break;
                    case 2:
                        details="Suradnik mora do kraja tjedna ispuniti unaprijed dogovorenu normu. Npr. do kraja tjedna treba 30h napraviti, ali nije bitno kada će već da bude do kraja tjedna.";
                        fillDialog("Type C",details,3);
                        break;
                    case 3:
                        details="Suradnik ima rok do kojeg treba napraviti funkcionalnost za koju je unamljen.";
                        fillDialog("Type D",details,4);
                        break;
                    case 4:
                        details="Suradnik mora raditi u isto vrijeme kada i ostali radnici u firmi (Fiksno vrijeme svaki radni dan), ali tijekom rada mora usko komunicirati sa razvojnim timom cijelo vrijeme putem neke audio ili čak video konferencije.";
                        fillDialog("Type E",details,5);
                        break;
                    default:
                        stateTypes = 0;
                }
            }
        });
    }

    private void fillDialog(String cooperationType, String details, final int type){
                mMaterialDialog = new MaterialDialog(this)
                .setTitle(cooperationType)
                .setMessage(details)
                .setPositiveButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        stateTypes = type;
                    }
                })
                .setNegativeButton("CANCEL", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        stateTypes = 0;
                    }
                });
        mMaterialDialog.show();
    }

    @Override
    public void onAdd() {
        Toast.makeText(getApplicationContext(), "New Needed Collaboration Has Been Created!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ProjectManagementActivity.class);
        intent.putExtra("projectId",projekt);
        startActivity(intent);

    }
}
