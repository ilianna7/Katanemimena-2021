# Katanemimena-2021

Αυτή η εφαρμογή είναι για το μάθημα Κατανεμημένα Συστήματα 2020-2021.

Για να τρέξει ο κώδικας, πρέπει: 

    1)Να πάτε στα παρακάτω σρχεία στον φάκελο src και να βάλετε τα στοιχεία της βάσης mysql που θα συνδέσετε με το πρόγραμμα. 
          α)application.properties
          β)hibernate.cfg.xml

    2)Να συνδέσετε έναν apache v9 server
    
    3)Να τρέξετε τους παρακάτω πίνακες/κώδικα στην mysql βάση: 
    
/*------------------------------------------------------------------------------------------------------------------------------------------------*/
DROP TABLE  IF EXISTS `application`;
DROP TABLE  IF EXISTS `user`;


CREATE TABLE `user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`username` varchar(45) NOT NULL UNIQUE,
`password` varchar(100) NOT NULL,
`first_name` varchar(45) DEFAULT NULL,
`last_name` varchar(45) DEFAULT NULL,
`email` varchar(45) DEFAULT NULL UNIQUE,
`afm` int(9) DEFAULT NULL UNIQUE,
`authority` varchar(50) NOT NULL,
`enabled` tinyint(1) NOT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;



CREATE TABLE `application` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`start_date` varchar(45) DEFAULT NULL,
`end_date` varchar(45) DEFAULT NULL,
`reason` varchar(45) DEFAULT NULL,
`status` varchar(45) DEFAULT NULL,
`accepted` varchar(45) DEFAULT NULL,
`employee_id` int(11) DEFAULT NULL,
PRIMARY KEY (id),
CONSTRAINT `FK_EMPLOYEE` FOREIGN KEY (employee_id)
REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `user` VALUES
(1,'root', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 'John','Doe','john@hua.gr', 123456789, 'ROLE_ADMIN', 1); 

/*------------------------------------------------------------------------------------------------------------------------------------------------*/


Αν θέλετε να έχετε έτοιμα παραδειγματα, μπορείτε να προσθέσετε και τα παρακάτω δεδομένα: 

INSERT INTO `user` VALUES
(2,'employee', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 'David','Adams','david@hua.gr', 234567890, 'ROLE_EMPLOYEE', 1),
(3,'sara', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 'Sara','Adams','sara@hua.gr', 098765432, 'ROLE_EMPLOYEE', 1),
(4,'super', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 'Super','Adams','super@hua.gr', 987654321, 'ROLE_SUPER', 1),
(5,'tm', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 'Super','Adams','tm@hua.gr', 678906543, 'ROLE_TM', 1);

INSERT INTO `application` VALUES
(1, '22/01/2021', '23/01/2021', 'Sick', 'Accepted', 'No',2),
(2, '01/01/2021', '10/01/2021', 'Anual', 'Rejected', 'Yes',2),
(3, '31/01/2021', '07/02/2021', 'Anual', 'Accepted','No' ,2),
(4, '03/03/2021', '13/03/2021', 'Anual', 'Processing','No' ,2),
(5, '10/02/2021', '10/02/2021', 'Student','Accepted', 'No',3),
(6, '31/04/2021', '31/04/2021', 'Parental', 'Processing','No' ,4);


