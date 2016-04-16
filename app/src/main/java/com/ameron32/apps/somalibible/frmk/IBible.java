package com.ameron32.apps.somalibible.frmk;

/**
 *
 *
 * Created by klemeilleur on 4/15/2016.
 */
public interface IBible {

  int getBookCount();
  int getChapterCount(int bookNumber);
  int getVerseCount(int bookNumber, int chapter);

  void setBookNames(String[] names);
  void setAbbrevs(String[] abbrevs);

  String getBookName(int bookNumber);
  String getBookAbbrev(int bookNumber);

  String getChapterVerses(int bookNumber, int chapter);
  String getVerse(int bookNumber, int chapter, int verse);
  String getVerses(int bookNumber, int chapter, int... verses);
}
