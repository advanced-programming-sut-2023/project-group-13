package model;

public enum SoldierImages {
    ARABIAN_SWORDSMAN("/images/game/map/troops/arabianSwordsman.png"),
    ARCHER("/images/game/map/troops/archer.png"),
    ARCHER_BOW("/images/game/map/troops/archerBow.png"),
    BLACK_MONK("/images/game/map/troops/blackmonk.png"),
    CROSS_BOW_MAN("/images/game/map/troops/crossbowman.png"),
    FIRE_THROWER("/images/game/map/troops/fireThrower.png"),
    HORSE_ARCHER("/images/game/map/troops/horseArcher.png"),
    KNIGHT("/images/game/map/troops/knight.png"),
    LADDER_MAN("/images/game/map/troops/ladderman.png"),
    MACE_MAN("/images/game/map/troops/maceman.png"),
    PIKE_MAN("/images/game/map/troops/pikeman.png"),
    SLAVE("/images/game/map/troops/slave.png"),
    SLINGER("/images/game/map/troops/slinger.png"),
    SPEAR_MAN("/images/game/map/troops/spearman.png"),
    SWORDS_MAN("/images/game/map/troops/swordsman.png")
    ;
   private String filePath;

    SoldierImages(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
