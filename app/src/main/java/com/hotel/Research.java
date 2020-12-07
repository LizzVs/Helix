package com.hotel;

public class Research {
    private int id_research;
  //  private String name;
    private String status;

    public Research(int id_research, String status){
        this.id_research = id_research;
        this.status = status;
    }

    public int getId_research() {
        return id_research;
    }

  //  public String getName() {return name;}

    public String getStatus() {
        return status;
    }
}
