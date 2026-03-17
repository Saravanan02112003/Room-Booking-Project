package PG.model;

import java.sql.Date;

public class Booking {

    private int id;
    private int roomId;
    private String customerName;
    private String email;

    private Date checkInDate;
    private Date checkOutDate;

    private boolean confirmed;

    // File upload fields
    private String fileName;
    private String fileType;
    private String fileData;

    public Booking() {}

    public Booking(int id, int roomId, String customerName, String email,
                   Date checkInDate, Date checkOutDate,
                   boolean confirmed,
                   String fileName, String fileType, String fileData) {

        this.id = id;
        this.roomId = roomId;
        this.customerName = customerName;
        this.email = email;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.confirmed = confirmed;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
    }

    // Getter and Setter Methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }


    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }


    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }
}