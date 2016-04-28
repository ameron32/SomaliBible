package com.ameron32.apps.somalibible.ui.book;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ameron32.apps.somalibible.R;
import com.ameron32.apps.somalibible.frmk.IBible;
import com.ameron32.apps.somalibible.frmk.OnItemClickListener;

/**
 * Created by klemeilleur on 4/15/2016.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

  private final IBible bible;
  private final OnItemClickListener listener;
  private static final int TYPE_HEADER =0;
  private static final int TYPE_ITEM=1;

  public BookAdapter(IBible bible, OnItemClickListener listener) {
    this.bible = bible;
    this.listener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    if (viewType == TYPE_ITEM) {
      //inflate your layout and pass it to view holder
      return new ViewHolder(LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_book, parent, false));    }

    else if (viewType == TYPE_HEADER) {
      //inflate your layout and pass it to view holder
      return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_header, parent, false));
    }
    return null;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {

    if (position==0){
      holder.text.setText("Axdigii Hore");
      holder.text.setTypeface(null, Typeface.BOLD);
    }else{
      if (position == 40){
        holder.text.setText("Axdiga Cusub");
        holder.text.setTypeface(null, Typeface.BOLD);
      }
      else{
        if (position<40){
          holder.text.setText(bible.getBookAbbrev(position-1)); // TODO tablet v. phone
          holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              listener.onItemClick(position-1);
            }
          });
        }else{
          holder.text.setText(bible.getBookAbbrev(position-2)); // TODO tablet v. phone
          holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              listener.onItemClick(position-2);
            }
          });
        }

      }
    }
  }

  @Override
  public int getItemCount() {
    return bible.getBookCount()+2;
  }

  @Override
  public int getItemViewType(int position){
    if ((position==0)||(position==40))
      return TYPE_HEADER;
    else
      return TYPE_ITEM;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView text;
    public ViewHolder(View itemView) {
      super(itemView);
      text = (TextView) itemView.findViewById(R.id.text);
    }
  }
}
