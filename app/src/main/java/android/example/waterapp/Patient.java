package android.example.waterapp;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;


public class Patient implements Parcelable {

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

    @SerializedName("heartbeat")
    @Expose
    private Integer heartbeat;

    @SerializedName("spo2")
    @Expose
    private Integer spo2;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("age")
    @Expose
    private Integer age;

    @SerializedName("medication1")
    @Expose
    private Integer medication1;

    @SerializedName("medication2")
    @Expose
    private Integer medication2;

    @SerializedName("medication3")
    @Expose
    private Integer medication3;

    @SerializedName("disease1")
    @Expose
    private Integer disease1;

    @SerializedName("height")
    @Expose
    private Integer height;

    @SerializedName("weight")
    @Expose
    private Integer weight;

    @SerializedName("button")
    @Expose
    private Integer button;

    public Integer getId() {
        return id;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeInt(room);
        parcel.writeString(name);
        parcel.writeString(forename);
        parcel.writeInt(dehydrationState);
        parcel.writeInt(heartbeat);
        parcel.writeInt(spo2);
        parcel.writeString(gender);
        parcel.writeString(birthday);
        parcel.writeInt(age);
        parcel.writeInt(medication1);
        parcel.writeInt(medication2);
        parcel.writeInt(medication3);
        parcel.writeInt(disease1);
        parcel.writeInt(height);
        parcel.writeInt(weight);
        parcel.writeInt(button);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer roomNumber) {
        this.room = room;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getForename() {
        return forename;
    }
    public void setForename(String forename) {
        this.forename = forename;
    }

    public Integer getDehydrationState() {
        return dehydrationState;
    }

    public void setDehydrationState(Integer dehydrationState) {
        this.dehydrationState = dehydrationState;
    }

    public Integer getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Integer heartbeat) {
        this.heartbeat= heartbeat;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {this.birthday = birthday; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Integer getAge(){
            int year = Integer.parseInt(birthday.substring(6));
            int day = Integer.parseInt(birthday.substring(0,2));
            int month = Integer.parseInt(birthday.substring(3,5));
            LocalDate today = LocalDate.now();
            LocalDate birth = LocalDate.of(year, month, day);
            Period p = Period.between(birth, today);
            int age = p.getYears();
            return age;
    }

    public void setAge(Integer age){this.age = age;}


    public Integer getMedication1() {
        return medication1;
    }

    public void setMedication1(Integer medication1) {
        this.medication1 = medication1;
    }

    public Integer getMedication2() {
        return medication2;
    }

    public void setMedication2(Integer medication2) {
        this.medication2 = medication2;
    }

    public Integer getMedication3() {
        return medication3;
    }

    public void setMedication3(Integer medication3) {
        this.medication3 = medication3;
    }

    public Integer getDisease1() {
        return disease1;
    }

    public void setDisease1(Integer disease1) {
        this.disease1 = disease1;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getButton() {
        return button;
    }

    public void setButton(Integer button) {
        this.button = button;
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }


        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    public Patient(Parcel in) {
        id = in.readInt();
        room = in.readInt();
        name = in.readString();
        forename = in.readString();
        dehydrationState = in.readInt();
        heartbeat = in.readInt();
        spo2 = in.readInt();

        gender = in.readString();
        birthday = in.readString();
        age = in.readInt();
        medication1 = in.readInt();
        medication2 = in.readInt();
        medication3 = in.readInt();


        disease1 = in.readInt();

        height = in.readInt();
        weight = in.readInt();

        button =  in.readInt();

    }


}