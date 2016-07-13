package mktvsmart.screen.dataconvert.model;

public class DataConvertTpModel
{
    private int fec;
    private int freq;
    private char pol;
    private int satIndex;
    private int symRate;
    private int tpIndex;

    public String toString() {
        return ""+tpIndex+" Pol="+pol;
    }

    public int getFec() {
        return this.fec;
    }

    public int getFreq() {
        return this.freq;
    }

    public char getPol() {
        return this.pol;
    }

    public int getSatIndex() {
        return this.satIndex;
    }

    public int getSymRate() {
        return this.symRate;
    }

    public int getTpIndex() {
        return this.tpIndex;
    }

    public void setFec(final int fec) {
        this.fec = fec;
    }

    public void setFreq(final int freq) {
        this.freq = freq;
    }

    public void setPol(final char pol) {
        this.pol = pol;
    }

    public void setSatIndex(final int satIndex) {
        this.satIndex = satIndex;
    }

    public void setSymRate(final int symRate) {
        this.symRate = symRate;
    }

    public void setTpIndex(final int tpIndex) {
        this.tpIndex = tpIndex;
    }
}
