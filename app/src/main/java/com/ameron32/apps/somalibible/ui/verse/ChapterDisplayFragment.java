package com.ameron32.apps.somalibible.ui.verse;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ameron32.apps.somalibible.R;
import com.ameron32.apps.somalibible.frmk.BibleProvider;
import com.ameron32.apps.somalibible.frmk.BibleReceiver;
import com.ameron32.apps.somalibible.frmk.IBible;
import com.ameron32.apps.somalibible.frmk.NavigationListener;
import com.ameron32.apps.somalibible.frmk.NavigationRequestor;
import com.ameron32.apps.somalibible.ui.book.BookAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterDisplayFragment extends Fragment
    implements NavigationRequestor, BibleReceiver {


  private static final String BOOK_KEY = "bookOrdinal";
  private static final String CHAPTER_KEY = "chapter";

  private int book;
  private int chapter;
  private IBible bible;
  private TextView text;

  public static ChapterDisplayFragment newInstance(int bookOrdinal, int chapter) {
    ChapterDisplayFragment f = new ChapterDisplayFragment();
    Bundle args = new Bundle();
    // store args
    args.putInt(BOOK_KEY, bookOrdinal);
    args.putInt(CHAPTER_KEY, chapter);
    f.setArguments(args);
    return f;
  }

  public ChapterDisplayFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      final Bundle args = getArguments();
      // restore args
      book = args.getInt(BOOK_KEY);
      chapter = args.getInt(CHAPTER_KEY);
    }
    bibleProvider.requestBible(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_chapter_display, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    text = (TextView) view.findViewById(R.id.text);
  }

  @Override
  public void onResume() {
    super.onResume();
    bibleProvider.requestBible(this);
    text.setText( getChapterString() );
  }

  private String getChapterString() {
    String text = "Book: " + book + " and Chapter: " + chapter + "\n\n";
    return text + bible.getChapterVerses(book, chapter);
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
    return "2";
  }

  @Override
  public void passBible(IBible bible) {
    this.bible = bible;
  }
}
