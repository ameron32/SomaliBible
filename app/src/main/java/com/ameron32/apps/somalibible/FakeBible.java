package com.ameron32.apps.somalibible;

/**
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
  public void setChapterNames(String[] names) {
    return;
  }

  @Override
  public void setAbbrevs(String[] abbrevs) {
    return;
  }

  @Override
  public String getChapterName(int chapter) {
    return "NAME";
  }

  @Override
  public String getChapterAbbrev(int chapter) {
    return "NA.";
  }
}
