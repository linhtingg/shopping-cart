package com.mnb.service;

import com.mnb.entity.Catalog;

import java.util.List;

public interface CatalogService {
    public List<Catalog> findAll();

    public Catalog findById(int theId);

    public void save(Catalog theCatalog);

    public void deleteById(int theId);
}
