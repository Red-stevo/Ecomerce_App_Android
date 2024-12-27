package Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.redstevo.ecomerce_app.R;

import java.util.List;

import Models.ImagePreviewModel;

public class ImageVideoPreviewAdapter extends RecyclerView.Adapter<ImageVideoPreviewAdapter.ViewHolder> {

    List<ImagePreviewModel> imagePreviewModelList;

    Context context;

    public ImageVideoPreviewAdapter(List<ImagePreviewModel> imagePreviewModelList, Context context) {
        this.imagePreviewModelList = imagePreviewModelList;
        this.context = context;
    }

    @NonNull
    @androidx.annotation.NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @androidx.annotation.NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.image_preview, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @androidx.annotation.NonNull ViewHolder viewHolder, int i) {
        ImagePreviewModel imagePreviewModel = imagePreviewModelList.get(i);

        Uri imageUri = imagePreviewModel.getImageVideoUri();

        if (imageUri != null)
            viewHolder.imagePreview.setImageURI(imageUri);
        else
            Toast.makeText(context, "Error, Image Uri Not is Null", Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return imagePreviewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagePreview;

        public ViewHolder(@NonNull @androidx.annotation.NonNull View itemView) {
            super(itemView);
            imagePreview = itemView.findViewById(R.id.image_preview_field);
        }
    }
}
