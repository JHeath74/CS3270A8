package edu.weber.cs.w01378454.cs3270a8;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.weber.cs.w01378454.cs3270a8.DB.Course;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.AllCourseViewHolder>
{
    private List<Course> courseList;
    private OnListChange mCallback;


    interface OnListChange
    {
        void OnListUpdate(Course course);
    }


    public CourseRecyclerAdapter(List<Course> courseList, Activity activity) {
        this.courseList = courseList;
        this.mCallback = (OnListChange) activity;

    }

    public void setCourseList(List<Course> courses)
    {
        courseList.clear();
        courseList.addAll(courses);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courseview,parent,false);

        return new AllCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCourseViewHolder holder, int position)
    {
       Course course = courseList.get(position);

       if(course != null)
       {
           holder.item = course;
           holder.tvCourseName.setText("Course Name: " + course.getCourse_name());



           holder.itemRoot.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                  mCallback.OnListUpdate(course);
                   Log.d("Adapter Course Info: ", course.toString());
                   /*AppCompatActivity activity = (AppCompatActivity) v.getContext();
                   Fragment myFragment = new CourseEditFragment();
                   activity.getSupportFragmentManager().beginTransaction()
                           .replace(R.id.CourseListFragmentHolder, myFragment)
                           .addToBackStack(null)
                           .commit();*/

               }
           });
       }

    }

    @Override
    public int getItemCount() {
        return courseList.size();

    }

    //ViewHolders Hold the ui of an individual item in the list.
    public class AllCourseViewHolder extends RecyclerView.ViewHolder
    {
        public View itemRoot;
        public TextView tvCourseName;
        public Course item;

        public AllCourseViewHolder(View view)
        {
            super(view);
            itemRoot = view;

            tvCourseName = itemRoot.findViewById(R.id.tvCourseName);

        }

    }
}
