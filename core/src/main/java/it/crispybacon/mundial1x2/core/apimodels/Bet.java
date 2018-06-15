package it.crispybacon.mundial1x2.core.apimodels;

/**
 * Created by Jameido on 15/06/2018.
 */
public class Bet {
    public Match match;
    public BetResult result;

    public Bet(Match match, BetResult result) {
        this.match = match;
        this.result = result;
    }

    public enum BetResult {
        HOME("1"),
        TIE("X"),
        AWAY("2");

        private final String string;

        /**
         * @param string
         */
        BetResult(final String string) {
            this.string = string;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return string;
        }
    }
}
