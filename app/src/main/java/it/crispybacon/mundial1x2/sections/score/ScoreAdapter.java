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
import it.crispybacon.mundial1x2.ui.imageview.RoundedImageView;
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

        private AppCompatTextView mTextPos;
        private AppCompatTextView mTextName;
        private AppCompatTextView mTextScore;
        private RoundedImageView mProfilePic;

        public ScoreViewHolder(View itemView) {
            super(itemView);

            mTextPos = itemView.findViewById(R.id.text_pos);
            mProfilePic = itemView.findViewById(R.id.img_profile);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextScore = itemView.findViewById(R.id.text_score);

        }

        public void bind(Score aScore) {

        //TODO

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
