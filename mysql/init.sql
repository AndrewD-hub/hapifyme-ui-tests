USE hapify_social;

CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    signup_date DATE NOT NULL,
    profile_pic VARCHAR(255) DEFAULT 'assets/images/profile_pics/defaults/head_deep_blue.png',
    num_posts INT DEFAULT 0,
    num_likes INT DEFAULT 0,
    user_closed VARCHAR(3) DEFAULT 'no',
    friend_array TEXT,
    confirmed TINYINT DEFAULT 1,
    confirmation_token VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS posts (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     body TEXT NOT NULL,
                                     added_by VARCHAR(100) NOT NULL,
    user_to VARCHAR(100) DEFAULT 'none',
    date_added DATETIME NOT NULL,
    user_closed VARCHAR(3) DEFAULT 'no',
    deleted VARCHAR(3) DEFAULT 'no',
    likes INT DEFAULT 0,
    image VARCHAR(255) DEFAULT ''
    );

CREATE TABLE IF NOT EXISTS messages (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        user_to VARCHAR(100) NOT NULL,
    user_from VARCHAR(100) NOT NULL,
    body TEXT NOT NULL,
    date DATETIME NOT NULL,
    opened VARCHAR(3) DEFAULT 'no',
    viewed VARCHAR(3) DEFAULT 'no',
    deleted VARCHAR(3) DEFAULT 'no'
    );

CREATE TABLE IF NOT EXISTS notifications (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             user_to VARCHAR(100) NOT NULL,
    user_from VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    link VARCHAR(255),
    datetime DATETIME NOT NULL,
    opened VARCHAR(3) DEFAULT 'no',
    viewed VARCHAR(3) DEFAULT 'no'
    );

CREATE TABLE IF NOT EXISTS friend_requests (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               user_to VARCHAR(100) NOT NULL,
    user_from VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS trends (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(50) NOT NULL,
    hits INT DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS comments (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        post_body TEXT NOT NULL,
                                        posted_by VARCHAR(100) NOT NULL,
    posted_to VARCHAR(100) NOT NULL,
    date_added DATETIME NOT NULL,
    removed VARCHAR(3) DEFAULT 'no',
    post_id INT NOT NULL
    );