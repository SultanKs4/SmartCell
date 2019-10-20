package id.natlus.smartcell.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Checkout implements Parcelable {
    private String type, price, image, detail, name, address, phone, email, payment;

    public Checkout(String type, String price, String image, String detail, String name, String address, String phone, String email, String payment) {
        this.type = type;
        this.price = price;
        this.image = image;
        this.detail = detail;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.payment = payment;
    }

    protected Checkout(Parcel in) {
        type = in.readString();
        price = in.readString();
        image = in.readString();
        detail = in.readString();
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        email = in.readString();
        payment = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(price);
        dest.writeString(image);
        dest.writeString(detail);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(payment);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Checkout> CREATOR = new Creator<Checkout>() {
        @Override
        public Checkout createFromParcel(Parcel in) {
            return new Checkout(in);
        }

        @Override
        public Checkout[] newArray(int size) {
            return new Checkout[size];
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public static Creator<Checkout> getCREATOR() {
        return CREATOR;
    }
}
