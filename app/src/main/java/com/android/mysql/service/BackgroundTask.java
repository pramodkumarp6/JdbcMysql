package com.android.mysql.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.mysql.Login;
import com.android.mysql.app.DbHelper;


public class BackgroundTask extends AsyncTask<String,Void,String> {
       public Context ctx;
       private ProgressDialog progressDialog;

        public BackgroundTask(Context ctx){
              this.ctx=ctx;

          }


    @Override
    protected void onPreExecute(){
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMessage("Register....");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... parms) {
        boolean result = false;
        String z=null;
        DbHelper db =  new DbHelper();

        String email =parms[1];
        String pass =parms[2];
        String username =parms[3];
        String lastName =parms[4];
        String mobile =parms[5];
        String address =parms[6];

        db.add(email,pass,username,lastName,mobile,address);

        if(!result)
        {
            result=true;
            z =" Registration Sucessfully ";
        }
        else {
            result=false;
            z="Registration is failed";
        }
        return z;
    }



    @Override
    protected void onProgressUpdate(Void...values)
    {
        super.onProgressUpdate(values);
    }





    @Override
    protected void onPostExecute(String result)
    {progressDialog.hide();


        Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();

        Intent  intent = new Intent(ctx, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ctx.startActivity(intent);
        super.onPostExecute(result);
    }

}
