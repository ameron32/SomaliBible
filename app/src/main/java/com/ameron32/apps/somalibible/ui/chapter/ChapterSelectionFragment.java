package com.ameron32.apps.somalibible.ui.chapter;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.ameron32.apps.somalibible.ui.book.BookAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterSelectionFragment extends Fragment
    implements NavigationRequestor, BibleReceiver, OnItemClickListener {


  private static final String BOOK_KEY = "bookOrdinal";

  private int book;

  public static ChapterSelectionFragment newInstance(int bookOrdinal) {
    ChapterSelectionFragment f = new ChapterSelectionFragment();
    Bundle args = new Bundle();
    // store args
    args.putInt(BOOK_KEY, bookOrdinal);
    f.setArguments(args);
    return f;
  }

  private IBible bible;
  private RecyclerView chapterGrid;

  public ChapterSelectionFragment() {
    // Required empty public constructor
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      final Bundle args = getArguments();
      // restore args
      book = args.getInt(BOOK_KEY);
    }
    bibleProvider.requestBible(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_chapter_selection, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    chapterGrid = (RecyclerView) view.findViewById(R.id.chapter_grid);
    chapterGrid.setLayoutManager(new GridLayoutManager(view.getContext(), 5));
  }

  @Override
  public void onResume() {
    super.onResume();
    bibleProvider.requestBible(this);
    chapterGrid.setAdapter(new ChapterAdapter(bible, book, this));
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
    return "1";
  }

  @Override
  public void passBible(IBible bible) {
    this.bible = bible;
  }

  @Override
  public void onItemClick(int position) {
    navigationListener.onChapter(book, position);
  }
}
