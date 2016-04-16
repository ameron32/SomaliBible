package com.ameron32.apps.somalibible.ui.book;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ameron32.apps.somalibible.R;
import com.ameron32.apps.somalibible.frmk.BibleProvider;
import com.ameron32.apps.somalibible.frmk.BibleReceiver;
import com.ameron32.apps.somalibible.frmk.IBible;
import com.ameron32.apps.somalibible.frmk.NavigationListener;
import com.ameron32.apps.somalibible.frmk.NavigationRequestor;
import com.ameron32.apps.somalibible.frmk.OnItemClickListener;


/**
 * A placeholder fragment containing a simple view.
 */
public class BookSelectionFragment extends Fragment
    implements NavigationRequestor, BibleReceiver, OnItemClickListener {


  public static BookSelectionFragment newInstance() {
    return new BookSelectionFragment();
  }

  private IBible bible;
  private RecyclerView bookGrid;

  public BookSelectionFragment() {
    // Required empty public constructor
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      final Bundle args = getArguments();
      // restore args

    }
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
  }

  @Override
  public void onResume() {
    super.onResume();
    bibleProvider.requestBible(this);
    bookGrid.setAdapter(new BookAdapter(bible, this));
  }

  NavigationListener navigationListener;
  BibleProvider bibleProvider;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof NavigationListener
        && context instanceof BibleProvider) {
      navigationListener = (NavigationListener) context;
      bibleProvider = (BibleProvider) context;
    } else {
      throw new IllegalStateException("activity must implement " + NavigationListener.class.getSimpleName());
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    navigationListener = null;
    bibleProvider = null;
  }


  @Override
  public String getRequestorId() {
    return "0";
  }

  @Override
  public void passBible(IBible bible) {
    this.bible = bible;
  }

  @Override
  public void onItemClick(int position) {
    navigationListener.onBook(position);
  }
}
