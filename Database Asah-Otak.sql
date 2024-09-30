
CREATE TABLE master_kata (
  id INT(11) AUTO_INCREMENT PRIMARY KEY,
  kata VARCHAR(255) NOT NULL,
  clue VARCHAR(255)
);


CREATE TABLE point_game (
  id_point INT(11) AUTO_INCREMENT PRIMARY KEY,
  nama_user VARCHAR(255) NOT NULL,
  total_point INT(20) DEFAULT 0,
  tanggal DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO master_kata (kata, clue) VALUES
('LEMARI', 'Aku tempat menyimpan pakaian?'),
('PENSIL', 'Aku alat tulis berbahan kayu'),
('KOMPUTER', 'Aku perangkat yang sering digunakan untuk bekerja'),
('MOBIL', 'Aku alat transportasi dengan roda empat');


