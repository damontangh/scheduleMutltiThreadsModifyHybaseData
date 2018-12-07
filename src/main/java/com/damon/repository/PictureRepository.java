package com.damon.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;


/**
 * Created by HongDanyang on 15/11/18.
 */
@Transactional
public interface PictureRepository extends PagingAndSortingRepository<Picture, Long>,JpaSpecificationExecutor<Picture> {

}