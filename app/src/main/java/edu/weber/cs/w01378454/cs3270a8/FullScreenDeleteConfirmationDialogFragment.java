package edu.weber.cs.w01378454.cs3270a8;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FullScreenDeleteConfirmationDialogFragment extends DialogFragment
{

    public FullScreenDeleteConfirmationDialogFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        {
            setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_Dialog_FullScreen);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_screen_delete_confirmation_dialog, container, false);
    }
}