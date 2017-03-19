

/**
 * Created by Damindu on 13/01/2016.
 */
public class StarterClass extends Application {
    private static StarterClass mInstance;


    String SelectTitle;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


    }

    public static synchronized StarterClass getInstance() {
        return mInstance;
    }

    public void SetSelectTitle(String SelectTitle){this.SelectTitle = SelectTitle;}
    public String GetSelectTitle(){return this.SelectTitle;}


    //how to call
    // StarterClass.getInstance().ShowDialog(CheckinActivity.this,message);

    /*
    * <application
        android:name=".StarterClass"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher" */