package android.example.waterapp;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @SerializedName("gender")
    @Expose
    private String gender;

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
        parcel.writeString(gender);
        parcel.writeInt(age);
        parcel.writeInt(medication1);
        parcel.writeInt(medication2);
        parcel.writeInt(medication3);
        parcel.writeInt(disease1);
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

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
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }

        if (in.readByte() == 0) {
            room = null;
        } else {
            room = in.readInt();
        }
        name = in.readString();
        forename = in.readString();
        if (in.readByte() == 0) {
            dehydrationState = null;
        } else {
            dehydrationState = in.readInt();
        }
        gender = in.readString();
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
        if (in.readByte() == 0) {
            medication1 = null;
        } else {
            medication1 = in.readInt();
        }
        if (in.readByte() == 0) {
            medication2 = null;
        } else {
            medication2 = in.readInt();
        }
        if (in.readByte() == 0) {
            medication3 = null;
        } else {
            medication3 = in.readInt();
        }
        if (in.readByte() == 0) {
            disease1 = null;
        } else {
            disease1 = in.readInt();
        }
    }


}