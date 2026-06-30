# 🚀 Distributed Cache System

> A distributed in-memory cache built with **Java** and **Spring Boot**, featuring **Consistent Hashing**, **Cluster-Aware Routing**, **Thread-Safe Storage**, and **Pluggable Eviction Policies**.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
![License](https://img.shields.io/badge/License-MIT-green)
![Status](https://img.shields.io/badge/Status-Active%20Development-yellow)

---

## 📖 Overview

Distributed Cache System is a scalable, in-memory caching platform inspired by systems like **Redis** and **Memcached**.

The project demonstrates backend engineering concepts such as:

- Distributed Systems
- Consistent Hashing
- Thread-Safe Data Structures
- Low-Latency Data Access
- Horizontal Scalability
- Cache Eviction Algorithms
- Modular Architecture

Unlike a simple key-value store, this project routes requests across multiple cache nodes using **Consistent Hashing**, minimizing key redistribution when nodes are added or removed.

---

# 🏗 Architecture

```
                   +--------------------+
                   |      Client        |
                   +---------+----------+
                             |
                             |
                    REST API Request
                             |
                             v
                 +----------------------+
                 | Cluster Controller   |
                 +----------+-----------+
                            |
                            |
                            v
                 +----------------------+
                 |  Cluster Service     |
                 +----------+-----------+
                            |
                            |
                            v
                 +----------------------+
                 | Consistent Hash Ring |
                 +----------+-----------+
                            |
          +-----------------+-----------------+
          |                                   |
          |                                   |
          v                                   v
+----------------------+         +----------------------+
|   Cache Node 1       |         |    Cache Node 2      |
+----------------------+         +----------------------+
| ConcurrentHashMap    |         | ConcurrentHashMap    |
| LRU / LFU / FIFO     |         | LRU / LFU / FIFO     |
| TTL                  |         | TTL                  |
+----------------------+         +----------------------+
```

---

# ✨ Features

## Cluster Management

- Cluster-aware request routing
- Multiple cache nodes
- Consistent Hashing
- Dynamic node ownership

---

## Cache Operations

- PUT
- GET
- DELETE
- UPDATE
- TTL Support

---

## Storage

- In-Memory Storage
- Thread-safe implementation
- ConcurrentHashMap
- Fast key lookup

---

## Eviction Policies

Currently Supported

- LRU (Least Recently Used)

Planned

- LFU
- FIFO

---

## Metrics

- Cache Hits
- Cache Misses
- Hit Ratio
- Miss Ratio
- Request Count
- Eviction Count

---

## REST APIs

Example endpoints

```
PUT    /cache
GET    /cache/{key}
DELETE /cache/{key}

GET    /cluster/nodes
GET    /cluster/owner?key=user:101

GET    /metrics
```

---

# 📂 Project Structure

```
distributed-cache-system
│
├── cluster-service
│
├── cache-node-service
│
├── docker
│
├── README.md
│
└── .gitignore
```

---

# 🛠 Tech Stack

### Backend

- Java 17
- Spring Boot
- Spring MVC

### Data Structures

- ConcurrentHashMap
- HashMap
- LinkedHashMap

### Distributed Systems

- Consistent Hashing
- Cluster Routing

### Build Tool

- Maven

### Containerization

- Docker

### Testing

- JUnit

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/distributed-cache-system.git

cd distributed-cache-system
```

---

## Build

```bash
mvn clean install
```

---

## Run Cluster Service

```bash
cd cluster-service

mvn spring-boot:run
```

---

## Run Cache Node

```bash
cd cache-node-service

mvn spring-boot:run
```

---

# 🔄 Request Flow

```
Client

↓

Cluster Controller

↓

Cluster Service

↓

Consistent Hash Ring

↓

Identify Target Cache Node

↓

Cache Storage

↓

Return Response
```

---

# 🧠 Consistent Hashing

Instead of using modulo-based partitioning, this project uses **Consistent Hashing** to distribute keys across cache nodes.

Benefits include:

- Minimal key movement
- Better scalability
- Simplified node addition/removal
- Uniform key distribution

---

# ⚡ Thread Safety

The cache storage is designed using **ConcurrentHashMap** to support concurrent read/write operations safely.

Goals:

- High Throughput
- Low Contention
- Thread Safety
- Better Performance

---

# 📊 Roadmap

## Completed

- [x] Cluster Controller
- [x] Cluster Service
- [x] Consistent Hash Ring
- [x] Cache Node
- [x] Thread-safe Storage
- [x] REST APIs
- [x] LRU Eviction
- [x] Cache Metrics
- [x] Docker Support

## In Progress

- [ ] LFU Eviction
- [ ] FIFO Eviction
- [ ] TTL Optimization
- [ ] Performance Benchmarking
- [ ] Unit Testing

## Planned

- [ ] Cache Replication
- [ ] Automatic Failover
- [ ] Cluster Rebalancing
- [ ] Prometheus Metrics
- [ ] Grafana Dashboard

---

# 🎯 Design Goals

- Low Latency
- High Throughput
- Modular Design
- Horizontal Scalability
- Fault Isolation
- Clean Architecture
- Extensible Eviction Policies
- Maintainability

---

# 📈 Future Improvements

- Replication
- Failover
- Leader Election
- Compression
- Persistent Storage
- Authentication
- Rate Limiting
- Monitoring Dashboard

---

# 🤝 Contributing

Contributions are welcome.

If you would like to improve this project:

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Open a Pull Request

---

# 👨‍💻 Author

**Vishal Gorakh Jadhav**

Java Backend Engineer

- LinkedIn: https://www.linkedin.com/in/vishal-jadhav-479bb0277/
- GitHub: https://github.com/vishal202021

---

# ⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub.
