package id.natlus.smartcell.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "checkout")
public class CheckoutEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @Expose
    @SerializedName("address")
    @ColumnInfo(name = "address")
    private String address;

    @Expose
    @SerializedName("phone_number")
    @ColumnInfo(name = "phone_number")
    private String phone_number;

    @Expose
    @SerializedName("email")
    @ColumnInfo(name = "email")
    private String email;

    @Expose
    @SerializedName("payment")
    @ColumnInfo(name = "payment")
    private String payment;

    @Expose
    @SerializedName("type")
    @ColumnInfo(name = "type")
    private String type;

    public CheckoutEntity(String name, String address, String phone_number, String email, String payment, String type) {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.payment = payment;
        this.type = type;
    }

    protected CheckoutEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        phone_number = in.readString();
        email = in.readString();
        payment = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phone_number);
        dest.writeString(email);
        dest.writeString(payment);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CheckoutEntity> CREATOR = new Creator<CheckoutEntity>() {
        @Override
        public CheckoutEntity createFromParcel(Parcel in) {
            return new CheckoutEntity(in);
        }

        @Override
        public CheckoutEntity[] newArray(int size) {
            return new CheckoutEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
