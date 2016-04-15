package com.ameron32.apps.somalibible.ui.book;

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

  public BookAdapter(IBible bible, OnItemClickListener listener) {
    this.bible = bible;
    this.listener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_book, parent, false));
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    holder.text.setText(bible.getChapterName(position)); // TODO tablet v. phone
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        listener.onItemClick(position);
      }
    });
  }

  @Override
  public int getItemCount() {
    return bible.getBookCount();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    TextView text;
    public ViewHolder(View itemView) {
      super(itemView);
      text = (TextView) itemView.findViewById(R.id.text);
    }
  }
}
