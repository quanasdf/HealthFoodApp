package com.example.myapplication.ADAPTER;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.FRAGMENT.HomeFragment;
import com.example.myapplication.MODEL.Loaisanpham;
import com.example.myapplication.MODEL.Sanpham;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.util.List;


public class SanPhamNgangAdapter extends RecyclerView.Adapter<SanPhamNgangAdapter.ViewHolder> {
    private Context context;
    private List<Sanpham>list;

    public HomeFragment fragHome = new HomeFragment();
    public SanPhamNgangAdapter(Context context, List<Sanpham> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sp_horizoltal,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sanpham sp = list.get(position);
        if(sp==null)
            return;

        //new DownloadImageTask(holder.img_sp).execute(sp.getImgURL());
        Glide.with(context).load(sp.getImgURL()).into(holder.img_sp);
        holder.tv_name.setText(sp.getName());
        holder.tv_price.setText(sp.getPrice()+"$");
        holder.tv_minutes.setText(sp.getTime_ship()+"minutes");
        holder.tv_ten_loai.setText(sp.getTen_loai());

       if(sp.isFavorite()){
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_24);

       }else {
           holder.img_favorite.setImageResource(R.drawable.ic_favorite_border_24);

       }
       holder.img_favorite.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(!sp.isFavorite()){
                   holder.img_favorite.setImageResource(R.drawable.ic_favorite_24);

                setFavoriteToServer(sp.getTen_loai(), sp.getMasp(),true);
               }else {
                   holder.img_favorite.setImageResource(R.drawable.ic_favorite_border_24);
                   setFavoriteToServer(sp.getTen_loai(), sp.getMasp(),false);
               }
           }
       });


    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_sp, img_favorite;
        TextView tv_name, tv_price, tv_minutes, tv_ten_loai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_sp = itemView.findViewById(R.id.img_food);
            img_favorite = itemView.findViewById(R.id.imgFood_favorite);
            tv_name = itemView.findViewById(R.id.tv_namOfFood);
            tv_price = itemView.findViewById(R.id.tv_price_food);
            tv_minutes = itemView.findViewById(R.id.tv_time_ship);
            tv_ten_loai = itemView.findViewById(R.id.tv_gerMan_food);
        }

    }

    public void setFavoriteToServer(String namecompare, String masp_update, boolean status){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("LoaiSanPhams").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Loaisanpham lsp = (Loaisanpham) document.toObject(Loaisanpham.class);
                        if(lsp.getName().equals(namecompare)){
                            db.collection("LoaiSanPhams").document(document.getId()).update("sanphams."+masp_update+".favorite", status);
                        }
                    }
                }
            }
        });
    }
//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public DownloadImageTask(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }
}
