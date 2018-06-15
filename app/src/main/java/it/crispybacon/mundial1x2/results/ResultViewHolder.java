package it.crispybacon.mundial1x2.results;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.crispybacon.mundial1x2.R;

/**
 * Created by Jameido on 15/06/2018.
 */
public class ResultViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private OnItemClickListener mOnItemClickListener = null;

    public ResultViewHolder(View itemView) {
        super(itemView);
    }

    public ResultViewHolder(final ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_result, parent, false)
        );
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    protected void bind(Result result) {

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }
}
