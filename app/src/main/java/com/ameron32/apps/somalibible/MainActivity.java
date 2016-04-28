package com.ameron32.apps.somalibible;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;

import com.ameron32.apps.somalibible.bible.Bible;
import com.ameron32.apps.somalibible.frmk.BibleProvider;
import com.ameron32.apps.somalibible.ui.book.BookSelectionFragment;
import com.ameron32.apps.somalibible.ui.chapter.ChapterSelectionFragment;
import com.ameron32.apps.somalibible.frmk.BibleReceiver;
import com.ameron32.apps.somalibible.frmk.IBible;
import com.ameron32.apps.somalibible.frmk.NavigationListener;
import com.ameron32.apps.somalibible.frmk.NavigationRequestor;
import com.ameron32.apps.somalibible.util.Util;
import com.ameron32.apps.somalibible.ui.verse.ChapterDisplayFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationListener, BibleProvider {

  IBible bible;
  int currentBook;
  ProgressBar progress;

  List<BibleReceiver> receivers = new ArrayList<>();

  public void setActionBarTitle(String title) {
    getSupportActionBar().setTitle(title);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Kitaabka Quduuska Ah");

    progress = (ProgressBar) findViewById(R.id.progress);
    progress.setVisibility(ProgressBar.GONE);

    SharedPreferences prefs = getPreferences(MODE_PRIVATE);
    int size = prefs.getInt(getString(R.string.text_size), 0);
    if (size == 0){
      SharedPreferences.Editor e = prefs.edit();
      e.putInt(getString(R.string.text_size), 16);
      e.putInt(getString(R.string.text_selection), 1);
      e.commit();
    }

    final AsyncTask<Void, Void, Void> loadBible = new LoadBibleAsyncTask().execute();

    if (savedInstanceState == null) {
      swapFragment(BookSelectionFragment.newInstance(), false);
    }
  }

  void onLoading() {
    progress.setVisibility(ProgressBar.VISIBLE);
  }

  void onLoadInBackgroundThread() {

    Log.d("Bible", "Bible loading.");
    bible = new Bible(getAssets());
    ((Bible) bible).load(MainActivity.this);
    Log.d("Bible", "Bible loaded.");

  }

  void onComplete() {
    for (BibleReceiver receiver: receivers) {
      receiver.passBible(bible);
    }
    progress.setVisibility(ProgressBar.GONE);
  }

  public void swapFragment(Fragment fragment, boolean addToBackStack) {
    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
            android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    ft.replace(R.id.fragment_container, fragment, "primary");
    if (addToBackStack) {
      ft.addToBackStack(null);
    }
    ft.commit();
  }

  private void resetTitleBar(){
    getSupportActionBar().setTitle("Kitaabka Quduuska Ah");

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public void onBack(NavigationRequestor requestor) {
    String id = requestor.getRequestorId();
    if (!Util.isInteger(id)) {
      return;
    }
    int idNumber = Integer.parseInt(id);
    if (idNumber ==1){
    }
    if (idNumber == 0) {
      finish();
    }

    getSupportFragmentManager().popBackStack();
  }

  @Override
  public void onBook(int bookOrdinal) {
    currentBook = bookOrdinal;
    swapFragment(ChapterSelectionFragment.newInstance(bookOrdinal), true);
  }

  @Override
  public void onChapter(int bookOrdinal, int chapter) {
    swapFragment(ChapterDisplayFragment.newInstance(bookOrdinal, chapter), true);
  }

  @Override
  public void requestBible(BibleReceiver receiver) {
    receivers.add(receiver);
    if (bible != null && ((Bible) bible).isLoaded()) {
      receiver.passBible(bible);
    }
  }

  class LoadBibleAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      onLoading();
    }

    @Override
    protected Void doInBackground(Void... params) {
      onLoadInBackgroundThread();
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      onComplete();
    }
  }

  @Override
  public void onBackPressed() {

    int count = getSupportFragmentManager().getBackStackEntryCount();
    super.onBackPressed();
    if (count == 1) {
      resetTitleBar();
    }else{
      if (count==2){
        getSupportActionBar().setTitle(bible.getBookName(currentBook));
      }
    }
  }


}
