package com.cd.consumeanapitest.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NasaPicture {

    private String date;
    private String explanation;
    private String media_type;
    private String title;
    private String url;
}
