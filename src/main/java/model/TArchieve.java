/*package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.time.LocalTime;

public class TArchieve {
    private long bid;
    private long pid;
    private LocalDate date;
    private LocalTime begin;
    private LocalTime end;
    private String description;
    private String remarks;
    private LocalDate archived_at;

    public TArchieve(long pid, LocalDate date, LocalTime begin, LocalTime end, String description, String remarks, LocalDate archived_at){
        this.pid = pid;
        this.archived_at = archived_at;
        this.date = date;
        this.end = end;
        this.begin = begin;
        this.description = description;
        this.remarks = remarks;
    }
    public TArchieve(long bid, long pid, LocalDate date, LocalTime begin, LocalTime end, String description, String remarks, LocalDate archived_at){
    this.bid = bid;
    this.pid = pid;
    this.archived_at = archived_at;
    this.date = date;
    this.end = end;
    this.begin = begin;
    this.description = description;
    this.remarks = remarks;
    }
    public long getBid() {
        return bid;
    }

    public long getPid() {
        return this.pid;
    }

    public String getDate() {
        return date.toString();
    }

    public String getBegin() {
        return begin.toString();
    }

    public String getEnd() {
        return end.toString();
    }
    public String getArchived_at(){return archived_at.toString();}

    public void setArchived_at(String archived_at){
        LocalDate archive = DateConverter.convertStringToLocalDate(archived_at);
        this.archived_at = archive;
    }
    public void setDate(String s_date) {
        LocalDate date = DateConverter.convertStringToLocalDate(s_date);
        this.date = date;
    }

    public void setBegin(String begin) {
        LocalTime time = DateConverter.convertStringToLocalTime(begin);
        this.begin = time;
    }

    public void setEnd(String end) {
        LocalTime time = DateConverter.convertStringToLocalTime(end);
        this.end = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}

 */
