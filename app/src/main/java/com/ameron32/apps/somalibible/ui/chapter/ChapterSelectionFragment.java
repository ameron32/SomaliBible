package com.ameron32.apps.somalibible.ui.chapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ameron32.apps.somalibible.MainActivity;
import com.ameron32.apps.somalibible.R;
import com.ameron32.apps.somalibible.frmk.BibleProvider;
import com.ameron32.apps.somalibible.frmk.BibleReceiver;
import com.ameron32.apps.somalibible.frmk.IBible;
import com.ameron32.apps.somalibible.frmk.NavigationListener;
import com.ameron32.apps.somalibible.frmk.NavigationRequestor;
import com.ameron32.apps.somalibible.frmk.OnItemClickListener;
import com.ameron32.apps.somalibible.util.GridSpacingItemDecoration;


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
    setHasOptionsMenu(true);

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
    chapterGrid.addItemDecoration(new GridSpacingItemDecoration(5, 5, true));

  }

  @Override
  public void onResume() {
    super.onResume();
    bibleProvider.requestBible(this);
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
    if (chapterGrid != null) {
      chapterGrid.setAdapter(new ChapterAdapter(bible, book, this));
    }
  }

  @Override
  public void onItemClick(int position) {
    navigationListener.onChapter(book, position);
    ((MainActivity)navigationListener).getSupportActionBar().setTitle(bible.getBookName(book)+ " " + String.valueOf(position+1));

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);

        builder.setTitle(R.string.dialog_title).setSingleChoiceItems(R.array.sizes_array, prefs.getInt(getString(R.string.text_selection), 0), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor e = prefs.edit();

            switch (which){
              case 0:{
                e.putInt(getString(R.string.text_size), 12);
                e.putInt(getString(R.string.text_selection), 0);
                break;
              }
              case 1:{
                e.putInt(getString(R.string.text_size), 16);
                e.putInt(getString(R.string.text_selection), 1);
                break;
              }
              case 2:{
                e.putInt(getString(R.string.text_size), 24);
                e.putInt(getString(R.string.text_selection), 2);
                break;
              }
              case 3:{
                e.putInt(getString(R.string.text_size), 40);
                e.putInt(getString(R.string.text_selection), 3);
                break;
              }
            }
            e.commit();
          }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        return true;
      default:
        // If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);

    }
  }
}
