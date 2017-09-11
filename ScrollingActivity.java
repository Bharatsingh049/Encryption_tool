package com.example.android.bharathash;

import android.app.ProgressDialog;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
//import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
//import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
//import java.lang.Object.org.apache.commons.codec.binary.BaseNCodec;
//import java.io.UTFDataFormatException;
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.MessageDigest;import java.io.FileInputStream;
//import java.io.UnsupportedEncodingException;
//import java.math.BigInteger;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchAlgorithmException;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.IvParameterSpec;import org.apache.commons.httpclient.*;
//import javax.crypto.spec.SecretKeySpec;
//
//import java.io.*;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
////import javax.swing.JOptionPane;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import org.apache.commons.httpclient.*;

public class ScrollingActivity extends AppCompatActivity {
    EditText showText1 = null;
    ProgressDialog progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Developed by Bharat Singh", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private static byte[] sharedvector = {
            0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11};
    public class MD5 {

        public String getMD5(String input) {
            byte[] source;
            try {
                //Get byte according by specified coding.
                source = input.getBytes("UTf-8");
            } catch (UnsupportedEncodingException e) {
                source = input.getBytes();
            }
            String result = null;
            char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                    '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(source);
                //The result should be one 128 integer
                byte temp[] = md.digest();
                char str[] = new char[16 * 2];
                int k = 0;
                for (int i = 0; i < 16; i++) {
                    byte byte0 = temp[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                result = new String(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        public String DecryptText(String EncText)
        {

            String RawText = "";
            byte[] keyArray = new byte[24];
            byte[] temporaryKey;
            String key = "developer notes";
            byte[] toEncryptArray = null;

            try
            {
                MessageDigest m = MessageDigest.getInstance("MD5");
                temporaryKey = m.digest(key.getBytes("UTF-16"));

                if(temporaryKey.length < 24) // DESede require 24 byte length key
                {
                    int index = 0;
                    for(int i=temporaryKey.length;i< 24;i++)
                    {
                        keyArray[i] =  temporaryKey[index];
                    }
                }

                Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
                c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
                byte[] decrypted = c.doFinal((EncText.getBytes("UTF-16")));

                RawText = new String(decrypted, "UTF-16");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            return RawText;

        }



    }

    public void copy(View view)
    {EditText showText5= (EditText)findViewById(R.id.showingText);
     String result12 = showText5.getText().toString();
        EditText edit23=(EditText) findViewById(R.id.editSting2);
        edit23.setText(result12);
    }
    public void hashGenerator(View view)
    {
        EditText edit=(EditText) findViewById(R.id.editSting);
        MD5 md= new MD5() ;
        String result1="";
        result1 = md.getMD5(edit.getText().toString());
        EditText showText= (EditText)findViewById(R.id.showingText);
        showText.setText(result1);


    }
    public void reset(View view)
    {   EditText showText2=(EditText)findViewById(R.id.showingText);
        showText2.setText("");
        EditText showText4=(EditText)findViewById(R.id.editSting);
        showText4.setText("");
        EditText showText5=(EditText)findViewById(R.id.editSting2);
        showText5.setText("");
        EditText showText3=(EditText)findViewById(R.id.showingText2);
        showText3.setText("");
    }

    public void getMessage(View view)
    {
        EditText edit2=(EditText) findViewById(R.id.editSting2);
        String result2=edit2.getText().toString();
        showText1=(EditText)findViewById(R.id.showingText2);
        if(result2.length()== 32)
        new Decrypthash().execute(result2);
        else
        {Toast.makeText(getApplicationContext(), "Invalid Input Try Again Later", Toast.LENGTH_LONG).show();}
    }
    class Decrypthash extends AsyncTask<String, Void, String> {

        private Exception exception;

        @Override
        protected void onPreExecute() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar = new ProgressDialog(ScrollingActivity.this);
                    progressBar.setMessage("Decrypting...");
                    progressBar.setIndeterminate(true);
                    progressBar.show();

                }
            });
            super.onPreExecute();
        }

        protected String doInBackground(String... urls) {
            String result3=urls[0],result="";;
            try {
                String url = "http://md5decrypt.net/Api/api.php?hash="+result3+"&hash_type=md5&email=bs049541@gmail.com&code=da000732306ba375";
                HttpClient client = new DefaultHttpClient();
                HttpResponse res;

                try {
                    res=client.execute(new HttpGet(url));
                    HttpEntity entity = res.getEntity();
                    result = EntityUtils.toString(entity);

                } catch(IOException e) {
                    //do something here
                }

            } catch (Exception e) {
                this.exception = e;
                Toast.makeText(getApplicationContext(), "Server error, try again later.", Toast.LENGTH_LONG).show();
            }
            Log.d("Result>>>>>>>>>",""+result);
            return result;
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(progressBar!=null){
                        progressBar.hide();
                    }
                }
            });
            if(showText1!= null)
            {
                showText1.setText(result);
            }
            super.onPostExecute(result);
        }
    }
}


