package com.the0shail.course_api.mapper.helper;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FormatHelper {

    @Named("truncate200")
    public String truncate200(String value) {
        if (value == null) return null;
        return value.length() <= 200 ? value : value.substring(0, 200);
    }

    @Named("formatPrice")
    public String formatPrice(BigDecimal price) {
        return price == null ? null : price.toPlainString();
    }
}
