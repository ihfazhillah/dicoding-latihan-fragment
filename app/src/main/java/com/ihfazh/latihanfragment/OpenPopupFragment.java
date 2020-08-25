package com.ihfazh.latihanfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpenPopupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenPopupFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnChoose, btnClose;
    private RadioGroup rgPilihan;
    private RadioButton rbIhf, rbAbd, rbSad;
    OnOptionDialogListener onOptionDialogListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();
        if (fragment instanceof DetailCategoryFragment){
            DetailCategoryFragment detailCategoryFragment = (DetailCategoryFragment) fragment;
            this.onOptionDialogListener = detailCategoryFragment.optionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.onOptionDialogListener = null;
    }

    public interface OnOptionDialogListener{
        void onOptionChosen(String guru);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rgPilihan = view.findViewById(R.id.rg_options);
        rbIhf = view.findViewById(R.id.rb_ihf);
        rbAbd = view.findViewById(R.id.rb_abd);
        rbSad = view.findViewById(R.id.rb_sad);

        btnChoose = view.findViewById(R.id.btn_pilih);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedRadioButton = rgPilihan.getCheckedRadioButtonId();
                if (checkedRadioButton != -1){
                    String guru = null;
                    switch (checkedRadioButton){
                        case R.id.rb_ihf:
                            guru = rbIhf.getText().toString();
                            break;
                        case R.id.rb_abd:
                            guru = rbAbd.getText().toString();
                            break;
                        case R.id.rb_sad:
                            guru = rbSad.getText().toString();
                            break;
                    }

                    if (onOptionDialogListener != null){
                        onOptionDialogListener.onOptionChosen(guru);
                    }

                    getDialog().dismiss();
                }

            }
        });

        btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });
    }

    public OpenPopupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpenPopupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenPopupFragment newInstance(String param1, String param2) {
        OpenPopupFragment fragment = new OpenPopupFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_popup, container, false);
    }
}