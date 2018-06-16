package it.crispybacon.mundial1x2.sections.score;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.ui.imageview.FlagImageView;
import it.crispybacon.mundial1x2.ui.text.DateTextView;

/**
 * Created by itscap on 16/06/2018.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private List<Score> mScores;

    private OnItemClickListener mOnItemClickListener;
    private Context mContext;

    public ScoreAdapter(Context aContext) {
        mContext = aContext;
    }

    public interface OnItemClickListener {
        void onScoreClicked(Score aScore);
    }



    class ScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private FlagImageView mFlagImageLeft;
        private FlagImageView mFlagImageRight;
        private DateTextView mDateTextView;
        private AppCompatTextView mHourTextView;

        public ScoreViewHolder(View itemView) {
            super(itemView);

            mFlagImageLeft = itemView.findViewById(R.id.img_left_team);
            mFlagImageRight = itemView.findViewById(R.id.img_right_team);
            mDateTextView = itemView.findViewById(R.id.text_date);
            mHourTextView = itemView.findViewById(R.id.text_hour);

        }

        public void bind(Score aScore) {



        }

        @Override
        public void onClick(View view) {
            if (null != mOnItemClickListener)
                mOnItemClickListener.onScoreClicked(mScores.get(getAdapterPosition()));
        }
    }


    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScoreViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_score, parent, false));
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, final int position) {
        holder.bind(mScores.get(position));
    }

    @Override
    public int getItemCount() {
        return null != mScores ? mScores.size() : 0;
    }

    public void updateData(List<Score> aScores) {

        if (aScores != null) {
            mScores = aScores;
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener aOnItemClickListener) {
        mOnItemClickListener = aOnItemClickListener;
    }


}
