package com.ameron32.apps.somalibible;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class BookSelectionFragment extends Fragment
    implements NavigationRequestor, BibleReceiver, OnItemClickListener {


  IBible bible;
  RecyclerView bookGrid;

  public BookSelectionFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_book_selection, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    bookGrid = (RecyclerView) view.findViewById(R.id.book_grid);
    bookGrid.setLayoutManager(new GridLayoutManager(view.getContext(), 5));
    bookGrid.setAdapter(new BookAdapter(bible, this));
  }


  NavigationListener navigationListener;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof NavigationListener) {
      navigationListener = (NavigationListener) context;
    } else {
      throw new IllegalStateException("activity must implement " + NavigationListener.class.getSimpleName());
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    navigationListener = null;
  }


  @Override
  public String getRequestorId() {
    return "0";
  }

  @Override
  public void receiveBible(IBible bible) {
    this.bible = bible;
  }

  @Override
  public void onItemClick(int position) {
    navigationListener.onBook(position);
  }
}
