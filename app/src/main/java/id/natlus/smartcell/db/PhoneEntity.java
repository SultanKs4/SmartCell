package id.natlus.smartcell.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "phone")
public class PhoneEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("type")
    @ColumnInfo(name = "type")
    private String type;

    @Expose
    @SerializedName("price")
    @ColumnInfo(name = "price")
    private String price;

    @Expose
    @SerializedName("image")
    @ColumnInfo(name = "image")
    private String image;

    @Expose
    @SerializedName("detail")
    @ColumnInfo(name = "detail")
    private String detail;

    @Expose
    @SerializedName("brand")
    @ColumnInfo(name = "brand")
    private String brand;

    @Expose
    @SerializedName("display_size")
    @ColumnInfo(name = "display_size")
    private String display_size;

    @Expose
    @SerializedName("display_ratio")
    @ColumnInfo(name = "display_ratio")
    private String display_ratio;

    @Expose
    @SerializedName("display_resolution")
    @ColumnInfo(name = "display_resolution")
    private String display_resolution;

    @Expose
    @SerializedName("hardware_proc")
    @ColumnInfo(name = "hardware_proc")
    private String hardware_proc;

    @Expose
    @SerializedName("hardware_camera")
    @ColumnInfo(name = "hardware_camera")
    private String hardware_camera;

    @Expose
    @SerializedName("hardware_battery")
    @ColumnInfo(name = "hardware_battery")
    private String hardware_battery;

    public PhoneEntity(String type, String price, String image, String detail, String brand,
                       String display_size, String display_ratio, String display_resolution,
                       String hardware_proc, String hardware_camera, String hardware_battery) {
        this.type = type;
        this.price = price;
        this.image = image;
        this.detail = detail;
        this.brand = brand;
        this.display_size = display_size;
        this.display_ratio = display_ratio;
        this.display_resolution = display_resolution;
        this.hardware_proc = hardware_proc;
        this.hardware_camera = hardware_camera;
        this.hardware_battery = hardware_battery;
    }

    protected PhoneEntity(Parcel in) {
        id = in.readInt();
        type = in.readString();
        price = in.readString();
        image = in.readString();
        detail = in.readString();
        brand = in.readString();
        display_size = in.readString();
        display_ratio = in.readString();
        display_resolution = in.readString();
        hardware_proc = in.readString();
        hardware_camera = in.readString();
        hardware_battery = in.readString();
    }

    public static final Creator<PhoneEntity> CREATOR = new Creator<PhoneEntity>() {
        @Override
        public PhoneEntity createFromParcel(Parcel in) {
            return new PhoneEntity(in);
        }

        @Override
        public PhoneEntity[] newArray(int size) {
            return new PhoneEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDisplay_size() {
        return display_size;
    }

    public void setDisplay_size(String display_size) {
        this.display_size = display_size;
    }

    public String getDisplay_ratio() {
        return display_ratio;
    }

    public void setDisplay_ratio(String display_ratio) {
        this.display_ratio = display_ratio;
    }

    public String getDisplay_resolution() {
        return display_resolution;
    }

    public void setDisplay_resolution(String display_resolution) {
        this.display_resolution = display_resolution;
    }

    public String getHardware_proc() {
        return hardware_proc;
    }

    public void setHardware_proc(String hardware_proc) {
        this.hardware_proc = hardware_proc;
    }

    public String getHardware_camera() {
        return hardware_camera;
    }

    public void setHardware_camera(String hardware_camera) {
        this.hardware_camera = hardware_camera;
    }

    public String getHardware_battery() {
        return hardware_battery;
    }

    public void setHardware_battery(String hardware_battery) {
        this.hardware_battery = hardware_battery;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(type);
        parcel.writeString(price);
        parcel.writeString(image);
        parcel.writeString(detail);
        parcel.writeString(brand);
        parcel.writeString(display_size);
        parcel.writeString(display_ratio);
        parcel.writeString(display_resolution);
        parcel.writeString(hardware_proc);
        parcel.writeString(hardware_camera);
        parcel.writeString(hardware_battery);
    }
}