package com.myur.wallpaperaccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.myur.wallpaperaccess.adapter.wallpaerAdapter;
import com.myur.wallpaperaccess.modles.wallpaperModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    wallpaerAdapter wallpaperAdapter;
    List<wallpaperModel> wallpaperModelList;
    int pageNumber = 1;
    EditText seaechet;
    ImageView searchiv;
    TextView More;
    String query = "nature";
    ScrollView scrollView;
    TextView cat, car, nature, landscape, city;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    String url = "https://api.pexels.com/v1/curated/?page=" + pageNumber + "&per_page=80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        wallpaperModelList = new ArrayList<>();
        wallpaperAdapter = new wallpaerAdapter(this, wallpaperModelList);

        seaechet = findViewById(R.id.searchEv);
        searchiv = findViewById(R.id.search_image);
        More = findViewById(R.id.more);
        cat = findViewById(R.id.cat);
        car = findViewById(R.id.car);
        nature = findViewById(R.id.nature);
        landscape = findViewById(R.id.landscape);
        city = findViewById(R.id.city);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seaechet.setText("Cat");

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();

            }
        });
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seaechet.setText("Car");

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();

            }
        });
        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seaechet.setText("Nature");

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();

            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seaechet.setText("City");

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();

            }
        });
        landscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seaechet.setText("Landscape");

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();

            }
        });

        recyclerView.setAdapter(wallpaperAdapter);

        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = staggeredGridLayoutManager.getChildCount();
                totalItems = staggeredGridLayoutManager.getItemCount();
                scrollOutItems = staggeredGridLayoutManager.findFirstVisibleItemPositions(null)[0];

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    fetchWallpaper();
                }


            }
        });
        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();

                //scrollView.fullScroll(ScrollView.FOCUS_UP);
                // scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
        searchiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();
            }
        });


        fetchWallpaper();

    }


    public void fetchWallpaper() {

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("photos");

                            int length = jsonArray.length();

                            for (int i = 0; i < length; i++) {

                                JSONObject object = jsonArray.getJSONObject(i);

                                int id = object.getInt("id");

                                JSONObject objectImages = object.getJSONObject("src");

                                String orignalUrl = objectImages.getString("original");
                                String mediumUrl = objectImages.getString("medium");

                                wallpaperModel wallpaperModel = new wallpaperModel(id, orignalUrl, mediumUrl);
                                wallpaperModelList.add(wallpaperModel);


                            }

                            wallpaperAdapter.notifyDataSetChanged();
                            pageNumber++;

                        } catch (JSONException e) {

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "563492ad6f9170000100000110445f433efc4226b6b636932b4d9115");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navi, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.action_treading) {
            url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + "Treading";
            wallpaperModelList.clear();
            fetchWallpaper();
        }
        if (item.getItemId() == R.id.action_mostview) {
            url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + "cat ";
            wallpaperModelList.clear();
            fetchWallpaper();
        }
        if (item.getItemId() == R.id.action_LogOut) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);



            /*AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final EditText editText = new EditText(this);

            editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            alert.setMessage("Enter Category e.g. Nature");
            alert.setTitle("Search Wallpaper");

            alert.setView(editText);

            alert.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                   *//* String query = editText.getText().toString().toLowerCase();

                    url = "https://api.pexels.com/v1/search/?page="+pageNumber+"&per_page=80&query="+query;
                    wallpaperModelList.clear();
                    fetchWallpaper();*//*


                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            alert.show();


*/
        }

        return super.onOptionsItemSelected(item);
    }
}