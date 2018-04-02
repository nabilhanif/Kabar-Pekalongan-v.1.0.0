package dimasdroid.com.kabarpekalongan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dimasdroid.com.kabarpekalongan.Model.ModelData;
import dimasdroid.com.kabarpekalongan.R;

/**
 * Created by ERIK on 9/18/2017.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>implements Filterable {
    private List<ModelData> mItems;
    private Context context;
    private ArrayList<ModelData> mArrayList;
    private ArrayList<ModelData> mFilteredList;

    public AdapterData(ArrayList<ModelData> arrayList) {
        mArrayList = arrayList;
        mFilteredList = arrayList;
    }

    public AdapterData(Context context, List<ModelData> items){
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_berita,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(final HolderData holder, final int position) {
        ModelData md = mItems.get(position);
        holder.tvJudul.setText(md.getJudul());
        holder.tvTanggal.setText(md.getTanggal());
        holder.md = md;

        /*holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("detail", "Detail Sekolah");
                Intent intent = new Intent(context, DetailSekolah.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<ModelData> filteredList = new ArrayList<>();

                    for (ModelData androidVersion : mArrayList) {

                        if (androidVersion.getJudul().toLowerCase().contains(charString) || androidVersion.getTanggal().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<ModelData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    class HolderData extends ViewHolder {
        TextView tvJudul, tvTanggal;
        //CardView card;
        ModelData md;
        Button btnjurnal;

        public HolderData(View view){
            super(view);

            tvJudul = (TextView) view.findViewById(R.id.judul_berita);
            tvTanggal = (TextView) view.findViewById(R.id.tanggal_berita);
            //card = (CardView) view.findViewById(R.id.card);

            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detail = new Intent(context, DetailSekolah.class);
                    detail.putExtra("id", md.getId());
                    detail.putExtra("nama_sekolah", md.getNamaSekolah());
                    detail.putExtra("alamat_sekolah", md.getAlamatSekkolah());
                    detail.putExtra("telp_sekolah", md.getNo_sekolah());
                    detail.putExtra("min", md.getMin());
                    detail.putExtra("email_sekolah", md.getEmail_sekolah());
                    detail.putExtra("website_sekolah", md.getWebsite_sekolah());
                    detail.putExtra("kuota", md.getKuota());
                    detail.putExtra("info_umum", md.getInfo_umum());
                    //detail.putExtra("detail_siswa", md.getDetail_siswa());

                    context.startActivity(detail);
                }
            });*/
        }
    }
}
