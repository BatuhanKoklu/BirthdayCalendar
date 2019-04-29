package batuhan.com.birthdaycalendar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import batuhan.com.birthdaycalendar.Models.BirthdayModel;
import batuhan.com.birthdaycalendar.R;

public class AdapterBirthdayCardView extends RecyclerView.Adapter<AdapterBirthdayCardView.CardTasarimTutucu> {

    private Context mContext;
    private ArrayList<BirthdayModel> birthdayModelArrayList;

    public AdapterBirthdayCardView(Context mContext, ArrayList<BirthdayModel> birthdayModelArrayList) {
        this.mContext = mContext;
        this.birthdayModelArrayList = birthdayModelArrayList;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recylerview_birthdays,viewGroup,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

        BirthdayModel birthdayModel =birthdayModelArrayList.get(position);

        holder.txtBirthdayName.setText(birthdayModel.getBirthdayName());

        holder.txtNote.setText(birthdayModel.getBirthdayNote());

        if(birthdayModel.getBirthdayFavorite() == 0){
            holder.imgFavorite.setBackgroundResource(R.drawable.icon_empty_star_yellow);

        }else if(birthdayModel.getBirthdayFavorite() == 1){
            holder.imgFavorite.setBackgroundResource(R.drawable.icon_filled_star_yellow);
        }
    }

    @Override
    public int getItemCount() {
        return birthdayModelArrayList.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView txtBirthdayName;
        private TextView txtNote;
        private ImageView imgFavorite;

        public CardTasarimTutucu(View view){
            super(view);
            txtBirthdayName = view.findViewById(R.id.txtBirthdayName);
            txtNote = view.findViewById(R.id.txtNote);
            imgFavorite = view.findViewById(R.id.imgFavorite);
        }
    }
}
