package com.heroku.tpe5.model;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "name"})
@Builder
@EqualsAndHashCode
public class CareerAto {

    private Long id;

    @NotNull
    private String name;
}
