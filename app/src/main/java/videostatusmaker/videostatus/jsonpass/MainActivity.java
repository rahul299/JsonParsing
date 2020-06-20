package videostatusmaker.videostatus.jsonpass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private static String url = "https://api.npoint.io/45337e79538b1335c315";
    ArrayList<Modul> contactList;
    private MoviesAdapter mAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        mAdapter = new MoviesAdapter(contactList);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray contacts = new JSONArray(jsonStr);

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        Log.i("hhh","id------"+id);
                        String name = c.getString("name");
                        Log.i("hhh","name------"+name);
                        String username = c.getString("username");
                        Log.i("hhh","username------"+username);
                        String email = c.getString("email");
                        Log.i("hhh","email------"+email);

                     // Phone node is JSON Object
                        JSONObject address = c.getJSONObject("address");
                        String street = address.getString("street");
                        Log.i("hhh","street------"+street);
                        String suite = address.getString("suite");
                        Log.i("hhh","suite------"+suite);
                        String city = address.getString("city");
                        Log.i("hhh","city------"+city);
                        String zipcode = address.getString("zipcode");
                        Log.i("hhh","zipcode------"+zipcode);


                        JSONObject geo = address.getJSONObject("geo");
                        String lat = geo.getString("lat");
                        Log.i("hhh","lat------"+lat);

                        String lng = geo.getString("lng");
                        Log.i("hhh","lng------"+lng);



                        Modul contact = new Modul(id,name,username,email,street,suite,city,zipcode,lat,lng);

                        contactList.add(contact);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            mAdapter = new MoviesAdapter(contactList);
            recyclerView.setAdapter(mAdapter);


        }
    }
}
