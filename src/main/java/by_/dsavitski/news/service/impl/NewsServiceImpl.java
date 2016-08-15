package by_.dsavitski.news.service.impl;

import by_.dsavitski.news.entity.News;
import by_.dsavitski.news.repository.NewsRepository;
import by_.dsavitski.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * News service implementation.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    /**
     * Returns news Page by given Pageable object.
     * If number of given page exceeds total pages number,
     * then first page returned.
     */
    @Override
    public Page<News> getPage(Pageable pageable) {
        final Page<News> page = newsRepository.findAll(pageable);
        if (!page.isFirst() && page.getNumberOfElements() == 0) {
            return newsRepository.findAll(pageable.first());
        } else {
            return page;
        }
    }

    /**
     * Returns news object by given id.
     */
    @Override
    public News findOne(int id) {
        return newsRepository.findOne(id);
    }

    /**
     * Persists given news object.
     */
    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }
}
