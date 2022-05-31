package edu.weber.cs.w01378454.cs3270a8;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import edu.weber.cs.w01378454.cs3270a8.DB.AppDatabase;
import edu.weber.cs.w01378454.cs3270a8.DB.Course;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteConfirmationDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteConfirmationDialogFragment extends DialogFragment {

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

    public DeleteConfirmationDialogFragment() {
        // Required empty public constructor
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

    // TODO: Rename and change types and number of parameters
    public static DeleteConfirmationDialogFragment newInstance(String param1, String param2) {
        DeleteConfirmationDialogFragment fragment = new DeleteConfirmationDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.are_you_sure);
        builder.setMessage(R.string.delete_warning)
            .setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  //  deleteCourse();
                }
            })

                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    private void deleteCourse()
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

                //Delete a selected course (in the DAO)
                db.CourseDAO().DeleteSelectedCourse(new Course(id, course_name, course_code, start_at, end_at));

            }
        }).start();

        Id.getEditText().setText("");
        CourseName.getEditText().setText("");
        CourseCode.getEditText().setText("");
        StartAt.getEditText().setText("");
        EndAt.getEditText().setText("");

        Toast toast = Toast.makeText(getContext(), "Selected Course Deleted", Toast.LENGTH_SHORT);
        toast.show();
    }
}