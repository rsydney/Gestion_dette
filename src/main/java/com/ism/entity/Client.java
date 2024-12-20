package com.ism.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@Getter
@NoArgsConstructor
public class Client {
    private int id; // Ajout d'un identifiant pour le Client
    private String surname;
    private String phone;
    private String address;
    private User account; 
    private double totalDebt; 

    private User user;

    public Client(int id, String surname, String phone, String address) {
        this.id = id; 
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public Client(int id, String surname, String phone, String address, User account, double totalDebt) {
        this.id = id; 
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        this.account = account;
        this.totalDebt = totalDebt;
    }
    public Client(String surname, String phone, String address, String email, String login, String password) {
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        long temp;
        temp = Double.doubleToLongBits(totalDebt);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id != other.id)
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (Double.doubleToLongBits(totalDebt) != Double.doubleToLongBits(other.totalDebt))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    
}
