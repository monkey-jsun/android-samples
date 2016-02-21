package net.junsun.l16_two_pane_url_bookmarks;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by jsun on 2/15/2016.
 */
public class UrlListFragment extends ListFragment {

    public String[] urlName;

    public interface OnListItemClick {
        public void onListItemClick(int position);
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                urlName);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        OnListItemClick listener = (OnListItemClick)getActivity();
        listener.onListItemClick(position);
    }
}
