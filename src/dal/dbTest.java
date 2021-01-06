package dal;

import java.io.IOException;
import java.sql.SQLException;

public class dbTest {








    public static void main(String[] args) throws SQLException, IOException {
        genreDAL myGenreDAL = new genreDAL();

        myGenreDAL.addGenre("Disco");

    }

}


