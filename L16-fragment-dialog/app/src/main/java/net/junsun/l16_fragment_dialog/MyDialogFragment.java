package net.junsun.l16_fragment_dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jsun on 2/15/2016.
 */
public class MyDialogFragment extends DialogFragment {
    public interface MyDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // here we use dialog builder to create a dialog view
        // alternatively one can inflate any view and use it for dialog
        Bundle bundle = getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final MyDialogListener listener = (MyDialogListener)getActivity();
        builder.setMessage(bundle.getString("message"))
                .setPositiveButton(bundle.getString("yesButton"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick(MyDialogFragment.this);
                    }
                })
                .setNegativeButton(bundle.getString("noButton"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(MyDialogFragment.this);
                    }
                })
                .setTitle(bundle.getString("title"))
                .setIcon(R.mipmap.ic_launcher);

        // attributes belong to the fragment, not the view anymore
        setCancelable(false);

        return builder.create();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("jsun", "onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("jsun", "onResume()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("jsun", "onDestroy()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("jsun", "onPause()");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("jsun", "onAttach()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("jsun", "onDetach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("jsun", "onCreate()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("jsun", "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("jsun", "onStart()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("jsun", "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("jsun", "onDestroyView()");
    }
}
