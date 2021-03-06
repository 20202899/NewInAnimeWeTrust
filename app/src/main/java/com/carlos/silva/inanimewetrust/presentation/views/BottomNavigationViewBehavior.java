package com.carlos.silva.inanimewetrust.presentation.views;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    private int height;
    private boolean isShowing = true;

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {
        height = child.getHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       BottomNavigationView child, @NonNull
                                               View directTargetChild, @NonNull View target,
                                       int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull
            BottomNavigationView child, @NonNull View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, type, consumed);
        if (dyConsumed > 0) {
            slideDown(child);
        } else if (dyConsumed < 0) {
            slideUp(child);
        }
    }

    private void slideUp(BottomNavigationView child) {
        child.clearAnimation();

        if (isShowing) {
            return;
        }

        child.animate().translationY(0).setDuration(200);
        isShowing = !isShowing;
    }

    private void slideDown(BottomNavigationView child) {
        child.clearAnimation();

        if (!isShowing) {
            return;
        }

        child.animate().translationY(height).setDuration(200);
        isShowing = !isShowing;
    }
}