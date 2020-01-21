package virtual.software.registration;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Dialog {
    Context context;

    public Dialog(Context ctx) {
        context = ctx;
    }

    public void showDialog() {
        final android.app.Dialog dialog = new android.app.Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.diloag_congratulation_layout);

//        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();

    }


    public boolean showConfirmDialog() {
        final boolean[] isYes = new boolean[1];
        final android.app.Dialog dialog = new android.app.Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.diloag_confirm_exit_layout);
        Button yesDialogButton = (Button) dialog.findViewById(R.id.btn_confirm_yes_dialog);
        yesDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                isYes[0] = true;
            }
        });

        dialog.show();

        Button noDialogButton = (Button) dialog.findViewById(R.id.btn_confirm_no_dialog);
        noDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                isYes[0] = false;
            }
        });

        dialog.show();

        return isYes[0];
    }


    public void showErrorDialog() {
        final android.app.Dialog dialog = new android.app.Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.diloag_error_layout);

//        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_error_dlg);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();

    }


}
