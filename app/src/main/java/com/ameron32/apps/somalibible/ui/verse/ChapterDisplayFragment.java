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
import com.ameron32.apps.somalibible.frmk.NavigationListener;
import com.ameron32.apps.somalibible.frmk.NavigationRequestor;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterDisplayFragment extends Fragment
    implements NavigationRequestor {


  private static final String BOOK_KEY = "bookOrdinal";
  private static final String CHAPTER_KEY = "chapter";

  private int book;
  private int chapter;

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
    TextView text = (TextView) view.findViewById(R.id.text);
    text.setText("Book: " + book + " and Chapter: " + chapter);
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
    return "2";
  }
}
