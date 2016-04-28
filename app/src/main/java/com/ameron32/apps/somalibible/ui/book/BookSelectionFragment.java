package com.ameron32.apps.somalibible.ui.book;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    setHasOptionsMenu(true);

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
    GridLayoutManager gridLayout = new GridLayoutManager(view.getContext(), 5);
    gridLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        if ((position==0)||(position==40))return 5;
        else{
          return 1;
        }
      }
    });
    bookGrid.setLayoutManager(gridLayout);
    bookGrid.addItemDecoration(new GridSpacingItemDecoration(5, 5, true));



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
    return "0";
  }

  @Override
  public void passBible(IBible bible) {
    this.bible = bible;
    if (bookGrid != null) {
      bookGrid.setAdapter(new BookAdapter(bible, this));
    }

  }

  @Override
  public void onItemClick(int position) {
    navigationListener.onBook(position);
    ((MainActivity)navigationListener).getSupportActionBar().setTitle(bible.getBookName(position));
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
