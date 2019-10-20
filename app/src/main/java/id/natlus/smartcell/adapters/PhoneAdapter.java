package id.natlus.smartcell.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.natlus.smartcell.R;
import id.natlus.smartcell.db.PhoneEntity;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder> {
    private Context context;
    private List<PhoneEntity> phoneEntityList;

    public PhoneAdapter(Context context) {
        this.context = context;
    }

    public List<PhoneEntity> getPhoneEntityList() {
        return phoneEntityList;
    }

    public void setPhoneList(List<PhoneEntity> phoneList) {
        this.phoneEntityList = phoneList;

        notifyDataSetChanged();
    }

    private OnPhoneClickListener listener;
    public interface OnPhoneClickListener{
        public void onClick(View view, int position);

    }

    public void setListener(OnPhoneClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View phoneView = layoutInflater.inflate(R.layout.item_phone, parent, false);
        return new PhoneViewHolder(phoneView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        PhoneEntity itemEntity = phoneEntityList.get(position);
        holder.price.setText(itemEntity.getPrice());
        holder.type.setText(itemEntity.getType());
        holder.detail.setText(itemEntity.getDetail());
        Picasso.get().load(itemEntity.getImage())
                .placeholder(R.color.colorWhite)
                .into(holder.imagePhone);
    }

    @Override
    public int getItemCount() {
        if(this.phoneEntityList == null)
            return 0;
        else
            return phoneEntityList.size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePhone;
        TextView price, type, detail;
        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePhone = itemView.findViewById(R.id.imagePhone);
            price = itemView.findViewById(R.id.pricePhone_price);
            type = itemView.findViewById(R.id.typePhone);
            detail = itemView.findViewById(R.id.detailPhone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, getAdapterPosition());
                }
            });
        }
    }
}
