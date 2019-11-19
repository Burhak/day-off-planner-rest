package com.evolveum.day_off_planner_rest.util

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

const val DEFAULT_PAGE_SIZE = 20
const val MAX_PAGE_SIZE = 100


fun pageable(pageNumber: Int?, pageSize: Int?): Pageable = PageRequest.of(
        maxOf(pageNumber ?: 0, 0),
        minOf(maxOf(pageSize ?: DEFAULT_PAGE_SIZE, 1), MAX_PAGE_SIZE)
)

fun pageable(pageNumber: Int?, pageSize: Int?, sort: Sort): Pageable = PageRequest.of(
        maxOf(pageNumber ?: 0, 0),
        minOf(maxOf(pageSize ?: DEFAULT_PAGE_SIZE, 1), MAX_PAGE_SIZE),
        sort
)