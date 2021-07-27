package com.myur.wallpaperaccess;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    LinearLayout linearLayout;
    wallpaerAdapter wallpaperAdapter;
    List<wallpaperModel> wallpaperModelList;
    int pageNumber = 1;
    EditText seaechet;
    ImageView searchiv;
    TextView More;
    String query = "LandScape";
    androidx.core.widget.NestedScrollView scrollView;
    TextView sugg, kwallpaper, supercar, cat, car, nature, landscape, city, hdbackground, cbedit;

    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    String url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        wallpaperModelList = new ArrayList<>();
        wallpaperAdapter = new wallpaerAdapter(this, wallpaperModelList);

        scrollView = findViewById(R.id.scrollView);
        linearLayout = findViewById(R.id.LinearView);
        seaechet = findViewById(R.id.searchEv);
        searchiv = findViewById(R.id.search_image);
        More = findViewById(R.id.more);
        cat = findViewById(R.id.cat);
        car = findViewById(R.id.car);
        nature = findViewById(R.id.nature);
        kwallpaper = findViewById(R.id.kwallpaper);
        hdbackground = findViewById(R.id.hdbackground);
        supercar = findViewById(R.id.supercar);
        cbedit = findViewById(R.id.cbedit);
        landscape = findViewById(R.id.landscape);
        city = findViewById(R.id.city);
        sugg = findViewById(R.id.suggTitle);

        sugg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + "LandScape";
                wallpaperModelList.clear();
                fetchWallpaper();

            }
        });

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
        kwallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seaechet.setText("4kWallpaper");

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();

            }
        });
        supercar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seaechet.setText("SuperCar");

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();

            }
        });
        hdbackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seaechet.setText("HDBackground");

                String query = seaechet.getText().toString().toLowerCase();

                url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + query;
                wallpaperModelList.clear();
                fetchWallpaper();

            }
        });
        cbedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seaechet.setText("DesktopBackgrounds");

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

                androidx.core.widget.NestedScrollView sv = scrollView;
                View highlightedItem = linearLayout;// find the LinearLayout or View that you need to scroll to which is inside this ScrollView
                int height = (int) highlightedItem.getY();
                sv.scrollTo(0, height);

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
            seaechet.setText("Trending");
            url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + "Trending";
            wallpaperModelList.clear();
            fetchWallpaper();
        }
        if (item.getItemId() == R.id.action_mostview) {
            seaechet.setText("Most View");
            url = "https://api.pexels.com/v1/search/?page=" + pageNumber + "&per_page=80&query=" + "Most View";
            wallpaperModelList.clear();
            fetchWallpaper();
        }
       /* if (item.getItemId() == R.id.action_LogOut) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);



       */     /*AlertDialog.Builder alert = new AlertDialog.Builder(this);
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
        if (item.getItemId() == R.id.action_AboutUs) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Wallpaper Access");
            alert.setCancelable(false);
            alert.setMessage("Wallpaper Access provides high quality and completely free stock photos licensed under the Pexels license. All photos are nicely tagged, searchable and also easy to discover through our discover pages.\n" +
                    "\n" +
                    "Photos\n" +
                    "We have hundreds of thousands free stock photos and every day new high resolution photos will be added. All photos are hand-picked from photos uploaded by our users or sourced from free image websites. We make sure all published pictures are high-quality and licensed under the Pexels license.\n" +
                    "\n" +
                    "Photo Sources\n" +
                    "Only free images from our community of photographers are added to our photo database. We constantly try to deliver as many high quality free stock photos as possible to the creatives who use our website.\nMission\n" +
                    "We help millions of designers, writers, artists, programmers and other creators to get access to beautiful photos that they can use freely which empowers them to create amazing products, designs, stories, websites, apps, art and other work. We call it: \"Empowering Creators\"\n" +
                    "\n" +
                    "Contribute\n" +
                    "Upload your own pictures to support the Pexels community:" +
                    "\n\nDeveloper\nMayur Hajare\nEmail:- Mayurshajare2002@gmail.com\n");
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }
}