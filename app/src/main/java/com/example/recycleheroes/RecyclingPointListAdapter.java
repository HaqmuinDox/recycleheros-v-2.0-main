package com.example.recycleheroes;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public class RecyclingPointListAdapter extends ArrayAdapter<RecyclingPointModel> {

    public RecyclingPointListAdapter(Context context, List<RecyclingPointModel> recyclingPoints) {
        super(context, 0, recyclingPoints);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecyclingPointModel recyclingPoint = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_recycling_point, parent, false);
        }

        // Bind data to the view
        TextView textViewCategory = convertView.findViewById(R.id.textViewCategory);
        TextView textViewType = convertView.findViewById(R.id.textViewType);
        TextView textViewCreationDate = convertView.findViewById(R.id.textViewCreationDate);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        if (recyclingPoint != null) {
            textViewCategory.setText(String.valueOf(recyclingPoint.category));
            textViewType.setText(recyclingPoint.type);
            textViewCreationDate.setText("vor " + TimeUtils.getTimeAgo(recyclingPoint.creation_date));

            if (!recyclingPoint.images.isEmpty()) {
                String base64Image = recyclingPoint.images; // Assuming you want to load the first image
                byte[] decodedImage = Base64.decode(base64Image, Base64.DEFAULT);
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.length));
            }
            // Set other properties accordingly
        }

        return convertView;
    }
}
