package com.example.picoyplaca.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.picoyplaca.MainActivity;
import com.example.picoyplaca.R;
import com.example.picoyplaca.databases.LocalDbHelper;
import com.example.picoyplaca.models.ItemHistoryObject;
import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static Button mCheckButton;
    private static Button mClearButton;
    private static EditText mPlateInputText;
    private String plate;
    static final int INFRINGEMENT = 1;
    static final int NOT_INFRINGEMENT = 0;
    static AlertDialog.Builder alertBuilder;
    private LocalDbHelper mDb;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private onNewCheck mListener;

    public CheckingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckingFragment newInstance(String param1, String param2) {
        CheckingFragment fragment = new CheckingFragment();
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
        mDb=new LocalDbHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView;
        mView = inflater.inflate(R.layout.fragment_checking, container, false);
        mPlateInputText=mView.findViewById(R.id.plate_input_text);
        mCheckButton=mView.findViewById(R.id.check_button);
        mClearButton=mView.findViewById(R.id.clear_button);

        mCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plate = String.valueOf(mPlateInputText.getText());
                if(isValidatePlate(plate)){
                    checkPicoyPlaca(plate);
                }
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlateInputText.setText("");
            }
        });

        return mView;
    }

    private void checkPicoyPlaca(String plate) {
        int last_digit;
        last_digit = Character.getNumericValue(plate.charAt(mPlateInputText.length()-1));
        Calendar calendar = Calendar.getInstance();
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day_of_week) {
            case Calendar.MONDAY:
                if(last_digit == 1 || last_digit == 2){
                    showAlertDialog(NOT_INFRINGEMENT);
                }else{
                    if(isTimeInrange()){
                        showAlertDialog(INFRINGEMENT);
                    }else {
                        showAlertDialog(NOT_INFRINGEMENT);
                    }
                }
                break;
            case Calendar.TUESDAY:
                if(last_digit == 1 || last_digit == 2){
                    showAlertDialog(NOT_INFRINGEMENT);
                }else{
                    if(isTimeInrange()){
                        showAlertDialog(INFRINGEMENT);
                    }else {
                        showAlertDialog(NOT_INFRINGEMENT);
                    }
                }
                break;
            case Calendar.WEDNESDAY:
                if(last_digit == 1 || last_digit == 2){
                    showAlertDialog(NOT_INFRINGEMENT);
                }else{
                    if(isTimeInrange()){
                        showAlertDialog(INFRINGEMENT);
                    }else {
                        showAlertDialog(NOT_INFRINGEMENT);
                    }
                }
                break;
            case Calendar.THURSDAY:
                if(last_digit == 1 || last_digit == 2){
                    showAlertDialog(NOT_INFRINGEMENT);
                }else{
                    if(isTimeInrange()){
                        showAlertDialog(INFRINGEMENT);
                    }else {
                        showAlertDialog(NOT_INFRINGEMENT);
                    }
                }
                break;
            case Calendar.FRIDAY:
                if(last_digit == 1 || last_digit == 2){
                    showAlertDialog(NOT_INFRINGEMENT);
                }else{
                    if(isTimeInrange()){
                        showAlertDialog(INFRINGEMENT);
                    }else {
                        showAlertDialog(NOT_INFRINGEMENT);
                    }
                }
                break;
        }
    }

    private boolean isTimeInrange() {
        try {
            String string1 = "07:00:00";
            Date time1 = new SimpleDateFormat("HH:mm:ss").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);

            String string2 = "9:30:00";
            Date time2 = new SimpleDateFormat("HH:mm:ss").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            DateFormat df = new SimpleDateFormat("hh:mm:ss");
            String someRandomTime = df.format(new Date());
            Date d = new SimpleDateFormat("HH:mm:ss").parse(someRandomTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                return true;
            }else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void showAlertDialog(int Infringement) {
        View checkBoxView = View.inflate(getActivity(), R.layout.checkbox_exceptions, null);
        final CheckBox seniorCitizencheckBox= checkBoxView.findViewById(R.id.checkbox_senior_citizen);
        final CheckBox handicappedcheckBox = checkBoxView.findViewById(R.id.checkbox_handicapped);

        switch (Infringement) {
            case INFRINGEMENT:
                int total_infringements = mDb.count(mPlateInputText.getText().toString());
                alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setTitle("SÍ HAY CONTRAVENCIÓN");
                alertBuilder.setIcon(R.mipmap.ic_launcher);
                alertBuilder.setMessage("Número de reincidencias: "+ total_infringements);
                alertBuilder.setView(checkBoxView);
                alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Date date= new Date();
                        long time = date.getTime();
                        int seniorCitizenValue = 0;
                        int hadicappedValue = 0;
                        int infringement_calculated = 1;
                        if (seniorCitizencheckBox.isChecked()){
                            seniorCitizenValue=1;
                            infringement_calculated = 0;
                        }
                        if (handicappedcheckBox.isChecked()){
                            hadicappedValue=1;
                            infringement_calculated = 0;
                        }

                        ItemHistoryObject mItemHistoryObject = new ItemHistoryObject(mPlateInputText.getText().toString(),
                                String.valueOf(time),
                                seniorCitizenValue,
                                hadicappedValue,
                                infringement_calculated
                        );
                        mDb.addItemToHistory(mItemHistoryObject);
                        if (mListener != null) {
                            mListener.onCheck(mItemHistoryObject);
                        }
                    }
                });
                alertBuilder.setCancelable(false);
                alertBuilder.create().show();
                break;
            case NOT_INFRINGEMENT:
                total_infringements = mDb.count(mPlateInputText.getText().toString());
                alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setTitle("NO HAY CONTRAVENCIÓN");
                alertBuilder.setIcon(R.mipmap.ic_launcher);
                alertBuilder.setMessage("Número de reincidencias: "+ total_infringements);
                alertBuilder.setView(checkBoxView);
                alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Date date= new Date();
                        long time = date.getTime();
                        int seniorCitizenValue = 0;
                        int hadicappedValue = 0;
                        if (seniorCitizencheckBox.isChecked()){
                            seniorCitizenValue=1;
                        }
                        if (handicappedcheckBox.isChecked()){
                            hadicappedValue=1;
                        }

                        ItemHistoryObject mItemHistoryObject = new ItemHistoryObject(mPlateInputText.getText().toString(),
                                String.valueOf(time),
                                seniorCitizenValue,
                                hadicappedValue,
                                NOT_INFRINGEMENT
                                );
                        mDb.addItemToHistory(mItemHistoryObject);
                        if (mListener != null) {
                            mListener.onCheck(mItemHistoryObject);
                        }
                    }
                });
                alertBuilder.setCancelable(false);
                alertBuilder.create().show();
                break;
        }
    }

    private boolean isValidatePlate(String mPlateInputText) {
        char last_digit;
        last_digit = mPlateInputText.charAt(mPlateInputText.length()-1);
        if( ! Character.isDigit(last_digit)){
            Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "La placa debe terminar en un número", Snackbar.LENGTH_LONG);
            snackBar.show();
            return false;
        }else if(mPlateInputText.length() < 6){
            Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "Inserte un placa válida", Snackbar.LENGTH_LONG);
            snackBar.show();
            return false;
        }
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onNewCheck) {
            mListener = (onNewCheck) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface onNewCheck {
        void onCheck(ItemHistoryObject item);
    }

}
