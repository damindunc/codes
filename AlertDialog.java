AlertDialog b; 
public void ShowPasswordDialog() {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.password_dialog, null);
            dialogBuilder.setView(dialogView);

            final EditText txtForgetPhone = (EditText) dialogView.findViewById(R.id.txtForgetPhone);
            final Button btnConfirm = (Button) dialogView.findViewById(R.id.btnConfirm);
            final Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);

            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    forgot = true;
                    registerReceiver(smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
                    DoForgetPassword(txtForgetPhone.getText().toString());
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    b.dismiss();
                }
            });

            //dialogBuilder.setTitle("Forgot Password?");
            //dialogBuilder.setMessage("");
//            dialogBuilder.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//                    forgot = true;
//                    registerReceiver(smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
//                    DoForgetPassword(txtForgetPhone.getText().toString());
//                }
//            });
//            dialogBuilder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//                    //pass
//                }
//            });
            b = dialogBuilder.create();
            b.show();
        }
