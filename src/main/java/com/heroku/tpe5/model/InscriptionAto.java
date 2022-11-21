package com.heroku.tpe5.model;


import com.sun.istack.NotNull;
import com.heroku.tpe5.domain.entity.InscriptionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "studentId", "careerId", "since", "status"})
@Builder
@EqualsAndHashCode
public class InscriptionAto {

    private Long id;

    @NotNull
    private Long studentId;

    @NotNull
    private Long careerId;

    @NotNull
    private LocalDateTime since;

    @NotNull
    private InscriptionStatus status;
}
