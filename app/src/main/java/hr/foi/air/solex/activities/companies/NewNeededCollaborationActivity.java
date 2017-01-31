package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import hr.foi.air.solex.utils.Utility;
import hr.foi.air.solex.models.collaboration.NeededCollaboration;
import hr.foi.air.solex.models.collaboration.NeededCollaborationInteractorImpl;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.companies.NewNeededCollaborationPresenter;
import hr.foi.air.solex.presenters.companies.NewNeededCollaborationPresenterImpl;
import me.drakeet.materialdialog.MaterialDialog;

public class NewNeededCollaborationActivity extends DrawerActivity implements NewNeededCollaborationView {

    @BindView(R.id.activity_new_needed_collaboration_btnAddCollaboration)
    Button btnAddCollaboration;

    @BindView(R.id.activity_new_needed_collaboration_etName)
    EditText etCollabName;

    @BindView(R.id.activity_new_needed_collaboration_etDescription)
    EditText etCollabDescription;

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
            projekt = savedInstanceState.getInt("projectId");

        }
        //fillSpinnerSkills();
        fillSpinnerCooperationType();
        mPresenter.getAllSkills();
        lvNeededSkills.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
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
            Utility.setListViewHeightBasedOnChildren(lvNeededSkills, 5);
        }
    }

    @OnClick(R.id.activity_new_needed_collaboration_btnAddCollaboration)
    public void btnAddCollaborationClick(){

        if (etCollabName.getText().toString().isEmpty() || etCollabDescription.getText().toString().isEmpty()  || stateTypes == 0) {
            Toast.makeText(getApplicationContext(), R.string.fill_data, Toast.LENGTH_LONG).show();
        }
        else {
            //int id= Integer.parseInt(projekt);
            //if (projekt.isEmpty()){
            //    id= mCollab.getProjectId();
            //}
            mCollab.setProjectId(projekt);
            mCollab.setName(etCollabName.getText().toString());
            mCollab.setDescription(etCollabDescription.getText().toString());
            mCollab.setTypeOfWork(spICooperationType.getSelectedIndex()+1);//+1 jer ID-ovi kreću od 1 ne od 0 (za A nije htjelo dodat u bazu)
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
                        details=getString(R.string.typeA_details);
                        fillDialog(getString(R.string.typeA),details,1);
                        break;
                    case 1:
                        details=getString(R.string.typeB_details);
                        fillDialog(getString(R.string.typeB),details,2);
                        break;
                    case 2:
                        details=getString(R.string.typeC_details);
                        fillDialog(getString(R.string.typeC),details,3);
                        break;
                    case 3:
                        details=getString(R.string.typeD_details);
                        fillDialog(getString(R.string.typeD),details,4);
                        break;
                    case 4:
                        details=getString(R.string.typeE_details);
                        fillDialog(getString(R.string.typeE),details,5);
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
                .setPositiveButton(getString(R.string.OK), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        stateTypes = type;
                    }
                })
                .setNegativeButton(getString(R.string.CANCEL), new View.OnClickListener() {
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
        Toast.makeText(getApplicationContext(), R.string.new_needed_collaboration_created, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ProjectManagementActivity.class);
        intent.putExtra("projectId",projekt);
        startActivity(intent);

    }
}
