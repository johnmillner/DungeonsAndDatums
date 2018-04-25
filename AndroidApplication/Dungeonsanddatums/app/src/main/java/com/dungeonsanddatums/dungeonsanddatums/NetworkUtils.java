package com.dungeonsanddatums.dungeonsanddatums;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by matthew on 2/26/18.
 */

public class NetworkUtils
{
    public static String postQueryServer(String stringUrl, String json)
    {
        // URL we are trying to go to
        String urlString = stringUrl;

        // Message (json) we are trying to send
        String message = json;

        // These are used inside the try{} but are outside to be referenced later if needed
        OutputStream os = null;
        InputStream is = null;
        HttpURLConnection conn = null;

        // Stores what is returned from the server
        String result = "Nothing Was returned";

        try
        {
            // Define what URL we will be accessing
            // (For the login, we want to access the Login.php located at https://dungeonsanddatums.com/test/server/login.php)
            URL url = new URL(urlString);

            // Set up connection settings
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // True = we intend to use this URL for output
            conn.setDoInput(true);  // True = we intend to use this URL for input
            conn.setFixedLengthStreamingMode(message.getBytes().length);

            //make some HTTP header nicety
            conn.setRequestProperty("Content-type", "'application/json;charset=utf-8'");
            conn.setRequestProperty("X-Request-with", "XMLHttpRequest");

            //open
            conn.connect();

            //setup send
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());

            //clean up
            os.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                /* Store what we got from the server
                 * in this case, we are only reading the first line, but this can be
                 * put in a loop to be read multiple times for however long the stream is
                 */

            result = in.readLine();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // returns what we got from server
        return result;

    }
}
