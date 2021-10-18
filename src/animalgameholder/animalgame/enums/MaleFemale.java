package animalgameholder.animalgame.enums;

public enum MaleFemale{
    FEMALE, MALE;

    public static MaleFemale getRandomSelectGender(){  //Random select for male or female
        return values()[(int) (Math.random() * values().length)];
    }
}
