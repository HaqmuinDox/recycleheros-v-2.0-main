package com.example.recycleheroes;


import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkService extends AsyncTask<String, Void, String> {

    private static final String SERVER_IP = "134.100.14.217";  // Replace with your server's IP address 134.100.14.217
    private static final int SERVER_PORT = 22527;

    private OnTaskCompleted listener;

    public NetworkService(OnTaskCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;

        try {

            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send the message to the server
            out.println(params[0]);

            // Receive the response from the server
            result = in.readLine();

            // Close the resources
            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener != null) {
            if(result != null) {
            listener.onTaskCompleted(result);
            }
        }
    }

    // Interface to handle the completion of the network task
    public interface OnTaskCompleted {
        void onTaskCompleted(String result);
    }
}
