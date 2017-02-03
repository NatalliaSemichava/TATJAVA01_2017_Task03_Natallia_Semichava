import com.company.catalog.bean.*;
import com.company.catalog.dao.NewsDAO;
import com.company.catalog.dao.exception.DAOException;
import com.company.catalog.dao.factory.DAOFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class tstFileNewsDAO {

    @DataProvider(name = "simpleDataProvider")
    public Object[][] createSomeData()
    {
        return new Object[][]
                {
                        { new Movie("Byron Howard","108"), "11.02.2016", "fairy-tail", "USA", "Zootopia (also known as Zootropolis in most of Europe and the Middle East) is a 2016 American 3D computer-animated comedy-adventure film[6] produced by Walt Disney Animation Studios and released by Walt Disney Pictures."},
                        { new Album("Metallica","62:31"), "1991", "Heavy metal", "USA", "Metallica (commonly known as The Black Album) is the eponymous fifth studio album by American heavy metal band Metallica."},
                        { new Book("Mikhail Bulgakov","YMCA Press"), "1967", "Fantastic, farce, mysticism, romance, satire, Modernist literature", "Soviet Union", "The Master and Margarita (Russian: Ма́стер и Маргари́та) is a novel by Mikhail Bulgakov, written between 1928 and 1940, but unpublished in book form until 1967. The story concerns a visit by the devil to the fervently atheistic Soviet Union. Many critics consider it to be one of the best novels of the 20th century, as well as the foremost of Soviet satires."},
                        { new Book("",""), "", "", "", ""}
                };
    }

    @Test(dataProvider = "simpleDataProvider")
    public void tstAddNews(News news, String date, String genre, String country, String description) throws DAOException{
        news.setCountry(country);
        news.setDate(date);
        news.setGenre(genre);
        news.setDescription(description);
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
        Assert.assertTrue(newsDAO.addNewsInFile(news));
    }

    @Test(dataProvider = "simpleDataProvider")
    public void tstReadNewsFromFile(News record, String date, String genre, String country, String description) throws DAOException{
        int i=0;
        DAOFactory daoObjectFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoObjectFactory.getNewsDAO();
        HashMap<News, String> news = newsDAO.readAllNewsInFile();
        record.setCountry(country);
        record.setDate(date);
        record.setGenre(genre);
        record.setDescription(description);
        for (News n: news.keySet()){
            if ((n.getDate().equals(record.getDate())) && (n.getCountry().equals(record.getCountry())) && (n.getGenres().equals(record.getGenres())) &&(n.getDescription().equals(record.getDescription()))){
                i++;
                break;
            }
        }
        Assert.assertEquals(1,i);
    }
}
