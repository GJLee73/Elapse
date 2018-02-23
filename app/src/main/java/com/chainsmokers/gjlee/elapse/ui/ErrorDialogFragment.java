package com.chainsmokers.gjlee.elapse.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chainsmokers.gjlee.elapse.R;

import org.w3c.dom.Text;

/**
 * Created by GILJAE on 2018-02-23.
 */

public class ErrorDialogFragment extends DialogFragment{

    private String messageError;

    public static ErrorDialogFragment newInstance (String messageError) {
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        errorDialogFragment.setMessageError(messageError);
        Bundle args = new Bundle ();
        errorDialogFragment.setArguments(args);
        return errorDialogFragment;
    }

    public ErrorDialogFragment () {
        //Empty Constructor
    }

    public void setMessageError (String messageError) {
        this.messageError = messageError;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null) {
            messageError = savedInstanceState.getString("MSG");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_error,null);
        TextView errorMessage = view.findViewById(R.id.errorMessage);
        errorMessage.setText(messageError);
        TextView buttonConfirm = view.findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("MSG",messageError);
    }
}
