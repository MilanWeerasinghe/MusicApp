# 🎵 Artist Management System

The Artist Management System is a Java-based application designed to manage and organize information about artists, their albums, and songs in a relational database. This system supports CRUD operations for managing artists, albums, and songs, allowing users to efficiently store, update, and retrieve music-related data. Built without using frameworks, this project demonstrates Java fundamentals in building a structured application with a relational database and following best practices like the Singleton design pattern for database connections.

## Features

- **Artist Management**: Add, view, update, and delete artist details (e.g., name, age).
- **Album Management**: Manage album details associated with artists, including album names and song lists.
- **Song Management**: Store and retrieve information about songs (e.g., title, duration) associated with specific artists and albums.
- **Database Singleton Pattern**: Ensures a single, reusable connection instance to the database for better performance and resource management.
- **Object-Oriented Design**: Clean separation of concerns with dedicated classes for each entity and Data Access Object (DAO) classes for database operations.

## Database Structure

- **Artist**: Stores artist information such as ID, first name, last name, and age.
- **Album**: Contains album details with a unique ID and name, linked to songs and artists.
- **Song**: Holds song-specific information including ID, title, duration, and links to the artist and album.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/MilanWeerasinghe/MusicApp.git
