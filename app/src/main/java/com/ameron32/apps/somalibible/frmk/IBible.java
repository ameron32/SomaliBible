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

  void setBookNames(String[] names);
  void setAbbrevs(String[] abbrevs);

  String getBookName(int chapter);
  String getBookAbbrev(int chapter);

  String getChapterVerses(int bookNumber, int chapter);
  String getVerse(int bookNumber, int chapter, int verse);
  String getVerses(int bookNumber, int chapter, int... verses);
}
