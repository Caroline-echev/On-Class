package com.bootcamp.onclass.domain.api;

import com.bootcamp.onclass.domain.model.Version;

import java.util.List;

public interface IVersionServicePort {

    Version addVersion(Version version);
    List<Version> getAllVersionByBootcamp(Long bootcampId, Integer page, Integer size, boolean orderAsc, String orderType);

}
