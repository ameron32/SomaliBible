package com.ameron32.apps.somalibible;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
  implements NavigationListener {


  IBible bible;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

//    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//    fab.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show();
//      }
//    });

    bible = new FakeBible();
    swapFragment(BookSelectionFragment.class, false);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//      return true;
//    }

    return super.onOptionsItemSelected(item);
  }


  public void swapFragment(Fragment fragment, boolean addToBackStack) {
    if (fragment instanceof BibleReceiver) {
      ((BibleReceiver) fragment).receiveBible(bible);
    }
    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(R.id.fragment_container, fragment, "primary");
    if (addToBackStack) {
      ft.addToBackStack(null);
    }
    ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
    ft.commit();
  }

  public void swapFragment(Class<? extends Fragment> fragmentClass, boolean addToBackStack) {
    try {

      Fragment fragment = fragmentClass.newInstance();
      swapFragment(fragment, addToBackStack);

    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  private void swapFragment(int ordinal) {
    swapFragment(Fragments.values()[ordinal].getFragmentClass(), true);
  }

  enum Fragments {
    BookSelection(BookSelectionFragment.class),
    ChapterSelection(ChapterSelectionFragment.class),
    ChapterDisplay(ChapterDisplayFragment.class);

    private final Class<? extends Fragment> chapterDisplayFragmentClass;

    Fragments(Class<? extends Fragment> chapterDisplayFragmentClass) {
      this.chapterDisplayFragmentClass = chapterDisplayFragmentClass;
    }

    Class<? extends Fragment> getFragmentClass() {
      return chapterDisplayFragmentClass;
    }
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

//  @Override
//  public void onNext(NavigationRequestor requestor) {
//    String id = requestor.getRequestorId();
//    if (!Util.isInteger(id)) {
//      return;
//    }
//    int idNumber = Integer.parseInt(id);
//    if (idNumber > Fragments.values().length -2) {
//      return;
//    }
//    idNumber++;
//    swapFragment(idNumber);
//  }

  @Override
  public void onBook(int bookOrdinal) {
    swapFragment(ChapterSelectionFragment.newInstance(bookOrdinal), true);
  }

  @Override
  public void onChapter(int bookOrdinal, int chapter) {
    swapFragment(ChapterDisplayFragment.newInstance(bookOrdinal, chapter), true);
  }
}
