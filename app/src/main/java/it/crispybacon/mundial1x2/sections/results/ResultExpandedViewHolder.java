package it.crispybacon.mundial1x2.sections.results;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import it.crispybacon.mundial1x2.R;

/**
 * Created by Jameido on 15/06/2018.
 */
public class ResultExpandedViewHolder extends ResultViewHolder {

    public ResultExpandedViewHolder(final ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_result_expanded, parent, false)
        );
    }

    protected void bind(Result result) {

    }
}
