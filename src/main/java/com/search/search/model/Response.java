package com.search.search.model;

import java.util.List;

public class Response {
    List<ResponseItem> responseItemList;

    public Response() {
    }

    public Response(List<ResponseItem> responseItemList) {
        this.responseItemList = responseItemList;
    }

    public List<ResponseItem> getResponseItemList() {
        return responseItemList;
    }

    public void setResponseItemList(List<ResponseItem> responseItemList) {
        this.responseItemList = responseItemList;
    }


}
