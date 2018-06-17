package it.crispybacon.mundial1x2.sections.home;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import it.crispybacon.mundial1x2.R;
import it.crispybacon.mundial1x2.core.apimodels.Match;
import it.crispybacon.mundial1x2.ui.imageview.FlagImageView;

/**
 * Created by itscap on 15/06/2018.
 */

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchViewHolder> {

    private List<Match> mMatches;

    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    private SimpleDateFormat vTimeDateFormat = new SimpleDateFormat("HH:mm", Locale.ITALY);
    private SimpleDateFormat vDayDateFormat = new SimpleDateFormat("EEE", Locale.ITALY);
    private SimpleDateFormat vDateDateFormat = new SimpleDateFormat("dd/MM", Locale.ITALY);

    public MatchesAdapter(Context aContext) {
        mContext = aContext;
    }

    public interface OnItemClickListener {
        void onMatchClicked(Match aMatch);
    }


    class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private FlagImageView mFlagImageLeft;
        private FlagImageView mFlagImageRight;
        private AppCompatTextView mDateTextView;
        private AppCompatTextView mHourTextView;

        public MatchViewHolder(View itemView) {
            super(itemView);

            mFlagImageLeft = itemView.findViewById(R.id.img_left_team);
            mFlagImageRight = itemView.findViewById(R.id.img_right_team);
            mDateTextView = itemView.findViewById(R.id.text_date);
            mHourTextView = itemView.findViewById(R.id.text_hour);

        }

        public void bind(Match aMatch) {
            mFlagImageLeft.withFlag(R.drawable.flag_russia)
                    .withText(aMatch.team1.name);

            mFlagImageRight.withFlag(R.drawable.flag_russia)
                    .withText(aMatch.team2.name);

            mDateTextView.setText(
                    new StringBuilder()
                            .append(vDayDateFormat.format(aMatch.date))
                            .append("\n")
                            .append(vDateDateFormat.format(aMatch.date))
                            .toString()
                            .toUpperCase()
            );
            mHourTextView.setText(vTimeDateFormat.format(aMatch.date));
        }

        @Override
        public void onClick(View view) {
            if (null != mOnItemClickListener)
                mOnItemClickListener.onMatchClicked(mMatches.get(getAdapterPosition()));
        }
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MatchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.column_match, parent, false));
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, final int position) {
        holder.bind(mMatches.get(position));
    }

    @Override
    public int getItemCount() {
        return null != mMatches ? mMatches.size() : 0;
    }

    public Match getMatch(final int position) {
        if (position >= 0 && position < mMatches.size()) {
            return mMatches.get(position);
        }
        return null;
    }

    public void updateData(List<Match> aMatches) {
        if (aMatches != null) {
            mMatches = aMatches;
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener aOnItemClickListener) {
        mOnItemClickListener = aOnItemClickListener;
    }
}
