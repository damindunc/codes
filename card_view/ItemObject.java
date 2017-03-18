package com.cemobile.likuiddriver.support;


/**
 * Created by daminduweerasooriya on 11/3/16.
 */

public class ItemObject {

    private String SchItemname,SchItemFrom;

    ScheduleObject(String SchItemname, String SchItemFrom ){
        this.SchItemname = SchItemname;
        this.SchItemFrom = SchItemFrom;
        
    }

    public ItemObject(){


    }

    public String getSchItemname() {
        return SchItemname;
    }

    public void setSchItemname(String SchItemname) {
        this.SchItemname = SchItemname;
    }

    public String getSchItemFrom() {
        return SchItemFrom;
    }

    public void setSchItemFrom(String SchItemFrom) {
        this.SchItemFrom = SchItemFrom;
    }

    


}
