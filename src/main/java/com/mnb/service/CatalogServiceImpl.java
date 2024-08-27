package com.mnb.service;

import com.mnb.entity.Catalog;
import com.mnb.exception.NotFoundException;
import com.mnb.repository.CatalogRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatalogServiceImpl implements  CatalogService{
    final CatalogRepository catalogRepository;

    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }

    @Override
    public Catalog findById(int theId) {
       /* Optional<Catalog> result = publisherRepository.findById(theId);

        Catalog thePublisher = null;

        if (result.isPresent()) {
            thePublisher = result.get();
        }
        else {
            // we didn't find the publisher
            throw new RuntimeException("Did not find publisher id - " + theId);
        }

        return thePublisher;

 */
         return catalogRepository.findById(theId)
                .orElseThrow(() -> new NotFoundException(String.format("Catalog not found  with ID %d", theId)));
    }


    @Override
    public void save(Catalog theCatalog) {
        catalogRepository.save(theCatalog);
    }

    @Override
    public void deleteById(int theId) {
        catalogRepository.deleteById(theId);
    }
}
