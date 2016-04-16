package com.ameron32.apps.somalibible.frmk;

/**
 *
 *
 * Created by klemeilleur on 4/15/2016.
 */
public interface IBible {

  int getBookCount();
  int getChapterCount(int book);
  int getVerseCount(int book, int chapter);

  // TODO bad method name. should be setBookNames()
  void setChapterNames(String[] names);
  void setAbbrevs(String[] abbrevs);

  String getChapterName(int chapter);
  String getChapterAbbrev(int chapter);
}
