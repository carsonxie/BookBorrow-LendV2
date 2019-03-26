/*
 * Copyright 2019 TEAM19
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.example.y.BookEX;

import android.graphics.Bitmap;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Lender object  abstract class
 * contain all User related attributes
 * setter and getter for the Book info.
 * And firebase related methods include:
 * setToFirebase , deleteFromFirebase
 * <p>
 * <p>
 * Created  on 2/15/19.
 *
 * @see Borrower
 * @see Lender
 * @since 1.0
 */
public abstract class User {
    private String name;
    private Bitmap photo;
    private String password;
    private String phone;
    private String email;
    private String Uid;



    /**
     * A constructor with no parameters
     */
    User(){}


    /**
     * another constructor with parameters @param name the name
     *
     * @param name     the name
     * @param photo    the photo
     * @param password the password
     * @param phone    the phone
     * @param email    the email
     */
    User(String name, Bitmap photo, String password, String phone, String email){


        this.name = name;
        this.photo = photo;
        this.password =password;
        this.phone = phone;
        this.email = email;
    }


    /**
     * set Profile image of the User
     *
     * @param photo the photo
     */


    public void setPhoto(Bitmap photo) {

        this.photo = photo;
    }

    public void setToFirebase(){
        FirebaseDatabase m = FirebaseDatabase.getInstance();
        DatabaseReference r = m.getReference("users/"+this.Uid);
        r.child("name").setValue(this.name);
        r.child("password").setValue(this.password);
        r.child("phone").setValue(this.phone);
    }

    /**
     * set phone of the User
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * set the login password of the User
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * set the User name of the User
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * return the account password of the User
     *
     * @return password password
     */
    public String getPassword() {
        return password;
    }

    /**
     * return the phone number of the User
     *
     * @return phone phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * return the User name of the User
     *
     * @return name name
     */
    public String getName() {
        return name;
    }

    /**
     * return the profiole photo of the User
     *
     * @return photo photo
     */

    public Bitmap getPhoto() {

        return photo;
    }

    /**
     * return the rmail of the User
     *
     * @return User email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set the emai of the User
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set the unique id of User
     *
     * @param id the id
     */
    public void setUid(String id){this.Uid = id;}

    /**
     * return the id of the User
     *
     * @return Uid string
     */
    public String getUid(){
        return this.Uid;
    }







}
