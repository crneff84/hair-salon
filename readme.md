# _Hair Salon Application_

#### _This application allows a hair salon to keep track of its stylists and their respective clients. 09/23/2016_

#### By _Chance Neff_

## Description and Specifications

_This application allows a hair salon to store and access their current staff and their respective clients. The user may add/update/delete stylists or clients._

* _Stylist is added to directory_
  * _Example Input: Stylist1_
  * _Example Output: Stylist1 is stored in database_

* _Client is added to the directory for a specific stylist_
  * _Example Input: Client1_
  * _Example Output: Client1 for a specific stylist is stored in database_

## Setup/Installation Requirements

* _This program can best be used with the Google Chrome browser._

* __PSQL Commands to initialize database__
* __In PSQL:__
* __CREATE DATABASE hair_salon;__
* __CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, specialty varchar);__
* __CREATE TABLE clients (id serial PRIMARY KEY, name varchar, stylistId varchar);__

## Known Bugs

_There are no known bugs at this time._

## Support and contact details

_If you encounter any bugs, please contact me at crneff84@gmail.com_

## Technologies Used

_This website was created using java, gradle, postgresql, and Spark._

### License

*MIT*

Copyright (c) 2016 **_Chance Neff_**
