package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher wiley = new Publisher();
        wiley.setName("Wiley");
        wiley.setAddressLine1("111 River Street");
        wiley.setCity("Hoboken");
        wiley.setState("NJ");
        wiley.setZip("07030");

        publisherRepository.save(wiley);

        System.out.println("Publishers Count: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "9780321125217");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(wiley);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(wiley);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "9781280350368");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(wiley);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(wiley);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher's Number of Books: " + wiley.getBooks().size());
    }
}
