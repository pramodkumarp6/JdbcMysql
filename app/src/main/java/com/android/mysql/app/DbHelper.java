package com.android.mysql.app;

import android.util.Log;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;

public class DbHelper {

    private  boolean result = false;
    private Connection conn;
    private String TAG = DbHelper.class.getSimpleName();



           public DbHelper()
           {boolean result = false;
               conn = null;
               try{
                    Class.forName(AppConfig.DRIVER);
                    conn = DriverManager.getConnection(AppConfig.connectionString,AppConfig.db_user,AppConfig.ab_pass);

               }
               catch (SQLException s)
               {
                   Log.e(TAG, s.getMessage());

               } catch (ClassNotFoundException e) {
                       Log.e(TAG,e.getMessage());
               }

           }

           public boolean add(String email,String username,String lastName,String pass,String  mobile,String address)
           {    boolean result = false;
           try {

               PreparedStatement statement =conn.prepareStatement("Insert into users(email,lastName,password,username,mobile,address)"+"values(?,?,?,?,?,?) ");
               statement.setString(1,email);
               statement.setString(2,pass);
               statement.setString(3,username);
               statement.setString(4,lastName);

               statement.setString(5,mobile);
               statement.setString(6,address);
               result= statement.execute();

               statement.close();
           }
               catch(SQLException s){
                   Log.e(TAG ,s.getMessage());
               }
                     return result;
           }
           /*************************************************login code *****************************************/
           public boolean login(String email,String pass){
             

                        try {

                            PreparedStatement statement =conn.prepareStatement("select * from users where email=? and password=?");
                            statement.setString(1,email);
                            statement.setString(2, pass);
                            ResultSet rs=statement.executeQuery();
                            result=rs.next();
                            while(rs.next()) {
                                 String  s=  rs.getString("mobile");
                                  Log.d(s,"mobile");

                            }

                            statement.close();
                        }catch (SQLException q){
                            q.getMessage();
                        }

                      return result;
                      }

/*
                     public ResultSet getAllDetails()
                      {
                          try{
                              
                              Statement st=conn.createStatement();
                              ResultSet rs st.executeQuery(query);

                          }catch(SQLException s){
                              s.getMessage();
                          }


                          return result;
                      }
*/




}
