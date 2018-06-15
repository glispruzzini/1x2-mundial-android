package it.crispybacon.mundial1x2.results;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jameido on 15/06/2018.
 */
public class ResultsAdapter extends RecyclerView.Adapter<ResultViewHolder>
        implements ResultViewHolder.OnItemClickListener {

    private static int VT_COLLAPSED = 0;
    private static int VT_EXPANDED = 1;

    private List<Result> mResults = new ArrayList<>();
    private List<Integer> mExpandedIndexes = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        if (mExpandedIndexes.contains(position)) {
            return VT_EXPANDED;
        }
        return VT_COLLAPSED;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ResultViewHolder vViewHolder;
        if (viewType == VT_EXPANDED) {
            vViewHolder = new ResultExpandedViewHolder(parent);
        } else {
            vViewHolder = new ResultViewHolder(parent);
        }
        vViewHolder.setOnItemClickListener(this);
        return vViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.bind(mResults.get(position));
    }

    @Override
    public int getItemCount() {
        return mResults != null ? mResults.size() : 0;
    }

    @Override
    public void onItemClick(int position) {
        if (mExpandedIndexes.contains(position)) {
            mExpandedIndexes.remove(Integer.valueOf(position));
        } else {
            mExpandedIndexes.add(Integer.valueOf(position));
        }
        notifyItemChanged(position);
    }

    public void setResults(List<Result> results) {
        mResults = results;
        notifyDataSetChanged();
    }
}
