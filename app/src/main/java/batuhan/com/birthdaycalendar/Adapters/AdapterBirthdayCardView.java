package batuhan.com.birthdaycalendar.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import batuhan.com.birthdaycalendar.Models.BirthdayModel;
import batuhan.com.birthdaycalendar.R;

public class AdapterBirthdayCardView extends RecyclerView.Adapter<AdapterBirthdayCardView.CardTasarimTutucu> {

    private Context mContext;
    private ArrayList<BirthdayModel> birthdayModelArrayList;
    private VeritabaniYardimcisi vt;

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
    public void onBindViewHolder(@NonNull final CardTasarimTutucu holder, int position) {

        vt = new VeritabaniYardimcisi(mContext);

        final BirthdayModel birthdayModel = birthdayModelArrayList.get(position);

        holder.txtBirthdayName.setText(birthdayModel.getBirthdayName());

        holder.txtNote.setText(birthdayModel.getBirthdayNote());

        if(birthdayModel.getBirthdayFavorite() == 0){
            holder.btnFavorite.setBackgroundResource(R.drawable.icon_empty_star_yellow);

        }else if(birthdayModel.getBirthdayFavorite() == 1){
            holder.btnFavorite.setBackgroundResource(R.drawable.icon_filled_star_yellow);
        }

        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.btnFavorite.getBackground().getConstantState()
                        .equals(mContext.getResources().getDrawable(R.drawable.icon_empty_star_yellow).getConstantState())){
                    holder.btnFavorite.setBackgroundResource(R.drawable.icon_filled_star_yellow);
                    new BirthdayDAO().changeFavoriteStatus(vt,birthdayModel,1);
                }else if(holder.btnFavorite.getBackground().getConstantState()
                        .equals(mContext.getResources().getDrawable(R.drawable.icon_filled_star_yellow).getConstantState())){
                    holder.btnFavorite.setBackgroundResource(R.drawable.icon_empty_star_yellow);
                    new BirthdayDAO().changeFavoriteStatus(vt,birthdayModel,0);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return birthdayModelArrayList.size();
    }


    public void removeItem(int position){
        birthdayModelArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(BirthdayModel model, int position){
        birthdayModelArrayList.add(position,model);
        notifyItemInserted(position);
    }

    public ArrayList<BirthdayModel> getData(){
        return birthdayModelArrayList;
    }




    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView txtBirthdayName;
        private TextView txtNote;
        private Button btnFavorite;


        public CardTasarimTutucu(View view){
            super(view);
            txtBirthdayName = view.findViewById(R.id.txtBirthdayName);
            txtNote = view.findViewById(R.id.txtNote);
            btnFavorite = view.findViewById(R.id.btnFavorite);


        }
    }


}
