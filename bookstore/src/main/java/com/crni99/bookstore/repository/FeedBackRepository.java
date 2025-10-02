package com.crni99.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crni99.bookstore.model.FeedBack;

@Repository
public interface FeedBackRepository extends CrudRepository<FeedBack, Integer> {

}
