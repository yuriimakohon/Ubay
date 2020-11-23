# Ubay

Online web-auction with REST-API

<p>
  <img src="https://i.ibb.co/tD48Fpf/Screen-Shot-2020-11-23-at-4-30-02-PM.png" alt="photo">
</p>

## Installation
1. `git clone https://github.com/yuriimakohon/ubay`
2. Apply maven wrapper: `mvn -N io.takari:maven:wrapper`
3. Build: `./mvnw clean package`
4. Run server: `./wvnw tomcat7:run`
5. Check site: http://localhost:8080/

## Description

This web resource allows you to create your own online english auction.
Register as an Auctioneer to create and manage new item lots, or as a Bidder to participate in auctions and bid.

## API
REST:
* Get all auctions:
`/api/auction/`
* Auction:
`/api/auction/` **`auctionId`**
* Bid:
`/api/bid/` **`bidId`**
* Feedbacks for auction:
`/api/feedback/` **`auctionId`**
* All users:
`/api/user/`
* User:
`/api/user/` **`userId`**

## Technologies
* **ORM DB**:
    * Hibernate
    * MySQL
* **Server-side**: Java servlets
* **Client-side**:
    * js
    * jQuery
    * JWT
    * jsp
    * css
* **Build**: Maven wrapper

## Gallery
<p>
  <img src="https://i.ibb.co/dMpT0cc/Screen-Shot-2020-11-23-at-5-17-08-PM.png" alt="photo">
</p>
