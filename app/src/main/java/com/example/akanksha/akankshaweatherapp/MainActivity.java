package com.example.akanksha.akankshaweatherapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static int serror = 0;
    public static int cerror = 0;
    public static int sterror = 0;

//    final EditText street= (EditText) findViewById(R.id.editText);
//    final EditText city= (EditText) findViewById(R.id.editText2);
//    final Spinner state= (Spinner) findViewById(R.id.spinner);
//    final Button search= (Button) findViewById(R.id.button);
//    final Button clear= (Button) findViewById(R.id.button2);
//    final TextView error= (TextView) findViewById(R.id.textView7);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final RadioButton f=(RadioButton)findViewById(R.id.radioButton);
       final RadioButton c=(RadioButton)findViewById(R.id.radioButton2);
        final TextView error= (TextView) findViewById(R.id.textView7);
        error.setText("");


        ImageView img = (ImageView)findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://forecast.io"));
                startActivity(intent);
            }
        });



        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c.isChecked())
                {
                    c.setChecked(false);
                }


            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f.isChecked())
                {
                    f.setChecked(false);
                }


            }
        });


        buttonvalidate();
        errorvalidate();
        Clear();
    }

    public void aboutStudent(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }





    private void buttonvalidate() {

       final EditText street= (EditText) findViewById(R.id.editText);
        final EditText city= (EditText) findViewById(R.id.editText2);
        final Spinner state= (Spinner) findViewById(R.id.spinner);
        final Button search= (Button) findViewById(R.id.button);
        final Button clear= (Button) findViewById(R.id.button2);
        final TextView error= (TextView) findViewById(R.id.textView7);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (street.getText().toString().matches("")) {
                    error.setText("Please enter address");
                } else if (city.getText().toString().matches("")) {
                    error.setText("Please enter city");
                } else if (state.getSelectedItem().toString().equals("Select")) {
                    error.setText("Please select a state");
                } else senddata();
            }
        });


    }

    //=====================

    public void errorvalidate()
    {
        final EditText street= (EditText) findViewById(R.id.editText);
        final EditText city= (EditText) findViewById(R.id.editText2);
        final Spinner state= (Spinner) findViewById(R.id.spinner);
        street.addTextChangedListener(new TextWatcher() {


                                          public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              EditText street = (EditText) findViewById(R.id.editText);
                                              TextView eLine = (TextView) findViewById(R.id.textView7);
                                              if (street.getText().toString().equals("")) {
                                                  eLine.setText("Please enter a Street Address");
                                                  serror = 1;
                                              } else {

                                                  eLine.setText("");
                                                  serror = 0;

                                              }
                                          }

                                          @Override
                                          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                          }

                                          @Override
                                          public void afterTextChanged(Editable s) {
                                          }


                                      }

        );

        city.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText city = (EditText) findViewById(R.id.editText2);
                TextView eLine = (TextView) findViewById(R.id.textView7);

                if(city.getText().toString().equals(""))
                {
                    eLine.setText("Please enter a City");
                    cerror=1;
                }
                else
                {

                    eLine.setText("");
                    cerror=0;

                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void afterTextChanged(Editable s){}
        });




    }






    //=========================================

    public void senddata() {

        final EditText street= (EditText) findViewById(R.id.editText);
        final EditText city= (EditText) findViewById(R.id.editText2);
        final Spinner state= (Spinner) findViewById(R.id.spinner);
        final Button search= (Button) findViewById(R.id.button);
        final Button clear= (Button) findViewById(R.id.button2);
        final TextView error= (TextView) findViewById(R.id.textView7);
//        final RadioButton fah= (RadioButton) findViewById(R.id.radioButton);
//        final RadioButton cel= (RadioButton) findViewById(R.id.radioButton2);

        String streetvalue=street.getText().toString();
        String cityvalue=city.getText().toString();
        String statevalue=state.getSelectedItem().toString();
//        RadioButton selectedButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
//        setDeg(selectedButton.getText().toString());

        RadioButton f=(RadioButton)findViewById(R.id.radioButton);
        RadioButton c=(RadioButton)findViewById(R.id.radioButton2);

        String degreevalue="us";

        if(f.isChecked()){
            degreevalue="us";
        }
        else if(c.isChecked())
        {
            degreevalue="si";
        }

        //String degree= "us";
        String value[]= new String[3];
        value[0]=cityvalue;
        value[1]=statevalue;
        value[2]=degreevalue;

        String url="http://akankshaapp27-env.elasticbeanstalk.com/?addr="+streetvalue+"&city="+cityvalue+"&state="+statevalue+"&deg="+degreevalue;
//Toast.makeText(MainActivity.this,url,Toast.LENGTH_SHORT).show();
//        Toast.makeText(MainActivity.this,f.toString(),Toast.LENGTH_SHORT).show();
new Async(this,value).execute(url);






    }



    public void Clear() {

        Button clear =(Button) findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText street = (EditText) findViewById(R.id.editText);
                EditText city = (EditText) findViewById(R.id.editText2);
                Spinner state = (Spinner) findViewById(R.id.spinner);
                RadioButton fah = (RadioButton) findViewById(R.id.radioButton);
                RadioButton cel = (RadioButton) findViewById(R.id.radioButton2);
                TextView eLine = (TextView) findViewById(R.id.textView7);

                street.setText("");
                city.setText("");
                state.setSelection(0);
                fah.setChecked(true);
                cel.setChecked(false);
                eLine.setText("");
            }
        });
    }






    public class Async extends AsyncTask<String,Void,String> {
        Context context;
        String City;
        String State;
        String Degree;
        public Async(Context context,String[] params) {
            this.context = context;
            City = params[0];
            State = params[1];
            Degree=params[2];
            Log.d("check1", City);
        }
        //}
        @Override
        protected String doInBackground(String... servervalue) {
            try {
                return Fetchdata(servervalue[0]);
            } catch (IOException e) {
                return null;
            }
        }

        private String Fetchdata(String myurl) throws IOException {
            InputStream is = null;

            try {

                URL url = new URL(myurl.replace(" ", "%20"));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                Integer response = conn.getResponseCode();
                //          Log.d("check", myurl);
                try {
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    String contentAsString = readStream(in);
                    // Convert the InputStream into a string

//                Log.d("check", contentAsString);
                    return contentAsString;
                } finally {
                    conn.disconnect();
                }

                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        private String readStream(InputStream is) {
            try {
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                int i = is.read();
                while (i != -1) {
                    bo.write(i);
                    i = is.read();
                }
                return bo.toString();
            } catch (IOException e) {
                return "";
            }
        }

        @Override
//    protected void onPostExecute(String s) {
//        super.onPostExecute(s);
//        ParseJson(s);
//    }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Intent intent=new Intent(context, Result.class);
            intent.putExtra("city",City);
            intent.putExtra("state",State);
            intent.putExtra("degree",Degree);

            intent.putExtra("jsonvalue", result);
            context.startActivity(intent);


        }




    }

}
