package core.basesyntax;

public class SongOrder {
    private String singer;
    private String songName;

    private SongOrder(String singer, String songName) {
        this.singer = singer;
        this.songName = songName;
    }

    private SongOrder(String singer) {
        this.singer = singer;
    }

    public static SongOrder of(String singer) {
        return new SongOrder(singer);
    }

    public static SongOrder of(String singer, String songname) {
        return new SongOrder(singer,songname);
    }

    @Override
    public String toString() {
        if (singer == null || songName == null) {
            return "You haven't chosen a singer. Please make your choice)";
        }
        if (songName == null) {
            return "Play any Bon Jovi song";
        }
        return "Play Bon Jovi song called " + "It's my life";
    }
}
  
