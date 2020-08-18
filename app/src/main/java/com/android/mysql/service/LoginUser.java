package com.android.mysql.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

import com.android.mysql.app.DbHelper;
import com.android.mysql.dash.ProfileActivity;

public class LoginUser extends AsyncTask<String,Void,String> {
    private ProgressDialog progressDialog;
    private Context ctx;

    public LoginUser(Context ctx) {
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute(){
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Login....");
        showDialog();
    }

    @Override
    protected String doInBackground(String... parms) {
          boolean result = false;
          String z=null;
        String email =parms[1];
        String pass =parms[2];
        Log.d(email,"email");




        DbHelper db = new DbHelper();
        result=db.login(email,pass);
         if(!result)
         {
             result=false;
             z ="Email & Password Wrong";
         }
         else {
             result=true;
             Intent intent = new Intent(ctx, ProfileActivity.class);
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
             ctx.startActivity(intent);
         }
        return z;
    }
    @Override
    protected void onPostExecute(String result){

        hideDialog();

       Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
    }

    private  void showDialog(){
        if(!progressDialog.isShowing()){
            progressDialog.show();

        }
    }

    private  void hideDialog(){
        if(!progressDialog.isShowing()){
            progressDialog.dismiss();

        }
    }

}
