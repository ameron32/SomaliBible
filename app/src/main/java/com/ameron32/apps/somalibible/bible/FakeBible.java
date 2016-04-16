package com.ameron32.apps.somalibible.bible;

import com.ameron32.apps.somalibible.frmk.IBible;

/**
 * Simply laughable implementation of IBible to enable framework.
 *
 * Created by klemeilleur on 4/15/2016.
 */
public class FakeBible implements IBible {

  @Override
  public int getBookCount() {
    return 66;
  }

  @Override
  public int getChapterCount(int book) {
    return 3;
  }

  @Override
  public int getVerseCount(int book, int chapter) {
    return 5;
  }

  @Override
  public void setBookNames(String[] names) {
    return;
  }

  @Override
  public void setAbbrevs(String[] abbrevs) {
    return;
  }

  @Override
  public String getBookName(int chapter) {
    return "Name";
  }

  @Override
  public String getBookAbbrev(int chapter) {
    return "Na.";
  }

  @Override
  public String getChapterVerses(int bookNumber, int chapter) {
    return bookNumber +", "+ chapter;
  }

  @Override
  public String getVerse(int bookNumber, int chapter, int verse) {
    return bookNumber +", "+ chapter +", "+ verse;
  }

  @Override
  public String getVerses(int bookNumber, int chapter, int... verses) {
    return bookNumber +", "+ chapter +", "+ verses.length;
  }
}
