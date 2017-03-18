public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerMessageList;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ProgressDialog ringProgressDialog;
    ArrayList<ItemObject> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ringProgressDialog = new ProgressDialog(MainActivity.this);
        ringProgressDialog.setTitle("Processing Request..");
        ringProgressDialog.setMessage("Please wait..");
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setIndeterminate(true);


        recyclerMessageList = (RecyclerView) findViewById(R.id.Itemlist); //add RecyclerView to the activity
        results = new ArrayList<ItemObject>();
        recyclerMessageList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerMessageList.setLayoutManager(mLayoutManager);
        mAdapter = new ItemAdapter(results);
        recyclerMessageList.setAdapter(mAdapter);
        getDataSet();





    }

     public void getDataSet(){

        ringProgressDialog.show();


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.GET,"http://localhost/api/get", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Got the response",response);
                ringProgressDialog.dismiss();
                try {

                    JSONObject jobj = new JSONObject(response);
                    if(jobj.has("status") && "1".equals(jobj.getString("status"))){


                        JSONArray jsonArr = jobj.getJSONArray("message");


                        for(int i=0;i<jsonArr.length();i++)
                        {


                            ScheduleObject dobj = new ScheduleObject();
                            JSONObject obj= (JSONObject) jsonArr.get(i);
                            dobj.setSchItemId(obj.getString("item_id"));
                            dobj.setSchItemname(obj.getString("item_name"));
                            results.add(dobj);
                        }


                    }else{

                       Snackbar snackbar = Snackbar.make(coordinatorLayout, "get data Failed..", Snackbar.LENGTH_LONG);
                       snackbar.show();
                    }

                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    ringProgressDialog.dismiss();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ringProgressDialog.dismiss();
                error.printStackTrace();

                String message = null;
                if (volleyError instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                } else if (volleyError instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                } else if (volleyError instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                } else if (volleyError instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }  else if (volleyError instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                } else if (volleyError instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }
        }){
            @Override
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

}