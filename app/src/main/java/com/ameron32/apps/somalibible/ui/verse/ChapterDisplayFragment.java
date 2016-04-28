package com.ameron32.apps.somalibible.ui.verse;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ameron32.apps.somalibible.MainActivity;
import com.ameron32.apps.somalibible.R;
import com.ameron32.apps.somalibible.frmk.BibleProvider;
import com.ameron32.apps.somalibible.frmk.BibleReceiver;
import com.ameron32.apps.somalibible.frmk.IBible;
import com.ameron32.apps.somalibible.frmk.NavigationListener;
import com.ameron32.apps.somalibible.frmk.NavigationRequestor;
import com.ameron32.apps.somalibible.util.OnSwipeTouchListener;


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
    setHasOptionsMenu(true);
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

    View v = inflater.inflate(R.layout.fragment_chapter_display, container, false);
    v.setOnTouchListener(new OnSwipeTouchListener(getContext()) {

      @Override
      public void onSwipeRight() {
        //Going backwards
        if ((chapter == 0) && (book>0)){
          book = book -1;
          chapter = bible.getChapterCount(book)-1;
          //Toast.makeText(getContext(), "Flipping...", Toast.LENGTH_SHORT).show();
        }else{
          if (book >0)
            chapter = chapter -1;
          //Toast.makeText(getContext(), "Flipping...", Toast.LENGTH_SHORT).show();
        }
        ((MainActivity)getActivity()).setActionBarTitle(bible.getBookName(book)+ " "+ String.valueOf(chapter+1));
        text.setText(new SpannableStringBuilder(getChapterString()));
      }


      @Override
      public void onSwipeLeft() {
        int max = bible.getChapterCount(book);
        if ((chapter == max-1)&&(book<65)){
          book = book +1;
          chapter = 0;
          //Toast.makeText(getContext(), "Flipping...", Toast.LENGTH_SHORT).show();
        }else{
          if (chapter <max -1)
            chapter = chapter +1;
          //Toast.makeText(getContext(), "Flipping...", Toast.LENGTH_SHORT).show();
        }
        ((MainActivity)getActivity()).setActionBarTitle(bible.getBookName(book)+ " "+ String.valueOf(chapter+1));
        text.setText(new SpannableStringBuilder(getChapterString()));
      }

    });


    // Inflate the layout for this fragment
    return v;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    text = (TextView) view.findViewById(R.id.text);
    int size = getActivity().getPreferences(Context.MODE_PRIVATE).getInt(getString(R.string.text_size), 0);
    text.setTextSize(size);

  }

  @Override
  public void onResume() {
    super.onResume();
    bibleProvider.requestBible(this);
  }

  private Spanned getChapterString() {
    return Html.fromHtml(bible.getChapterVerses(book, chapter));
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
    if (text != null) {
      SpannableStringBuilder ssb = new SpannableStringBuilder(getChapterString());
      text.setText(ssb);
    }
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
                text.setTextSize(12);
                e.putInt(getString(R.string.text_size), 12);
                e.putInt(getString(R.string.text_selection), 0);
                break;
              }
              case 1:{
                text.setTextSize(16);
                e.putInt(getString(R.string.text_size), 16);
                e.putInt(getString(R.string.text_selection), 1);
                break;
              }
              case 2:{
                text.setTextSize(24);
                e.putInt(getString(R.string.text_size), 24);
                e.putInt(getString(R.string.text_selection), 2);
                break;
              }
              case 3:{
                text.setTextSize(40);
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
