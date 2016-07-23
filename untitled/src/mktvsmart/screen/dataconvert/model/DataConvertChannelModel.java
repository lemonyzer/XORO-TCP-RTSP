package mktvsmart.screen.dataconvert.model;

import javafx.scene.control.ListCell;

import javax.swing.*;
import java.awt.*;

public class DataConvertChannelModel implements java.io.Serializable {
    final private static long serialVersionUID = 1831321093741944842L;
    private int FavMark;
    private int LockMark;
    private String ProgramId;
    private int ProgramIndex;
    private String ProgramName;
    private String SatName;
    private String audioPid;
    private int channelType;
    private int fec;
    private int freq;
    private int haveEPG;
    private int isPlaying;
    private int isProgramHd;
    private int isProgramScramble;
    private boolean isSelected;
    private short isTuner2;
    private int mCurrentChannelListDispIndex;
    private int[] mMatchChannelNameIndexArray;
    private int mSearchChannelSortPriority;
    private int mWillBePlayed;
    public java.util.List mfavGroupIDs;
    private int modulationSystem;
    private int modulationType;
    private String moveToPosition;
    private int pilotTones;
    private int pmtPid;
    private char pol;
    private int rollOff;
    private String subPid;
    private int symRate;
    private int ttxPid;
    private int videoPid;

    public String toString() {
        return ProgramIndex + " " + ProgramName;
    }

    public DataConvertChannelModel()
    {
        super();
        this.isSelected = false;
        this.mfavGroupIDs = (java.util.List)(Object)new java.util.ArrayList();
    }

    public int GetFavMark()
    {
        return this.FavMark;
    }

    public int GetHaveEPG()
    {
        return this.haveEPG;
    }

    public int GetIsProgramScramble()
    {
        return this.isProgramScramble;
    }

    public String GetProgramId()
    {
        return this.ProgramId;
    }

    public int GetProgramIndex()
    {
        return this.ProgramIndex;
    }

    public String GetSatName()
    {
        return this.SatName;
    }

    public void SetFavMark(int i)
    {
        this.FavMark = i;
    }

    public void SetHaveEPG(int i)
    {
        this.haveEPG = i;
    }

    public void SetIsProgramScramble(int i)
    {
        this.isProgramScramble = i;
    }

    public void SetProgramId(String s)
    {
        this.ProgramId = s;
    }

    public void SetProgramIndex(int i)
    {
        this.ProgramIndex = i;
    }

    public void SetSatName(String s)
    {
        this.SatName = s;
    }

    public String getAudioPid()
    {
        return this.audioPid;
    }

    public int getChannelTpye()
    {
        return this.channelType;
    }

    public int getCurrentChannelListDispIndex()
    {
        return this.mCurrentChannelListDispIndex;
    }

    public int getFec()
    {
        return this.fec;
    }

    public int getFreq()
    {
        return this.freq;
    }

    public int getIsPlaying()
    {
        return this.isPlaying;
    }

    public int getIsProgramHd()
    {
        return this.isProgramHd;
    }

    public short getIsTuner2()
    {
        int i = this.isTuner2;
        return (short)i;
    }

    public int getLockMark()
    {
        return this.LockMark;
    }

    public int[] getMatchChannelNameIndexArray()
    {
        return this.mMatchChannelNameIndexArray;
    }

    public int getModulationSystem()
    {
        return this.modulationSystem;
    }

    public int getModulationType()
    {
        return this.modulationType;
    }

    public String getMoveToPosition()
    {
        return this.moveToPosition;
    }

    public int getPilotTones()
    {
        return this.pilotTones;
    }

    public int getPmtPid()
    {
        return this.pmtPid;
    }

    public char getPol()
    {
        int i = this.pol;
        return (char)i;
    }

    public String getProgramName()
    {
        return this.ProgramName;
    }

    public int getRollOff()
    {
        return this.rollOff;
    }

    public int getSearchChannelSortPriority()
    {
        return this.mSearchChannelSortPriority;
    }

    public boolean getSelectedFlag()
    {
        return this.isSelected;
    }

    public String getSubPid()
    {
        return this.subPid;
    }

    public int getSymRate()
    {
        return this.symRate;
    }

    public int getTtxPid()
    {
        return this.ttxPid;
    }

    public int getVideoPid()
    {
        return this.videoPid;
    }

    public int getmWillBePlayed()
    {
        return this.mWillBePlayed;
    }

    public void setAudioPid(String s)
    {
        this.audioPid = s;
    }

    public void setChannelTpye(int i)
    {
        this.channelType = i;
    }

    public void setCurrentChannelListDispIndex(int i)
    {
        this.mCurrentChannelListDispIndex = i;
    }

    public void setFec(int i)
    {
        this.fec = i;
    }

    public void setFreq(int i)
    {
        this.freq = i;
    }

    public void setIsPlaying(int i)
    {
        this.isPlaying = i;
    }

    public void setIsProgramHd(int i)
    {
        this.isProgramHd = i;
    }

    public void setIsTuner2(short a)
    {
        this.isTuner2 = a;
    }

    public void setLockMark(int i)
    {
        this.LockMark = i;
    }

    public void setMatchChannelNameIndexArray(int[] a)
    {
        this.mMatchChannelNameIndexArray = a;
    }

    public void setModulationSystem(int i)
    {
        this.modulationSystem = i;
    }

    public void setModulationType(int i)
    {
        this.modulationType = i;
    }

    public void setMoveToPosition(String s)
    {
        this.moveToPosition = s;
    }

    public void setPilotTones(int i)
    {
        this.pilotTones = i;
    }

    public void setPmtPid(int i)
    {
        this.pmtPid = i;
    }

    public void setPol(char a)
    {
        this.pol = a;
    }

    public void setProgramName(String s)
    {
        this.ProgramName = s;
    }

    public void setRollOff(int i)
    {
        this.rollOff = i;
    }

    public void setSearchChannelSortPriority(int i)
    {
        this.mSearchChannelSortPriority = i;
    }

    public void setSelectedFlag(boolean b)
    {
        this.isSelected = b;
    }

    public void setSubPid(String s)
    {
        this.subPid = s;
    }

    public void setSymRate(int i)
    {
        this.symRate = i;
    }

    public void setTtxPid(int i)
    {
        this.ttxPid = i;
    }

    public void setVideoPid(int i)
    {
        this.videoPid = i;
    }

    public void setmWillBePlayed(int i)
    {
        this.mWillBePlayed = i;
    }
}
