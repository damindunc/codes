/*app.gradle
*    compile 'com.android.volley:volley:1.0.0'
*/


 public void getDataSet(){

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.GET,"http://localhost:80/api/function", new Response.Listener<String>() { //set method Method.GET| Method.POST| Method.PUT
            @Override
            public void onResponse(String response) {
                Log.d("Got the response",response);
                try {

                    JSONObject jobj = new JSONObject(response);
                    if(jobj.has("status") && "1".equals(jobj.getString("status"))){


                        JSONArray jsonArr = jobj.getJSONArray("message");


                        for(int i=0;i<jsonArr.length();i++)
                        {

                            
                        }


                    }else{

                       Snackbar snackbar = Snackbar.make(coordinatorLayout, "Verification Failed..", Snackbar.LENGTH_LONG);
                       snackbar.show();s
                    }

                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
        
        		volleyError.printStackTrace();
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
            protected Map<String,String> getParams(){ //adding additional parameters

                Map<String,String> params = new HashMap<>();
                params.put("key","value");
                params.put("key","value");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError { //updating request header
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/x-www-form-urlencoded"); 
                return params;
            }
        };
        queue.add(sr);
    }



    