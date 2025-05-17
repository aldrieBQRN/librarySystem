-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 17, 2025 at 11:51 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `libraryManagementSystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `Books`
--

CREATE TABLE `Books` (
  `book_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `genre` varchar(100) DEFAULT NULL,
  `publication_year` int(11) DEFAULT NULL,
  `copies` int(11) DEFAULT 1,
  `location` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Books`
--

INSERT INTO `Books` (`book_id`, `title`, `author`, `isbn`, `genre`, `publication_year`, `copies`, `location`, `status`) VALUES
(117, 'The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 'Fiction', 1925, 13, 'Shelf A1', 'Available'),
(118, 'To Kill a Mockingbird', 'Harper Lee', '9780061120084', 'Fiction', 1960, 20, 'Shelf A1', 'Available'),
(119, '1984', 'George Orwell', '9780451524935', 'Science Fiction', 1949, 19, 'Shelf A1', 'Borrowed'),
(120, 'The Da Vinci Code', 'Dan Brown', '9780307474270', 'Mystery/Thriller', 2003, 20, 'Shelf A1', 'Reserved'),
(121, 'Harry Potter and the Sorcerer\'s Stone', 'J.K. Rowling', '9780590353427', 'Fantasy', 1997, 20, 'Shelf A1', 'Available'),
(122, 'Pride and Prejudice', 'Jane Austen', '9781503290563', 'Fiction', 1813, 20, 'Shelf A2', 'Available'),
(123, 'Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', '9780062316097', 'Non-Fiction', 2011, 20, 'Shelf A2', 'Available'),
(124, 'Dune', 'Frank Herbert', '9780441013593', 'Science Fiction', 1965, 20, 'Shelf A2', 'Available'),
(125, 'Gone Girl', 'Gillian Flynn', '9780307588371', 'Mystery/Thriller', 2012, 20, 'Shelf A2', 'Reserved'),
(126, 'The Hobbit', 'J.R.R. Tolkien', '9780547928227', 'Fantasy', 1937, 20, 'Shelf A2', 'Available'),
(127, 'The Catcher in the Rye', 'J.D. Salinger', '9780316769488', 'Fiction', 1951, 20, 'Shelf A3', 'Available'),
(128, 'Educated', 'Tara Westover', '9780399590504', 'Non-Fiction', 2018, 20, 'Shelf A3', 'Available'),
(129, 'Ender\'s Game', 'Orson Scott Card', '9780812550702', 'Science Fiction', 1985, 19, 'Shelf A3', 'Available'),
(130, 'The Girl with the Dragon Tattoo', 'Stieg Larsson', '9780307454546', 'Mystery/Thriller', 2005, 20, 'Shelf A3', 'Available'),
(131, 'The Name of the Wind', 'Patrick Rothfuss', '9780756404741', 'Fantasy', 2007, 20, 'Shelf A3', 'Available'),
(132, 'One Hundred Years of Solitude', 'Gabriel García Márquez', '9780060883287', 'Fiction', 1967, 20, 'Shelf B1', 'Available'),
(133, 'The Immortal Life of Henrietta Lacks', 'Rebecca Skloot', '9781400052189', 'Non-Fiction', 2010, 19, 'Shelf B1', 'Available'),
(134, 'Neuromancer', 'William Gibson', '9780441569595', 'Science Fiction', 1984, 20, 'Shelf B1', 'Available'),
(135, 'The Silence of the Lambs', 'Thomas Harris', '9780312924584', 'Mystery/Thriller', 1988, 20, 'Shelf B1', 'Available'),
(136, 'The Lies of Locke Lamora', 'Scott Lynch', '9780553588941', 'Fantasy', 2006, 20, 'Shelf B1', 'Available'),
(137, 'Brave New World', 'Aldous Huxley', '9780060850524', 'Fiction', 1932, 20, 'Shelf B2', 'Available'),
(138, 'The Wright Brothers', 'David McCullough', '9781476728759', 'Non-Fiction', 2015, 20, 'Shelf B2', 'Available'),
(139, 'Foundation', 'Isaac Asimov', '9780553293357', 'Science Fiction', 1951, 20, 'Shelf B2', 'Available'),
(140, 'Shutter Island', 'Dennis Lehane', '9780061898815', 'Mystery/Thriller', 2003, 20, 'Shelf B2', 'Available'),
(141, 'Mistborn: The Final Empire', 'Brandon Sanderson', '9780765311788', 'Fantasy', 2006, 20, 'Shelf B2', 'Available'),
(142, 'The Road', 'Cormac McCarthy', '9780307387899', 'Fiction', 2006, 20, 'Shelf B3', 'Available'),
(143, 'In Cold Blood', 'Truman Capote', '9780679745587', 'Non-Fiction', 1966, 20, 'Shelf B3', 'Available'),
(144, 'Snow Crash', 'Neal Stephenson', '9780553380958', 'Science Fiction', 1992, 20, 'Shelf B3', 'Available'),
(145, 'The Da Vinci Code', 'Dan Brown', '9780307474278', 'Mystery/Thriller', 2003, 20, 'Shelf B3', 'Available'),
(146, 'The Way of Kings', 'Brandon Sanderson', '9780765326355', 'Fantasy', 2010, 20, 'Shelf B3', 'Available'),
(147, 'The Left Hand of Darkness', 'Ursula K. Le Guin', '9780441478125', 'Science Fiction', 1969, 20, 'Shelf C1', 'Available'),
(148, 'Big Little Lies', 'Liane Moriarty', '9780425274866', 'Mystery/Thriller', 2014, 20, 'Shelf C1', 'Available'),
(149, 'Eragon', 'Christopher Paolini', '9780375826696', 'Fantasy', 2002, 20, 'Shelf C1', 'Available'),
(150, 'Moby-Dick', 'Herman Melville', '9781503280786', 'Fiction', 1851, 20, 'Shelf C2', 'Available'),
(151, 'Thinking, Fast and Slow', 'Daniel Kahneman', '9780374533557', 'Non-Fiction', 2011, 20, 'Shelf C2', 'Available'),
(152, 'Hyperion', 'Dan Simmons', '9780553283686', 'Science Fiction', 1989, 20, 'Shelf C2', 'Available'),
(153, 'The Girl on the Train', 'Paula Hawkins', '9781594634024', 'Mystery/Thriller', 2015, 20, 'Shelf C2', 'Available'),
(154, 'The Blade Itself', 'Joe Abercrombie', '9780575079793', 'Fantasy', 2006, 20, 'Shelf C2', 'Available'),
(155, 'Crime and Punishment', 'Fyodor Dostoevsky', '9780486415871', 'Fiction', 1866, 20, 'Shelf C3', 'Available'),
(156, 'The Power of Habit', 'Charles Duhigg', '9780812981605', 'Non-Fiction', 2012, 20, 'Shelf C3', 'Available'),
(157, 'The Stars My Destination', 'Alfred Bester', '9780679767800', 'Science Fiction', 1956, 20, 'Shelf C3', 'Available'),
(158, 'Before I Go to Sleep', 'S.J. Watson', '9780062060563', 'Mystery/Thriller', 2011, 20, 'Shelf C3', 'Available'),
(159, 'The Priory of the Orange Tree', 'Samantha Shannon', '9781635570298', 'Fantasy', 2019, 20, 'Shelf C3', 'Available'),
(160, 'The Grapes of Wrath', 'John Steinbeck', '9780143039433', 'Fiction', 1939, 20, 'Shelf D1', 'Available'),
(161, 'A Brief History of Time', 'Stephen Hawking', '9780553380163', 'Non-Fiction', 1988, 20, 'Shelf D1', 'Available'),
(162, 'Childhood’s End', 'Arthur C. Clarke', '9780345347954', 'Science Fiction', 1953, 20, 'Shelf D1', 'Available'),
(163, 'Sharp Objects', 'Gillian Flynn', '9780307341556', 'Mystery/Thriller', 2006, 20, 'Shelf D1', 'Available'),
(164, 'The Black Prism', 'Brent Weeks', '9780316075558', 'Fantasy', 2010, 20, 'Shelf D1', 'Available'),
(165, 'The Sun Also Rises', 'Ernest Hemingway', '9780743297333', 'Fiction', 1926, 20, 'Shelf D2', 'Available'),
(166, 'Atomic Habits', 'James Clear', '9780735211292', 'Non-Fiction', 2018, 5, 'Shelf D2', 'Available'),
(167, 'Rendezvous with Rama', 'Arthur C. Clarke', '9780345322401', 'Science Fiction', 1973, 20, 'Shelf D2', 'Available'),
(168, 'The Woman in the Window', 'A.J. Finn', '9780062678416', 'Mystery/Thriller', 2018, 20, 'Shelf D2', 'Available'),
(169, 'The Poppy War', 'R.F. Kuang', '9780062662569', 'Fantasy', 2018, 20, 'Shelf D2', 'Available'),
(170, 'Catch-22', 'Joseph Heller', '9781451626650', 'Fiction', 1961, 20, 'Shelf D3', 'Available'),
(171, 'Shoe Dog', 'Phil Knight', '9781501135927', 'Non-Fiction', 2016, 20, 'Shelf D3', 'Available'),
(172, 'The Three-Body Problem', 'Liu Cixin', '9780765382030', 'Science Fiction', 2008, 20, 'Shelf D3', 'Available'),
(173, 'The Silent Patient', 'Alex Michaelides', '9781250301697', 'Mystery/Thriller', 2019, 18, 'Shelf D3', 'Reserved'),
(174, 'The Last Wish', 'Andrzej Sapkowski', '9780316029186', 'Fantasy', 1993, 12, 'Shelf D3', 'Reserved');

-- --------------------------------------------------------

--
-- Table structure for table `BorrowedBooks`
--

CREATE TABLE `BorrowedBooks` (
  `borrow_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `book_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `borrow_date` date DEFAULT curdate(),
  `due_date` date NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `BorrowedBooks`
--

INSERT INTO `BorrowedBooks` (`borrow_id`, `username`, `book_id`, `quantity`, `borrow_date`, `due_date`, `status`) VALUES
(22, 'Aldrie Baquiran', 117, 1, '2025-02-23', '2025-02-25', 'Returned/Reserved'),
(23, 'Zeth Ramzy', 117, 2, '2025-02-24', '2025-02-25', 'Reserved'),
(24, 'Yeoj Valdez', 174, 1, '2025-02-24', '2025-02-25', 'Returned/Reserved'),
(25, 'Aldrie Baquiran', 119, 1, '2025-02-16', '2025-02-25', 'Borrowed'),
(26, 'Aldrie Baquiran', 174, 1, '2025-02-25', '2025-02-25', 'Returned/Reserved'),
(27, 'Aldrie Baquiran', 174, 2, '2025-02-23', '2025-02-25', 'Returned/Reserved'),
(28, 'Aldrie Baquiran', 173, 2, '2025-02-23', '2025-02-25', 'Returned/Reserved'),
(29, 'Yeoj Valdez', 125, 2, '2025-02-24', '2025-02-26', 'Returned/Reserved'),
(30, 'Yeoj Valdez', 133, 1, '2025-02-11', '2025-02-19', 'Returned'),
(31, 'Yeoj Valdez', 120, 1, '2025-01-13', '2025-02-26', 'Returned/Reserved'),
(32, 'Yeoj Valdez', 171, 1, '2025-01-06', '2025-01-07', 'Returned'),
(34, 'Ryan Magnaye', 117, 2, '2025-03-10', '2025-03-25', 'Returned/Reserved'),
(35, 'Ryan Magnaye', 174, 1, '2025-02-24', '2025-02-25', 'Returned/Reserved'),
(36, 'Ryan Magnaye', 174, 1, '2025-01-07', '2025-01-09', 'Reserved'),
(37, 'Ryan Magnaye', 174, 1, '2025-03-17', '2025-03-18', 'Reserved'),
(38, 'Aldrie Baquiran', 174, 1, '2025-02-24', '2025-02-26', 'Returned/Reserved'),
(39, 'Aldrie Baquiran', 125, 1, '2025-02-25', '2025-02-26', 'Returned/Reserved'),
(40, 'Aldrie Baquiran', 174, 1, '2025-02-25', '2025-02-26', 'Borrowed'),
(41, 'John Boiser', 174, 1, '2025-02-18', '2025-02-18', 'Reserved'),
(42, 'John Boiser', 117, 1, '2025-02-25', '2025-02-26', 'Borrowed'),
(43, 'Ryan Magnaye', 117, 1, '2025-02-24', '2025-02-25', 'Returned/Reserved'),
(44, 'Yeoj Valdez', 120, 1, '2025-02-27', '2025-02-28', 'Returned/Reserved'),
(45, 'Aldrie Baquiran', 174, 1, '2025-02-25', '2025-02-26', 'Returned/Reserved'),
(46, 'Yeoj Valdez', 126, 1, '2025-02-27', '2025-03-09', 'Returned'),
(47, 'Yeoj Valdez', 117, 1, '2025-02-26', '2025-02-27', 'Borrowed');

-- --------------------------------------------------------

--
-- Table structure for table `Fines`
--

CREATE TABLE `Fines` (
  `fine_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `book_id` int(11) NOT NULL,
  `due_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `days_overdue` int(11) NOT NULL,
  `fine_amount` decimal(10,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Fines`
--

INSERT INTO `Fines` (`fine_id`, `username`, `book_id`, `due_date`, `return_date`, `days_overdue`, `fine_amount`, `status`) VALUES
(1, 'Yeoj Valdez', 133, '2025-02-19', '2025-02-24', 5, 250.00, 'Paid'),
(2, 'Aldrie Baquiran', 174, '2025-02-25', '2025-02-25', 0, 0.00, 'Pending'),
(3, 'Aldrie Baquiran', 174, '2025-02-25', '2025-02-25', 0, 0.00, 'Pending'),
(4, 'Yeoj Valdez', 171, '2025-01-07', '2025-01-15', 8, 200.00, 'Paid'),
(6, 'Ryan Magnaye', 117, '2025-03-25', '2025-03-26', 1, 50.00, 'Paid'),
(7, 'Aldrie Baquiran', 174, '2025-02-26', '2025-02-27', 1, 50.00, 'Paid'),
(8, 'Yeoj Valdez', 125, '2025-02-26', '2025-02-26', 0, 0.00, 'Pending'),
(9, 'Aldrie Baquiran', 125, '2025-02-26', '2025-02-25', 0, 0.00, 'Pending'),
(10, 'Ryan Magnaye', 174, '2025-02-25', '2025-02-24', 0, 0.00, 'Pending'),
(11, 'Yeoj Valdez', 120, '2025-02-26', '2025-02-27', 1, 50.00, 'Pending'),
(12, 'Aldrie Baquiran', 174, '2025-02-26', '2025-02-27', 1, 50.00, 'Pending'),
(13, 'Yeoj Valdez', 120, '2025-02-28', '2025-02-27', 0, 0.00, 'Pending'),
(14, 'Yeoj Valdez', 126, '2025-03-09', '2025-03-12', 3, 150.00, 'Paid'),
(15, 'Ryan Magnaye', 117, '2025-02-25', '2025-02-26', 1, 50.00, 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `Notifications`
--

CREATE TABLE `Notifications` (
  `notification_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `message` text NOT NULL,
  `status` enum('Unread','Read') DEFAULT 'Unread'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Notifications`
--

INSERT INTO `Notifications` (`notification_id`, `username`, `message`, `status`) VALUES
(1, 'Aldrie Baquiran', 'You have successfully borrowed Book ID: 174. Due date: 2025-02-25', 'Read'),
(2, 'Aldrie Baquiran', 'You have successfully borrowed Book ID: 173. Due date: 2025-02-25', 'Read'),
(3, 'Aldrie Baquiran', 'You have successfully returned Book ID: 173. Overdue fine: $50.0', 'Read'),
(4, 'Yeoj Valdez', 'The book you reserved (Book ID: 173) is now available.', 'Unread'),
(5, 'Aldrie Baquiran', 'You have successfully returned Book ID: 117', 'Read'),
(6, 'Zeth Ramzy', 'The book you reserved (Book ID: 117) is now available.', 'Unread'),
(7, 'Yeoj Valdez', 'You have successfully borrowed Book ID: 125. Due date: 2025-02-26', 'Unread'),
(8, 'Yeoj Valdez', 'You have successfully borrowed Book ID: 133. Due date: 2025-02-19', 'Unread'),
(9, 'Yeoj Valdez', 'You have successfully returned Book ID: 133. Overdue fine: $250.0', 'Unread'),
(10, 'Yeoj Valdez', 'You have successfully paid your overdue fine of $250.0', 'Unread'),
(11, 'Aldrie Baquiran', 'You have successfully returned Book ID: 174', 'Read'),
(12, 'Aldrie Baquiran', 'The book you reserved (Book ID: 174) is now available.', 'Read'),
(13, 'Aldrie Baquiran', 'You have successfully returned Book ID: 174', 'Read'),
(14, 'Aldrie Baquiran', 'The book you reserved (Book ID: 174) is now available.', 'Read'),
(15, 'Yeoj Valdez', 'You have successfully borrowed Book ID: 120. Due date: 2025-02-26', 'Unread'),
(16, 'Yeoj Valdez', 'You have successfully borrowed Book ID: 171. Due date: 2025-01-07', 'Unread'),
(17, 'Yeoj Valdez', 'You have successfully returned 1 copies of Book ID: 171. Overdue fine: $400.0', 'Unread'),
(18, 'Yeoj Valdez', 'You have successfully paid your overdue fine of 400.0', 'Unread'),
(21, 'Zeth Ramzy', 'The book you reserved (Book ID: 117) is now available.', 'Unread'),
(22, 'Ryan Magnaye', 'You have successfully borrowed Book ID: 117. Due date: 2025-03-25', 'Unread'),
(23, 'Ryan Magnaye', 'You have successfully borrowed Book ID: 174. Due date: 2025-02-25', 'Unread'),
(24, 'Ryan Magnaye', 'You have successfully returned 2 copies of Book ID: 117. Overdue fine: $50.0', 'Unread'),
(25, 'Zeth Ramzy', 'The book you reserved (Book ID: 117) is now available.', 'Unread'),
(26, 'Ryan Magnaye', 'You have successfully paid your overdue fine of 50.0', 'Unread'),
(27, 'Ryan Magnaye', 'Your book (ID: 120) has been reserved. Return date: 2025-02-26.', 'Unread'),
(28, 'Ryan Magnaye', 'You have successfully borrowed Book ID: 174. Due date: 2025-01-09', 'Unread'),
(29, 'Yeoj Valdez', 'Your book (ID: 174) has been reserved. Return date: 2025-01-09.', 'Unread'),
(30, 'Ryan Magnaye', 'You have successfully borrowed Book ID: 174. Due date: 2025-03-18', 'Unread'),
(31, 'Yeoj Valdez', 'Your book (ID: 174) has been reserved. Return date: 2025-03-18.', 'Unread'),
(32, 'Aldrie Baquiran', 'You have successfully borrowed Book ID: 174. Due date: 2025-02-26', 'Read'),
(33, 'Aldrie Baquiran', 'You have successfully returned 1 copies of Book ID: 174. Overdue fine: $50.0', 'Read'),
(34, 'Aldrie Baquiran', 'The book you reserved (Book ID: 174) is now available.', 'Read'),
(35, 'Aldrie Baquiran', 'You have successfully paid your overdue fine of 50.0', 'Read'),
(36, 'Aldrie Baquiran', 'Your book (ID: 125) has been reserved. Return date: 2025-02-26.', 'Read'),
(37, 'Yeoj Valdez', 'You have successfully returned 2 copies of Book ID: 125', 'Unread'),
(38, 'Aldrie Baquiran', 'The book you reserved (Book ID: 125) is now available.', 'Read'),
(39, 'Aldrie Baquiran', 'You have successfully borrowed book (ID: 125). Due Date: 2025-02-26.', 'Unread'),
(40, 'Aldrie Baquiran', 'You have successfully returned 1 copies of Book ID: 125', 'Unread'),
(41, 'Aldrie Baquiran', 'The book you reserved (Book ID: 125) is now available.', 'Read'),
(42, 'Aldrie Baquiran', 'You have successfully borrowed book (ID: 174). Due Date: 2025-02-26.', 'Unread'),
(43, 'Aldrie Baquiran', 'Your book (ID: 174) has been reserved. Return date: 2025-02-25.', 'Read'),
(44, 'John Boiser', 'You have successfully borrowed Book ID: 174. Due date: 2025-02-18', 'Unread'),
(45, 'John Boiser', 'You have successfully borrowed Book ID: 117. Due date: 2025-02-26', 'Unread'),
(46, 'Ryan Magnaye', 'You have successfully borrowed Book ID: 117. Due date: 2025-02-25', 'Unread'),
(47, 'Yeoj Valdez', 'You have successfully borrowed Book ID: 120. Due date: 2025-02-28', 'Unread'),
(48, 'Ryan Magnaye', 'You have successfully returned 1 copies of Book ID: 174', 'Unread'),
(49, 'Aldrie Baquiran', 'The book you reserved (Book ID: 174) is now available.', 'Read'),
(50, 'Aldrie Baquiran', 'Your book (ID: 174) has been reserved. Return date: 2025-02-18.', 'Unread'),
(51, 'Yeoj Valdez', 'You have successfully returned 1 copies of Book ID: 120. Overdue fine: $50.0', 'Unread'),
(52, 'Ryan Magnaye', 'The book you reserved (Book ID: 120) is now available.', 'Unread'),
(53, 'Aldrie Baquiran', 'You have successfully borrowed Book ID: 174. Due date: 2025-02-26', 'Unread'),
(54, 'Aldrie Baquiran', 'You have successfully returned 1 copies of Book ID: 174. Overdue fine: $50.0', 'Unread'),
(55, 'Aldrie Baquiran', 'The book you reserved (Book ID: 174) is now available.', 'Unread'),
(56, 'Yeoj Valdez', 'You have successfully borrowed Book ID: 126. Due date: 2025-03-09', 'Unread'),
(57, 'Yeoj Valdez', 'You have successfully returned 1 copies of Book ID: 120', 'Unread'),
(58, 'Ryan Magnaye', 'The book you reserved (Book ID: 120) is now available.', 'Unread'),
(59, 'Yeoj Valdez', 'You have successfully returned 1 copies of Book ID: 126. Overdue fine: $150.0', 'Unread'),
(60, 'Yeoj Valdez', 'You have successfully paid your overdue fine of 150.0', 'Unread'),
(61, 'Yeoj Valdez', 'Your book (ID: 117) has been reserved. Return date: 2025-02-25.', 'Unread'),
(62, 'Ryan Magnaye', 'You have successfully returned 1 copies of Book ID: 117. Overdue fine: $50.0', 'Unread'),
(63, 'Zeth Ramzy', 'The book you reserved (Book ID: 117) is now available.', 'Unread'),
(64, 'Yeoj Valdez', 'You have successfully borrowed book (ID: 117). Due Date: 2025-02-27.', 'Unread');

-- --------------------------------------------------------

--
-- Table structure for table `Reservations`
--

CREATE TABLE `Reservations` (
  `reservation_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `book_id` int(11) NOT NULL,
  `reservation_date` date NOT NULL DEFAULT current_timestamp(),
  `status` varchar(255) DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Reservations`
--

INSERT INTO `Reservations` (`reservation_id`, `username`, `book_id`, `reservation_date`, `status`) VALUES
(2, 'Zeth Ramzy', 117, '2025-02-25', 'Available'),
(3, 'Aldrie Baquiran', 174, '2025-02-25', 'Available'),
(4, 'Yeoj Valdez', 173, '2025-02-25', 'Available'),
(5, 'Ryan Magnaye', 120, '2025-02-26', 'Available'),
(6, 'Yeoj Valdez', 174, '2025-01-09', 'Available'),
(7, 'Yeoj Valdez', 174, '2025-03-18', 'Available'),
(8, 'Aldrie Baquiran', 125, '2025-02-26', 'Available'),
(9, 'Aldrie Baquiran', 174, '2025-02-25', 'Available'),
(10, 'Aldrie Baquiran', 174, '2025-02-18', 'Available'),
(11, 'Yeoj Valdez', 117, '2025-02-25', 'Complete');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `contact` varchar(15) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'Member',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`user_id`, `username`, `email`, `password`, `contact`, `role`, `created_at`) VALUES
(1, 'Staff', 'staff', 'staff', 'unknown', 'Staff', '2025-02-21 23:35:00'),
(2, 'Aldrie Baquiran', 'aldrie', 'aldrie15', '0982837918298', 'Member', '2025-02-21 23:35:45'),
(3, 'Zeth Ramzy', 'zeth', 'zeth123', '09826273', 'Member', '2025-02-23 07:19:30'),
(4, 'Yeoj Valdez', 'yeoj', 'yeoj123', '09478328282', 'Member', '2025-02-23 07:20:24'),
(5, 'John Boiser', 'boiser', 'boiser123', '0936722883', 'Member', '2025-02-23 07:24:04'),
(6, 'Ryan Magnaye', 'ryan', 'ryan123', '097276262', 'Member', '2025-02-24 10:03:32'),
(7, 'Calvin', 'clavin', 'Calvin', '09293823', 'Member', '2025-02-24 13:05:06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Books`
--
ALTER TABLE `Books`
  ADD PRIMARY KEY (`book_id`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indexes for table `BorrowedBooks`
--
ALTER TABLE `BorrowedBooks`
  ADD PRIMARY KEY (`borrow_id`),
  ADD KEY `username` (`username`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `Fines`
--
ALTER TABLE `Fines`
  ADD PRIMARY KEY (`fine_id`),
  ADD KEY `username` (`username`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `Notifications`
--
ALTER TABLE `Notifications`
  ADD PRIMARY KEY (`notification_id`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `Reservations`
--
ALTER TABLE `Reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `fk_reservations_users` (`username`),
  ADD KEY `fk_reservations_books` (`book_id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `unique_username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Books`
--
ALTER TABLE `Books`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=175;

--
-- AUTO_INCREMENT for table `BorrowedBooks`
--
ALTER TABLE `BorrowedBooks`
  MODIFY `borrow_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `Fines`
--
ALTER TABLE `Fines`
  MODIFY `fine_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `Notifications`
--
ALTER TABLE `Notifications`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `Reservations`
--
ALTER TABLE `Reservations`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `BorrowedBooks`
--
ALTER TABLE `BorrowedBooks`
  ADD CONSTRAINT `borrowedbooks_ibfk_1` FOREIGN KEY (`username`) REFERENCES `Users` (`username`) ON DELETE CASCADE,
  ADD CONSTRAINT `borrowedbooks_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `Books` (`book_id`) ON DELETE CASCADE;

--
-- Constraints for table `Fines`
--
ALTER TABLE `Fines`
  ADD CONSTRAINT `fines_ibfk_1` FOREIGN KEY (`username`) REFERENCES `Users` (`username`) ON DELETE CASCADE,
  ADD CONSTRAINT `fines_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `Books` (`book_id`) ON DELETE CASCADE;

--
-- Constraints for table `Notifications`
--
ALTER TABLE `Notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`username`) REFERENCES `Users` (`username`) ON DELETE CASCADE;

--
-- Constraints for table `Reservations`
--
ALTER TABLE `Reservations`
  ADD CONSTRAINT `fk_reservations_books` FOREIGN KEY (`book_id`) REFERENCES `Books` (`book_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_reservations_users` FOREIGN KEY (`username`) REFERENCES `Users` (`username`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
