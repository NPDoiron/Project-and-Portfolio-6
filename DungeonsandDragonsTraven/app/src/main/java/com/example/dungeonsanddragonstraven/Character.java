package com.example.dungeonsanddragonstraven;

import java.io.Serializable;

public class Character implements Serializable {

    public String bitmapString;
    public String race;
    public String raceDesc;
    public String classString;
    public String classDesc;
    public int str;
    public int dex;
    public int con;
    public int charInt;
    public int wis;
    public int charis;
    public String background;
    public String backgroundDesc;
    public String skillProficOne;
    public String skillProficTwo;
    public String exoticLanguage;
    public String backgroundFeat;
    public String alignment;
    public String faith;
    public String lifeStyle;
    public String hair;
    public String skin;
    public String eyes;
    public String height;
    public String weight;
    public String age;
    public String gender;
    public String characterName;

    public Character(String _bitmapString, String _race, String _raceDesc, String _classString, String _classDesc, int _str,
                     int _dex, int _con, int _charInt, int _wis, int _charis, String _background, String _backgroundDesc,
                     String _skillProficOne, String _skillProficTwo, String _exoticLang, String _backgroundFeat,
                     String _alignment, String _faith, String _lifeStyle, String _hair, String _skin, String _eyes, String _height, String _weight,
                     String _age, String _gender, String _characterName) {
        bitmapString = _bitmapString;
        race = _race;
        raceDesc = _raceDesc;
        classString = _classString;
        classDesc = _classDesc;
        str = _str;
        dex = _dex;
        con = _con;
        charInt = _charInt;
        wis = _wis;
        charis = _charis;
        background = _background;
        backgroundDesc = _backgroundDesc;
        skillProficOne = _skillProficOne;
        skillProficTwo = _skillProficTwo;
        exoticLanguage = _exoticLang;
        backgroundFeat = _backgroundFeat;
        alignment = _alignment;
        faith = _faith;
        lifeStyle = _lifeStyle;
        hair = _hair;
        skin = _skin;
        eyes = _eyes;
        height = _height;
        weight = _weight;
        age = _age;
        gender = _gender;
        characterName = _characterName;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setBackgroundDesc(String backgroundDesc) {
        this.backgroundDesc = backgroundDesc;
    }

    public void setBackgroundFeat(String backgroundFeat) {
        this.backgroundFeat = backgroundFeat;
    }

    public void setBitmapString(String bitmapString) {
        this.bitmapString = bitmapString;
    }

    public void setCharInt(int charInt) {
        this.charInt = charInt;
    }

    public void setCharis(int charis) {
        this.charis = charis;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public void setClassString(String classString) {
        this.classString = classString;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setExoticLanguage(String exoticLanguage) {
        this.exoticLanguage = exoticLanguage;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setRaceDesc(String raceDesc) {
        this.raceDesc = raceDesc;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSkillProficOne(String skillProficOne) {
        this.skillProficOne = skillProficOne;
    }

    public void setSkillProficTwo(String skillProficTwo) {
        this.skillProficTwo = skillProficTwo;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public void setFaith(String faith) {
        this.faith = faith;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setLifeStyle(String lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
