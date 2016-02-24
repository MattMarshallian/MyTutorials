package com.example.tutorial.marcin.mylibrary.general.containers.interfaces;

import android.view.View;import java.lang.Object;

public interface IRemovableItemsAdapterComponent {
	/**
	 * Called when item is removed from component by user clicking on remove button
	 * @return true, if you removed item from adapter manually in this step
	 */
	boolean onItemRemove(int position, View view, Object item);
}
