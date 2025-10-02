package com.crni99.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crni99.bookstore.model.FeedBack;
import com.crni99.bookstore.repository.FeedBackRepository;

@Service
public class FeedBackService {

    @Autowired
    FeedBackRepository feedBackRepository;

    public void feedBackService(FeedBack feedBack) {
        feedBackRepository.save(feedBack);
    }

    public List<FeedBack> showFeedBack() {
        List<FeedBack> list = (List<FeedBack>) feedBackRepository.findAll();
        return list;

    }

}
