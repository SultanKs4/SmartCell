package id.natlus.smartcell.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Phone implements Parcelable {
    private String type, price, image, detail;

    private Phone(Parcel in) {
        type = in.readString();
        price = in.readString();
        image = in.readString();
        detail = in.readString();
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

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

    public Phone(String type, String price, String image, String detail) {
        this.type = type;
        this.price = price;
        this.image = image;
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(price);
        parcel.writeString(image);
        parcel.writeString(detail);
    }
}
