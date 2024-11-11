package com.medhead.webapp.model;

import lombok.Data;

@Data
public class AjaxResponseBodySpecialite {
    String msg;
    Iterable<Specialite> result;
}
