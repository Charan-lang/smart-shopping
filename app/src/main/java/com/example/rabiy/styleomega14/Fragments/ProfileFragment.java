package com.example.rabiy.styleomega14.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rabiy.styleomega14.Models.User;
import com.example.rabiy.styleomega14.R;
import com.example.rabiy.styleomega14.SharedPrefUtility;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_profile, container, false);

        final EditText edtTxtupdateFName=rootview.findViewById(R.id.updatefName_et);
        final EditText edtTxtupdateLName=rootview.findViewById(R.id.updatelName_et);
        final EditText edtTxtupdateEmail=rootview.findViewById(R.id.updateemail_et);
        final EditText edtTxtupdatePhoneNo=rootview.findViewById(R.id.updatephone_et);
        final EditText edtTxtupdateDOB=rootview.findViewById(R.id.updatedob_et);
        final EditText edtTxtupdatePsw=rootview.findViewById(R.id.updatepsw_et);
        final EditText edtTxtupdateConfirmPsw=rootview.findViewById(R.id.update_confirmpsw_et);
        Button btnUpdateProfile=rootview.findViewById(R.id.update_btn);

        String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
        List<User> users=User.findWithQuery(User.class,"Select * from User where email = ? ",userEmail);
        for (User user:users) {
            edtTxtupdateFName.setText(user.getfName());
            edtTxtupdateLName.setText(user.getlName());
            edtTxtupdateEmail.setText(user.getEmail());
            edtTxtupdatePhoneNo.setText(user.getPhoneNo());
            edtTxtupdateDOB.setText(user.getDob());
        }

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
                List<User> users=User.findWithQuery(User.class,"Select * from User where email = ? ",userEmail);
                User updateUser = null;
                for (User user:users) {
                    long userId = user.getId();
                    updateUser=User.findById(User.class,userId);
                    updateUser.setfName(edtTxtupdateFName.getText().toString());
                    updateUser.setlName(edtTxtupdateLName.getText().toString());
                    updateUser.setEmail(edtTxtupdateEmail.getText().toString());
                    updateUser.setPhoneNo(edtTxtupdatePhoneNo.getText().toString());
                    updateUser.setDob(edtTxtupdateDOB.getText().toString());
                    if(edtTxtupdateConfirmPsw.getText().equals(edtTxtupdatePsw.getText())){
                        updateUser.setPassword(edtTxtupdatePsw.getText().toString());
                    }else if(!(edtTxtupdateConfirmPsw.getText().equals(edtTxtupdatePsw.getText()))){
                        Toast.makeText(getActivity().getApplicationContext(), "Passwords Don't Match ",
                                Toast.LENGTH_LONG).show();
                    }
                    
                }
                showDialog();
                updateUser.save();
            }
        });
        return rootview;
    }

    public void showDialog(){
        // get prompts_psw.xml view
        LayoutInflater li = LayoutInflater.from(ProfileFragment.this.getActivity());
        View promptsView = li.inflate(R.layout.prompt_psw, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                ProfileFragment.this.getActivity());

        // set prompts_psw.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.pswDialogUserInput_et);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and verify password
                                String userEmail=SharedPrefUtility.read(SharedPrefUtility.EMAIL,null);
                                List<User> users=User.findWithQuery(User.class,
                                        "Select * from User where email = ? ",userEmail);
                                for (User user:users) {
                                    if (user.getPassword().equals(userInput.getText().toString())) {
                                        Toast.makeText(getActivity().getApplicationContext(), 
                                                "Profile Updated! ", Toast.LENGTH_LONG).show();
                                    }else{
                                        String message = "The password you have entered is incorrect."
                                                + " \n \n" + "Please try again!";
                                        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileFragment.this.getActivity());
                                        builder.setTitle("Error");
                                        builder.setMessage(message);
                                        builder.setPositiveButton("Cancel", null);
                                        builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                showDialog();
                                            }
                                        });
                                        builder.create().show();
                                    }
                                }

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

}
