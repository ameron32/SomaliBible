package com.ameron32.apps.somalibible.frmk;

/**
 * Any class that receives a global IBible object.
 * Waits for passBible() then uses it.
 *
 * Created by klemeilleur on 4/15/2016.
 */
public interface BibleReceiver {

  void passBible(IBible bible);
}
