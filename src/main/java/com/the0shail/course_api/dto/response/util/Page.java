package com.the0shail.course_api.dto.response.util;

import java.util.List;

public record Page<T>(
        List<T> content,
        Integer number,
        Integer size,
        Long totalElements,
        Integer totalPages
) {
    public static <T> Page<T> from(org.springframework.data.domain.Page<T> page) {
        return new Page<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
