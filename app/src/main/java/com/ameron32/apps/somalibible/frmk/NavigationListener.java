package com.ameron32.apps.somalibible.frmk;

/**
 * Callback for Non-Navigation UI elements to trigger a navigation change.
 *
 * Created by klemeilleur on 4/15/2016.
 */
public interface NavigationListener {

  void onBack(NavigationRequestor requestor);
  void onBook(int bookOrdinal);
  void onChapter(int book, int chapter);
}
