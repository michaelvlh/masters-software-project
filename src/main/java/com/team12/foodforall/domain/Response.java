package com.team12.foodforall.domain;

import lombok.*;

/**
 * @author: Heng Gao
 * @date: 08/03/2022 :22:49
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Response<T> {
    private int status;
    private T data;
}
