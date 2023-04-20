package com.androicourse.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import com.androicourse.assignment2.adapters.ProductAdapter;
import com.androicourse.assignment2.db.ProductManager;
import com.androicourse.assignment2.fragments.CartFragment;
import com.androicourse.assignment2.models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static String JSON_URL =
            "https://hanu-congnv.github.io/mpr-cart-api/products.json";
    List<Product> productList;
    RecyclerView recyclerView;
    private RecycleViewItemClick myClickListener;
    private ProductManager pm;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pm = ProductManager.getInstance(this);

        recyclerView = findViewById(R.id.recyclerView);

        productList = new ArrayList<>();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

//        GetData getData = new GetData();
//        getData.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.view_cart){
            List<Product> carts = pm.;

            FragmentManager manager = getSupportFragmentManager();
            Fragment fragment = new CartFragment(carts, pManager);

            manager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment, "VIEW_CART" )
                    .addToBackStack("back")
                    .commit();

            return true;
        }

        return false;
    }

    public class GetData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String current ="";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(JSON_URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();
                Scanner sc = new Scanner(is);
                StringBuilder result = new StringBuilder();
                String line;
                while(sc.hasNextLine()) {
                    line = sc.nextLine();
                    result.append(line);
                    current = result.toString();
                }
                return current;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray jsonArray = new JSONArray(s);

                for (int i=0; i< jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    Product model = new Product();
                    model.setThumbnail(jsonObject1.getString("thumbnail"));
                    model.setName(jsonObject1.getString("name"));
//                    model.setCategory(jsonObject1.getString("category"));
                    model.setUnitPrice(jsonObject1.getInt("unitPrice"));
                    productList.add(model);
                }
                putDataIntoRecycleView(productList);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void putDataIntoRecycleView(List<Product> productList) {
        ProductAdapter productAdapter = new ProductAdapter(productList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(productAdapter);
    }


}