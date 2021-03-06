package edu.weber.cs.w01378454.cs3270a8;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import edu.weber.cs.w01378454.cs3270a8.DB.AppDatabase;
import edu.weber.cs.w01378454.cs3270a8.DB.Course;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseEditFragment extends Fragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;

    private TextInputLayout Id;
    private TextInputLayout CourseName;
    private TextInputLayout CourseCode;
    private TextInputLayout StartAt;
    private TextInputLayout EndAt;

    public void setCourse(Course course)
    {
        this.course = course;
    }

    Course course;

    MenuItem delete;
    MenuItem edit;

    public CourseEditFragment()
    {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CourseEditFragment newInstance(String param1, String param2)
    {
        CourseEditFragment fragment = new CourseEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        Id = root.findViewById(R.id.Id);
        CourseName = root.findViewById(R.id.Course_Name);
        CourseCode = root.findViewById(R.id.Course_Code);
        StartAt = root.findViewById(R.id.Start_At);
        EndAt = root.findViewById(R.id.End_At);



    }

    public void updateText()
    {
        Id.getEditText().setText(course.getId());
        CourseName.getEditText().setText(course.getCourse_name());
        CourseCode.getEditText().setText(course.getCourse_code());
        StartAt.getEditText().setText(course.getStart_at());
        EndAt.getEditText().setText(course.getEnd_at());
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_course_edit, container, false);

        return root;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        delete = menu.add("Delete");
        delete.setIcon(R.drawable.ic_baseline_delete_24);

        edit = menu.add("Edit");
        edit.setIcon(R.drawable.ic_baseline_mode_edit_24);

        inflater.inflate(R.menu.main,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.editCourse:
                editSelectedCourse();
                return true;

            case R.id.deleteCourse:
             //   deleteCourse();
                DeleteConfirmationDialogFragment dialogFragment = new DeleteConfirmationDialogFragment();
                dialogFragment.setCancelable(false);
                dialogFragment.show(getChildFragmentManager(), "DeleteConfirmationDiaglogFragment");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();

      // updateText();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //Retrieve a list of courses (in the DAO)
                AppDatabase db = AppDatabase.getInstance(getContext());
                LiveData<List<Course>> courses = db.CourseDAO().ListAllCourses();
            }
        }).start();

    }

        /*private void deleteCourse()
         {
            final String id = Id.getText().toString();
            final String course_name = CourseName.getText().toString();
            final String course_code = CourseCode.getText().toString();
            final String start_at = StartAt.getText().toString();
            final String end_at = EndAt.getText().toString();

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    //Background

                    AppDatabase db = AppDatabase.getInstance(getContext());

                    //Delete a selected course (in the DAO)
                    db.CourseDAO().DeleteSelectedCourse(new Course(id, course_name, course_code, start_at, end_at));

                }
            }).start();

            Id.setText("");
            CourseName.setText("");
            CourseCode.setText("");
            StartAt.setText("");
            EndAt.setText("");

            Toast toast = Toast.makeText(getContext(), "Selected Course Deleted", Toast.LENGTH_SHORT);
            toast.show();
        }
*/
        private void editSelectedCourse()
        {
            final String id = Id.getEditText().getText().toString();
            final String course_name = CourseName.getEditText().getText().toString();
            final String course_code = CourseCode.getEditText().getText().toString();
            final String start_at = StartAt.getEditText().getText().toString();
            final String end_at = EndAt.getEditText().getText().toString();


            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    //Background
                    AppDatabase db = AppDatabase.getInstance(getContext());

                    if(course != null)
                    {

                    }

                    //Edit a selected course (in the DAO)
                   // db.CourseDAO().EditASelectedCourse(new Course(id, course_name, course_code, start_at, end_at));
                    db.CourseDAO().EditASelectedCourse(course);
                }
            }).start();

            Id.getEditText().setText("");
            CourseName.getEditText().setText("");
            CourseCode.getEditText().setText("");
            StartAt.getEditText().setText("");
            EndAt.getEditText().setText("");

            Toast toast = Toast.makeText(getContext(), "Selected Course Edited", Toast.LENGTH_SHORT);
            toast.show();
        }
    }




