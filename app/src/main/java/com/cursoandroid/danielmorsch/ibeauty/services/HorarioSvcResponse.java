package com.cursoandroid.danielmorsch.ibeauty.services;

import com.cursoandroid.danielmorsch.ibeauty.domain.Horario;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class HorarioSvcResponse {

    public Horario[] content;

    @JsonIgnore
    private Object pageable; //	{…}

    private int totalPages; //	3
    private int totalElements; //	70
    private boolean last; //	private int false; //
    private int size; //	30
    private int number; //	0

    @JsonIgnore
    private Object sort; //	{…}

    private boolean first; //	private int true; //
    private int numberOfElements; //	30
    private boolean empty; //	private int false; //


    public Horario[] getContent() {
        return content;
    }

    public void setContent(Horario[] content) {
        this.content = content;
    }

    public Object getPageable() {
        return pageable;
    }

    public void setPageable(Object pageable) {
        this.pageable = pageable;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
