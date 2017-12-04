package net.simplifiedlearning.retrofitexample.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Belal on 10/2/2017.
 */

public class Incidencia {

    @SerializedName("name")
    private String name;
    private String desc;


    public Incidencia(String name, String desc) {
        this.name = name;
        this.desc = desc;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }


}
