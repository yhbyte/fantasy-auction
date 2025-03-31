# 🐉 Dragon’s Den of Deals

**Fantasy auction platform for digital products & services**  
This project aims to create an online auction platform where users can list and bid on "magical items". In reality,
these items represent digital products or services like PDFs, templates, mentorship sessions, small freelance tasks,
etc. The goal is to build a functional Minimum Viable Product (MVP) within the 100-day timeframe.

---

## 🚀 Tech Stack

| Layer          | Technology                                               |
|----------------|----------------------------------------------------------|
| Backend        | Java 21+, Spring Boot 3, Spring Security, JPA, MapStruct |
| Database       | PostgreSQL, Flyway                                       |
| Messaging      | RabbitMQ (bidding events), Kafka (notifications/events)  |
| Frontend       | Thymeleaf+HTMX/React                                     |
| Testing        | JUnit 5, Testcontainers                                  |
| Infrastructure | Docker                                                   |

---

## 🌟 Project Vision

* **User Authentication:** Secure registration and login for users.
* **User registration & role management:** Use the role-based resource and operations access (user, seller, admin etc.).
* **Item Listing:** Sellers can list their digital items/services with descriptions, fantasy themes, starting prices,
  and auction durations.
* **Item Browse & Viewing:** Users can browse active auctions and view item details.
* **Bidding System:** Registered users can place bids on active items. Bids must be higher than the current highest bid.
* **Real-time Updates:** Near real-time updates for new bids and auction timers using HTMX.
* **Auction Lifecycle:** Automated closing of auctions when the end time is reached.
* **Winner Determination:** Identifying the highest bidder when an auction closes.
* **Asynchronous Notifications:** Using RabbitMQ/Kafka to notify users about being outbid or winning an auction.
* **User Dashboard:** Basic profile page showing items listed, bids placed, and items won.
* **Digital Item "Delivery":** Displaying the seller-provided information (link, instructions) to the winner after the
  auction concludes.

---