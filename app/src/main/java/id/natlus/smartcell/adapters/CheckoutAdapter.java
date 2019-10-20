package id.natlus.smartcell.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.natlus.smartcell.R;
import id.natlus.smartcell.db.CheckoutEntity;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {
    private Context context;
    private List<CheckoutEntity> checkoutEntityList;

    public CheckoutAdapter(Context context) {
        this.context = context;
    }

    public List<CheckoutEntity> getCheckoutEntityList() {
        return checkoutEntityList;
    }

    public void setCheckoutEntityList(List<CheckoutEntity> checkoutEntityList) {
        this.checkoutEntityList = checkoutEntityList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View historyView = layoutInflater.inflate(R.layout.item_history, parent, false);
        CheckoutViewHolder viewHolder = new CheckoutViewHolder(historyView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {
        CheckoutEntity checkoutEntity = checkoutEntityList.get(position);
        String detail = checkoutEntity.getAddress() + "\n" + checkoutEntity.getPhone_number();
        holder.typeHistory.setText(checkoutEntity.getType());
        holder.paymentHistory.setText(checkoutEntity.getPayment());
        holder.detailHistory.setText(detail);
    }

    @Override
    public int getItemCount() {
        if(checkoutEntityList == null)
            return 0;
        else
            return checkoutEntityList.size();
    }


    public class CheckoutViewHolder extends RecyclerView.ViewHolder{
        public TextView typeHistory, paymentHistory, detailHistory;
        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            typeHistory = itemView.findViewById(R.id.history_typePhone);
            paymentHistory = itemView.findViewById(R.id.history_paymentPhone_selected);
            detailHistory = itemView.findViewById(R.id.history_detailPhone);
        }
    }
}
