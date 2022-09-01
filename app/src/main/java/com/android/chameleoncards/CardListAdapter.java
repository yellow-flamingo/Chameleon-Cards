package com.android.chameleoncards;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class CardListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Card> cardsList;

    public CardListAdapter(Context context, int layout, ArrayList<Card> cardsList) {
        this.context = context;
        this.layout = layout;
        this.cardsList = cardsList;
    }

    @Override
    public int getCount() {
        return cardsList.size();
    }

    @Override
    public Object getItem(int i) {
        return cardsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.imageView = (ImageView) row.findViewById(R.id.imgCard);
            //Log.d("Image: ", "true");

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
            //Log.d("image: ","true");
        }

        Card card = cardsList.get(i);

        byte[] cardImage = card.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(cardImage, 0, cardImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
