package it.crispybacon.mundial1x2.sections.results;

import android.view.View;
import it.crispybacon.mundial1x2.core.apimodels.Bet;

/**
 * Created by Jameido on 15/06/2018.
 */
public class ResultExpandedViewHolder extends ResultViewHolder {

    public ResultExpandedViewHolder(View itemView) {
        super(itemView);
    }

    protected void bind(Bet result) {
        super.bind(result);
        //TODO: fill with the rest of the data
    }

    @Override
    protected void blurScore() {
        //We don't need to blur scores when showing the expanded one
    }
}
