package it.ispw.booknook.logic.control;

import it.ispw.booknook.logic.bean.BookBean;
import it.ispw.booknook.logic.bean.LibraryBean;
import it.ispw.booknook.logic.boundary.JSONManager;
import it.ispw.booknook.logic.database.dao.BookDao;
import it.ispw.booknook.logic.database.dao.LibraryDao;
import it.ispw.booknook.logic.entity.Book;
import it.ispw.booknook.logic.entity.Library;
import javafx.scene.image.Image;
import org.json.JSONObject;

import java.util.*;

public class BorrowBookController {


    public List<BookBean> borrowByName(BookBean requestedBook) {
        List<BookBean> bookBeans = new ArrayList<BookBean>();
        List<Book> bookList = BookDao.getRequestedBooks(requestedBook.getTitle());
        for (int i = 0; i < bookList.size(); i++) {
            BookBean bookBean = new BookBean();
            bookBean.setBookDetails(bookList.get(i));
            bookBeans.add(bookBean);
        }

        return bookBeans;
    }

    //qui dentro ci sta la get del file json con l'immagine
    public List<BookBean> processRequestedBook(BookBean selectedBook) {
        //ritorna le informazioni sul libro selezionato (titolo, autore, immagine, tag)
        Book book = BookDao.getSelectedBookDetails(selectedBook.getTitle(), selectedBook.getAuthor());
        //recupera l'immagine della copertina del libro

        BookBean bookBean = new BookBean(book);

        List<BookBean> related = calculateBookList(book.getTags(), book.getIsbn());

        related.add(0, bookBean);  //aggiunge il libro selezionato alla lista

        related.forEach(bookBean1 -> {
            bookBean1.setTags(BookDao.getTagsByISBN(bookBean1.getIsbn()));
            JSONObject myResponse = JSONManager.getJsonFromUrl(bookBean1.getIsbn());
            String url = JSONManager.getImageURL(myResponse);
            bookBean1.setCover(url);
            bookBean1.setCoverImage(new Image(url));
        });

        return related;
    }

    //recupera la lista dei libri simili a quello selezionato
    private List<BookBean> calculateBookList(List<String> tags, String isbn) {
        List<Book> books = BookDao.getRelatedBooks(tags, isbn);
        //continua qui!
        return sortByOccurrences(books);
    }

    private List<BookBean> sortByOccurrences(List<Book> books) {
        HashMap<String, List<Book>> map = new HashMap<>();

        for (Book book : books) {
            List<Book> booksWithSameISBN = map.get(book.getIsbn());
            if (booksWithSameISBN == null) { //does not exist in map yet
                booksWithSameISBN = new ArrayList<Book>();
                map.put(book.getIsbn(), booksWithSameISBN);
            }
            booksWithSameISBN.add(book); //now add the item to the list for this key
        }

        class Data implements Comparable<Data> {

            private Book book;
            private int occurrences;

            public Data(Book book, int occurrences) {
                this.book = book;
                this.occurrences = occurrences;
            }

            public Book getBook() {
                return this.book;
            }

            @Override
            public int compareTo(Data o) {
                return o.getOccurrences() - this.occurrences;
            }

            public int getOccurrences() {
                return this.occurrences;
            }

            @Override
            public String toString() {
                return "Data{"+this.book.getIsbn()+"}";
            }
        }

        List<Data> sorted = new ArrayList<>();

        for (String key : map.keySet()) {
            List<Book> listOfElementsWithSameKey = map.get(key);
            int size = listOfElementsWithSameKey.size();  //numero di occorrenze di un libro
            Book book = listOfElementsWithSameKey.get(0);

            Data data = new Data(book, size);
            sorted.add(data);
        }

        Collections.sort(sorted);  //ordina per numero di occorrenze (ossia numero di tag uguali a quelli del libro selezionato)

        List<BookBean> bookBeans = new ArrayList<>();  //lista con libri ordinati per affinità
        sorted.forEach(book -> bookBeans.add(new BookBean(book.getBook())));

        return bookBeans;
    }

    //recupera la lista di biblioteche con disponibilità del libro richiesto
    public List<LibraryBean> calculateLibraries(BookBean book) {
        Map<String, Library> libraries = LibraryDao.getLibrariesByISBN(book.getIsbn());
        List<LibraryBean> libraryList = new ArrayList<LibraryBean>();
        libraries.forEach((name, library) -> {
            LibraryBean bean = new LibraryBean(library);
            bean.setAvailability(library.getAvailability(book.getIsbn()));
            libraryList.add(bean);
            /* library.getOwnedCopies().forEach(copy-> System.out.println("copia: "+ copy.getId() + "della bilbioteca " + copy.getLibrary().getName()));
            System.out.println(library.getAvailability(book.getIsbn())); */
        });

        return libraryList;
    }



}
