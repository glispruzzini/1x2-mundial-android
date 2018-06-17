package it.crispybacon.mundial1x2.sections.results;

import android.graphics.BlurMaskFilter;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Locale;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.apimodels.Bet;
import it.crispybacon.mundial1x2.ui.imageview.FlagImageView;

/**
 * Created by Jameido on 15/06/2018.
 */
public class ResultViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    protected FlagImageView mFlagHome;
    protected FlagImageView mFlagAway;
    protected AppCompatTextView mTextDate;
    protected AppCompatTextView mTextScore;

    private SimpleDateFormat vDateDateFormat = new SimpleDateFormat("EEE dd/MM", Locale.ITALY);

    private OnItemClickListener mOnItemClickListener = null;

    public ResultViewHolder(View itemView) {
        super(itemView);
        mFlagHome = itemView.findViewById(R.id.flag_team_home);
        mFlagAway = itemView.findViewById(R.id.flag_team_away);
        mTextDate = itemView.findViewById(R.id.text_date);
        mTextScore = itemView.findViewById(R.id.text_score);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    @CallSuper
    protected void bind(Bet result) {
        mFlagHome.withText(result.match.team1.code);
        mFlagAway.withText(result.match.team2.code);

        mTextScore.setText(itemView.getContext()
                .getString(R.string.score_format, result.match.score1, result.match.score2)
        );

        mTextDate.setText(vDateDateFormat.format(result.match.date).toUpperCase());
    }

    protected void blurScore() {
        if (Build.VERSION.SDK_INT >= 11) {
            mTextScore.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        float radius = mTextScore.getTextSize() / 3;
        BlurMaskFilter filter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL);
        mTextScore.getPaint().setMaskFilter(filter);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }
}
