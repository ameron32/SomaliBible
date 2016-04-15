package com.ameron32.apps.somalibible.frmk;

/**
 * Non-Navigation UI element descriptor handed to Navigation Listener
 * to communicate who requested a Navigation change.
 *
 * Created by klemeilleur on 4/15/2016.
 */
public interface NavigationRequestor {

  String getRequestorId();
}
