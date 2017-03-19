package com.cr.support;

/* Intent i = new Intent(this, BackgroundService.class);
            startService(i);*/

/**
 * Created by daminduweerasooriya on 2/24/17.
 */

public class BackgroundService extends Service{
    // Must create a default constructor

    private PowerManager pm;
    LocationManager locationManager;
    LocationListner loclistner = new LocationListner();
    RequestQueue queue;
//    private boolean isScreenOn;
//    private String oldPassiveProvider;
//    private String newPassiveProvider;
//    private Calendar calendar;
//    private Date date;
//    private String screenStatusStr;
//
//
//    //log stuff
//    File externalStorageDir;
//    File internalStorageDir;
//    File myFile;
//
//    FileOutputStream fostream;
//    OutputStreamWriter oswriter;
//    BufferedWriter bwriter;


    @Override
    public void onCreate() {

        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.
        Log.d("Location", "Location Started");
        queue = Volley.newRequestQueue(getApplicationContext());
        //initialize a power manager to check screen status later
        GetLocationGPS();




        //go ahead and open the file
        //openfile();

    }


    public void GetLocationGPS() {

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean statusOfGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (statusOfGPS) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_LOW);
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            Looper loop = null;

            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            locationManager.requestLocationUpdates(10000,0f,criteria,loclistner,loop);
        } else {
            //Toast.makeText(getApplicationContext(),"Enable Location Service!!!",Toast.LENGTH_LONG).show();
        }
    }


    class LocationListner implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {

           // Toast.makeText(getApplicationContext(),String.valueOf(location.getLatitude())+" && "+String.valueOf(location.getLongitude()),Toast.LENGTH_SHORT).show();
            Log.d("Location Updated",String.valueOf(location.getLatitude())+" && "+String.valueOf(location.getLongitude()));
            UpdateLocation(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    @Override
    public void onDestroy()
    {

        //closefile();
    }



//    public void openfile() {
//
//        externalStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        myFile = new File(externalStorageDir, "/log.txt");
//
//
//        if (myFile.exists()) {
//            try {
//                fostream = new FileOutputStream(myFile, true);
//                oswriter = new OutputStreamWriter(fostream);
//                bwriter = new BufferedWriter(oswriter);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                myFile.createNewFile();
//                fostream = new FileOutputStream(myFile, true);
//                oswriter = new OutputStreamWriter(fostream);
//                bwriter = new BufferedWriter(oswriter);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

//    public void closefile() {
//
//        try {
//            bwriter.close();
//            oswriter.close();
//            fostream.close();
//        } catch (IOException e) {
//        }
//
//    }

//    public void writefile(boolean screenStatus, Date timestamp, String provider_name) {
//
//        try {
//            if (screenStatus == true) {
//                screenStatusStr = "ON";
//            } else {
//                screenStatusStr = "OFF";
//            }
//            bwriter.append("S: " + screenStatusStr + ". T: " + timestamp + ". Src: " + provider_name);
//            bwriter.newLine();
//            bwriter.flush();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }




//    @Override
//    public void onLocationChanged(Location location) {
//
//        Log.d("Location Updated",String.valueOf(location.getLatitude())+" && "+String.valueOf(location.getLongitude()));
//        UpdateLocation(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()));
////        boolean isScreen;
////        String provider = location.getProvider();
////        isScreen = pm.isInteractive();
////        calendar = Calendar.getInstance();
////        date = calendar.getTime();
////
////        //When the location changes,
////        //log Screen Status, time stamp, and the location provider (GPS, Network, and etc)
////        writefile(isScreen, date, provider);
//
//    }


    public void UpdateLocation(final String lat, final String lng){

        SharedPreferences prefs = getSharedPreferences("appsettings", MODE_PRIVATE);
        String key = prefs.getString("api_key", "0");
Log.d("UpdateLocation_APIKEY",key);

        StringRequest sr = new StringRequest(Request.Method.PUT,"http://localhost/log?key="+key, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Got the response",response);
                try {

                    JSONObject jobj = new JSONObject(response);
                    if(jobj.has("status") && "1".equals(jobj.getString("status"))){




                    }else{

                        Log.d("Location Update","Update failed");
                    }


                } catch (JSONException e) {
//                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "Verification Failed..", Snackbar.LENGTH_LONG);
//                    snackbar.show();
                    //Log.d("Got the JSONException",e.getMessage());
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

                } else if (volleyError instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";

                } else if (volleyError instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";

                } else if (volleyError instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";

                }  else if (volleyError instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";

                } else if (volleyError instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";

                }
                Log.d("Volley Update",message);
            }
        }){
            @Override
            protected Map<String,String> getParams(){

                Map<String,String> params = new HashMap<>();
                params.put("lat",lat);
                params.put("lng",lng);

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

//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


