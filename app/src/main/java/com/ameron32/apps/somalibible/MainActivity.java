package com.ameron32.apps.somalibible;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ameron32.apps.somalibible.bible.FakeBible;
import com.ameron32.apps.somalibible.ui.book.BookSelectionFragment;
import com.ameron32.apps.somalibible.ui.chapter.ChapterSelectionFragment;
import com.ameron32.apps.somalibible.frmk.BibleReceiver;
import com.ameron32.apps.somalibible.frmk.IBible;
import com.ameron32.apps.somalibible.frmk.NavigationListener;
import com.ameron32.apps.somalibible.frmk.NavigationRequestor;
import com.ameron32.apps.somalibible.util.Util;
import com.ameron32.apps.somalibible.ui.verse.ChapterDisplayFragment;


public class MainActivity extends AppCompatActivity
  implements NavigationListener {


  IBible bible;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    bible = new FakeBible();
    if (savedInstanceState == null) {
      swapFragment(BookSelectionFragment.newInstance(), false);
    } else {
      Fragment fragment = getSupportFragmentManager().findFragmentByTag("primary");
      if (fragment instanceof BibleReceiver) {
        ((BibleReceiver) fragment).passBible(bible);
      }
    }
  }

  public void swapFragment(Fragment fragment, boolean addToBackStack) {
    if (fragment instanceof BibleReceiver) {
      ((BibleReceiver) fragment).passBible(bible);
    }
    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
        android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    ft.replace(R.id.fragment_container, fragment, "primary");
    if (addToBackStack) {
      ft.addToBackStack(null);
    }
    ft.commit();
  }

  @Override
  public void onBack(NavigationRequestor requestor) {
    String id = requestor.getRequestorId();
    if (!Util.isInteger(id)) {
      return;
    }
    int idNumber = Integer.parseInt(id);
    if (idNumber == 0) {
      finish();
    }
    getSupportFragmentManager().popBackStack();
  }

  @Override
  public void onBook(int bookOrdinal) {
    swapFragment(ChapterSelectionFragment.newInstance(bookOrdinal), true);
  }

  @Override
  public void onChapter(int bookOrdinal, int chapter) {
    swapFragment(ChapterDisplayFragment.newInstance(bookOrdinal, chapter), true);
  }
}
