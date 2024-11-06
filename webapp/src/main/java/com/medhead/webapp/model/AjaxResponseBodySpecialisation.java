package com.medhead.webapp.model;

import lombok.Data;

@Data
public class AjaxResponseBodySpecialisation {
    String msg;
    Iterable<Specialisation> result;
}
