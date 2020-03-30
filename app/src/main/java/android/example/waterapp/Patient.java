package android.example.waterapp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Patient {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("room")
    @Expose
    private Integer room;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("forename")
    @Expose
    private String forename;

    @SerializedName("dehydrationState")
    @Expose
    private Integer dehydrationState;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("age")
    @Expose
    private Integer age;

    @SerializedName("medication1")
    @Expose
    private Boolean medication1;

    @SerializedName("medication2")
    @Expose
    private Boolean medication2;

    @SerializedName("medication3")
    @Expose
    private Boolean medication3;

    @SerializedName("disease1")
    @Expose
    private Boolean disease1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer roomNumber) {
        this.room = room;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Integer getDehydrationState() {
        return dehydrationState;
    }

    public void setDehydrationState(Integer dehydrationState) {
        this.dehydrationState = dehydrationState;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMedication1() {
        return medication1;
    }

    public void setMedication1(Boolean medication1) {
        this.medication1 = medication1;
    }

    public Boolean getMedication2() {
        return medication2;
    }

    public void setMedication2(Boolean medication2) {
        this.medication2 = medication2;
    }

    public Boolean getMedication3() {
        return medication3;
    }

    public void setMedication3(Boolean medication3) {
        this.medication3 = medication3;
    }

    public Boolean getDisease1() {
        return disease1;
    }

    public void setDesease1(Boolean desease1) {
        this.disease1 = desease1;
    }

}