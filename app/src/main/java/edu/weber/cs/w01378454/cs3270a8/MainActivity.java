package edu.weber.cs.w01378454.cs3270a8;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import edu.weber.cs.w01378454.cs3270a8.DB.Course;

public class MainActivity extends AppCompatActivity implements CourseRecyclerAdapter.OnListChange {

    FragmentManager fm = getSupportFragmentManager();
    CourseEditFragment cef = new CourseEditFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.CourseListFragmentHolder, new CourseListFragment(), "Course_List_Fragment")
                .addToBackStack(null)
                .commit();

    }



    @Override
    public void OnListUpdate(Course course)
    {
        if(cef == null)
        {
            cef = new CourseEditFragment();
            cef.setCourse(course);
        }
        else
        {
            cef.setCourse(course);
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.CourseListFragmentHolder, cef, "Course_Edit_Fragment")
                //.setCourse(course)
                .addToBackStack(null)
                .commit();
        fm.executePendingTransactions();

        Log.d("Main Activity ", course.toString());
    }
}