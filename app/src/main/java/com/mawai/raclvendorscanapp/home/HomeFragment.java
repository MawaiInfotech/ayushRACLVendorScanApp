package com.mawai.raclvendorscanapp.home;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mawai.raclvendorscanapp.R;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    CardView fg_receive_camera,scan_barcode,invoice_map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fg_receive_camera = view.findViewById(R.id.fg_receive_camera);
        scan_barcode = view.findViewById(R.id.scan_barcode);
        invoice_map = view.findViewById(R.id.invoice_map);

        scan_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_vendorScanFragment);
            }
        });

        onBackPress();

        return view;
    }

    private void onBackPress() {
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                showAlertDialog();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }

    private void showAlertDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
        alertDialog.setTitle("Sign out Confirmation");
        alertDialog.setMessage("Are you sure you want to sign out ?");
        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
//        alertDialog.setIcon(R.drawable.eloficone);

        alertDialog.setPositiveButton("YES", (dialogInterface, i) -> {
//            Intent intent = new Intent(getContext(), LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_loginFragment);

        });

        alertDialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());

        alertDialog.show();
    }
}