/*
Created: 25.11.2023
Modified: 28.11.2023
Project: BADA
Model: Model_logiczny
Company: gr. B
Author: Iga Bernat, Mikołaj Roszczyk
Version: 5
Database: Oracle 19c
*/


-- Create sequences section -------------------------------------------------

CREATE SEQUENCE schroniska_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE pracownicy_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE adresy_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE wolontariusze_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE poczty_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE zwierzeta_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE zadania_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE rasa_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE gatunki_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE wplaty_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE kojce_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE stanowiska_seq
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE zajecia_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

CREATE SEQUENCE adoptujacy_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOMINVALUE
 CACHE 20
/

-- Create tables section -------------------------------------------------

-- Table Schroniska

CREATE TABLE Schroniska(
  Nr_schroniska Integer NOT NULL,
  Nazwa Varchar2(30 ) NOT NULL,
  Data_zalozenia Date NOT NULL,
  Kierownik Varchar2(50 ) NOT NULL,
  Nr_adresu Integer NOT NULL
)
/

-- Add keys for table Schroniska

ALTER TABLE Schroniska ADD CONSTRAINT Identyfikator_schroniska PRIMARY KEY (Nr_schroniska)
/

-- Table Zwierzęta

CREATE TABLE Zwierzęta(
  Nr_zwierzecia Integer NOT NULL,
  Imie Varchar2(20 ),
  Data_przyjecia Date NOT NULL,
  Szczepienie_wscieklizna Char(1 ) NOT NULL,
  Data_adopcji Date,
  Rok_urodzenia Char(4 ),
  Nr_schroniska Integer NOT NULL,
  Nr_adoptujacego Integer,
  Nr_kojca Integer NOT NULL,
  Nr_rasy Integer
)
/

-- Create indexes for table Zwierzęta

CREATE INDEX IX_Zajmuje_sie ON Zwierzęta (Nr_schroniska)
/

CREATE INDEX IX_Adoptuje ON Zwierzęta (Nr_adoptujacego)
/

CREATE INDEX IX_Mieszka_w ON Zwierzęta (Nr_kojca)
/

CREATE INDEX IX_Relationship11 ON Zwierzęta (Nr_rasy)
/

-- Add keys for table Zwierzęta

ALTER TABLE Zwierzęta ADD CONSTRAINT Identyfikator_zwierzecia PRIMARY KEY (Nr_zwierzecia)
/

-- Table Pracownicy

CREATE TABLE Pracownicy(
  Nr_pracownika Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(40 ) NOT NULL,
  Data_urodzenia Date NOT NULL,
  PESEL Char(11 ),
  Plec Char(1 ) NOT NULL,
  Data_zatrudnienia Date NOT NULL,
  Nr_telefonu Varchar2(15 ),
  Email Varchar2(100 ),
  Nr_konta Varchar2(30 ),
  Data_zwolnienia Date,
  Zarobki_h Number(10,2) NOT NULL,
  Nr_schroniska Integer NOT NULL,
  Nr_stanowiska Integer NOT NULL,
  Nr_adresu Integer NOT NULL,
  CONSTRAINT CheckConstraintD1 CHECK (Plec IN ('D','M'))
)
/

-- Create indexes for table Pracownicy

CREATE INDEX IX_Zatrudnia ON Pracownicy (Nr_schroniska)
/

CREATE INDEX IX_Relationship14 ON Pracownicy (Nr_adresu)
/

-- Add keys for table Pracownicy

ALTER TABLE Pracownicy ADD CONSTRAINT Identyfikator_pracownika PRIMARY KEY (Nr_pracownika)
/

-- Table Weterynarz

CREATE TABLE Weterynarz(
  Nr_PWZ Char(5 ) NOT NULL,
  Specjalizacja Varchar2(100 ),
  Nr_pracownika Integer NOT NULL
)
/

-- Add keys for table Weterynarz

ALTER TABLE Weterynarz ADD CONSTRAINT Unique_Identifier2 PRIMARY KEY (Nr_pracownika)
/

-- Table Adoptujacy

CREATE TABLE Adoptujacy(
  Nr_adoptujacego Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(40 ) NOT NULL,
  Nr_telefonu Varchar2(15 ),
  Email Varchar2(50 ),
  Zamieszkanie Char(1 ) NOT NULL,
  Czy_dzieci Char(1 ) NOT NULL,
  Czy_doswiadczenie Char(1 ) NOT NULL,
  Nr_adresu Integer,
  Nr_schroniska Integer,
  CONSTRAINT CheckConstraintD2 CHECK (Zamieszkanie IN ('D','M'))
)
/

-- Create indexes for table Adoptujacy

CREATE INDEX IX_Relationship10 ON Adoptujacy (Nr_adresu)
/

CREATE INDEX IX_Relationship43 ON Adoptujacy (Nr_schroniska)
/

-- Add keys for table Adoptujacy

ALTER TABLE Adoptujacy ADD CONSTRAINT Identyfikator_adoptujacego PRIMARY KEY (Nr_adoptujacego)
/

-- Table Wolontariusze

CREATE TABLE Wolontariusze(
  Nr_wolontariusza Integer NOT NULL,
  Imie Varchar2(20 ) NOT NULL,
  Nazwisko Varchar2(30 ) NOT NULL,
  Nr_telefonu Varchar2(15 ) NOT NULL,
  Email Varchar2(50 ),
  Nr_schroniska Integer NOT NULL,
  Nr_adresu Integer NOT NULL,
  Nr_zadania Integer
)
/

-- Create indexes for table Wolontariusze

CREATE INDEX IX_Koordynuje ON Wolontariusze (Nr_schroniska)
/

CREATE INDEX IX_Relationship13 ON Wolontariusze (Nr_zadania)
/

-- Add keys for table Wolontariusze

ALTER TABLE Wolontariusze ADD CONSTRAINT Identyfikator_wolontariusza PRIMARY KEY (Nr_wolontariusza)
/

-- Table Wplaty

CREATE TABLE Wplaty(
  Nr_wplaty Integer NOT NULL,
  Nr_konta_wplacajacego Varchar2(30 ) NOT NULL,
  Podmiot_wplacajacy Varchar2(200 ),
  Kwota Number(10,2) NOT NULL,
  Data Date NOT NULL,
  Cel_Szczegolowy Varchar2(150 ),
  Nr_schroniska Integer NOT NULL
)
/

-- Create indexes for table Wplaty

CREATE INDEX IX_Otrzymuje ON Wplaty (Nr_schroniska)
/

-- Add keys for table Wplaty

ALTER TABLE Wplaty ADD CONSTRAINT Identyfikator_wplaty PRIMARY KEY (Nr_wplaty)
/

-- Table Kojce

CREATE TABLE Kojce(
  Nr_kojca Integer NOT NULL,
  Rozmiar Integer NOT NULL,
  Liczba_zwierzat Integer NOT NULL,
  Czy_ocieplony Char(1 )
)
/

-- Add keys for table Kojce

ALTER TABLE Kojce ADD CONSTRAINT Identyfikator_kojca PRIMARY KEY (Nr_kojca)
/

-- Table Opieki

CREATE TABLE Opieki(
  Nr_pracownika Integer NOT NULL,
  Nr_zwierzecia Integer NOT NULL,
  Data Date NOT NULL,
  Nr_zajecia Integer
)
/

-- Create indexes for table Opieki

CREATE INDEX IX_Relationship111 ON Opieki (Nr_zajecia)
/

-- Table Spacery

CREATE TABLE Spacery(
  Nr_zwierzecia Integer NOT NULL,
  Nr_wolontariusza Integer NOT NULL,
  Data Date NOT NULL
)
/

-- Table Nadzorowania

CREATE TABLE Nadzorowania(
  Nr_pracownika Integer NOT NULL,
  Nr_kojca Integer NOT NULL,
  Data_przypisania Date NOT NULL
)
/

-- Table Adresy

CREATE TABLE Adresy(
  Nr_adresu Integer NOT NULL,
  Miejscowosc Varchar2(20 ) NOT NULL,
  Ulica Varchar2(30 ) NOT NULL,
  Nr_domu Varchar2(5 ) NOT NULL,
  Nr_lokalu Varchar2(5 ),
  Attribute1 Char(20 )
)
/

-- Add keys for table Adresy

ALTER TABLE Adresy ADD CONSTRAINT PK_Adresy PRIMARY KEY (Nr_adresu)
/

-- Table Poczty

CREATE TABLE Poczty(
  nr_poczty Char(20 ) NOT NULL,
  Kod_pocztowy Char(6 ) NOT NULL,
  Poczta Varchar2(30 ) NOT NULL
)
/

-- Add keys for table Poczty

ALTER TABLE Poczty ADD CONSTRAINT PK_Poczty PRIMARY KEY (nr_poczty)
/

-- Table Stanowiska

CREATE TABLE Stanowiska(
  Nr_stanowiska Integer NOT NULL,
  Opis_stanowiska Varchar2(200 ) NOT NULL
)
/

-- Add keys for table Stanowiska

ALTER TABLE Stanowiska ADD CONSTRAINT PK_Stanowiska PRIMARY KEY (Nr_stanowiska)
/

-- Table Gatunki

CREATE TABLE Gatunki(
  Nr_gatunku Integer NOT NULL,
  Nazwa_gatunku Varchar2(20 ) NOT NULL
)
/

-- Add keys for table Gatunki

ALTER TABLE Gatunki ADD CONSTRAINT PK_Gatunki PRIMARY KEY (Nr_gatunku)
/

-- Table Rasa

CREATE TABLE Rasa(
  Nr_rasy Integer NOT NULL,
  Nazwa_rasy Char(1 ) NOT NULL,
  Nr_gatunku Integer NOT NULL
)
/

-- Add keys for table Rasa

ALTER TABLE Rasa ADD CONSTRAINT PK_Rasa PRIMARY KEY (Nr_rasy)
/

-- Table Zajecia

CREATE TABLE Zajecia(
  Nr_zajecia Integer NOT NULL,
  Nazwa Varchar2(20 ) NOT NULL,
  Opis Varchar2(200 )
)
/

-- Add keys for table Zajecia

ALTER TABLE Zajecia ADD CONSTRAINT PK_Zajecia PRIMARY KEY (Nr_zajecia)
/

-- Table Zadania

CREATE TABLE Zadania(
  Nr_zadania Integer NOT NULL,
  Nazwa Varchar2(20 ) NOT NULL,
  Opis Varchar2(200 )
)
/

-- Add keys for table Zadania

ALTER TABLE Zadania ADD CONSTRAINT PK_Zadania PRIMARY KEY (Nr_zadania)
/

-- Trigger for sequence schroniska_seq for column Nr_schroniska in table Schroniska ---------
CREATE OR REPLACE TRIGGER ts_Schroniska_schroniska_seq BEFORE INSERT
ON Schroniska FOR EACH ROW
BEGIN
  :new.Nr_schroniska := schroniska_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Schroniska_schroniska_seq AFTER UPDATE OF Nr_schroniska
ON Schroniska FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_schroniska in table Schroniska as it uses sequence.');
END;
/

-- Trigger for sequence zwierzeta_seq for column Nr_zwierzecia in table Zwierzęta ---------
CREATE OR REPLACE TRIGGER ts_Zwierzęta_zwierzeta_seq BEFORE INSERT
ON Zwierzęta FOR EACH ROW
BEGIN
  :new.Nr_zwierzecia := zwierzeta_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Zwierzęta_zwierzeta_seq AFTER UPDATE OF Nr_zwierzecia
ON Zwierzęta FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_zwierzecia in table Zwierzęta as it uses sequence.');
END;
/

-- Trigger for sequence pracownicy_seq for column Nr_pracownika in table Pracownicy ---------
CREATE OR REPLACE TRIGGER ts_Pracownicy_pracownicy_seq BEFORE INSERT
ON Pracownicy FOR EACH ROW
BEGIN
  :new.Nr_pracownika := pracownicy_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Pracownicy_pracownicy_seq AFTER UPDATE OF Nr_pracownika
ON Pracownicy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_pracownika in table Pracownicy as it uses sequence.');
END;
/

-- Trigger for sequence adoptujacy_seq for column Nr_adoptujacego in table Adoptujacy ---------
CREATE OR REPLACE TRIGGER ts_Adoptujacy_adoptujacy_seq BEFORE INSERT
ON Adoptujacy FOR EACH ROW
BEGIN
  :new.Nr_adoptujacego := adoptujacy_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Adoptujacy_adoptujacy_seq AFTER UPDATE OF Nr_adoptujacego
ON Adoptujacy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_adoptujacego in table Adoptujacy as it uses sequence.');
END;
/

-- Trigger for sequence wolontariusze_seq for column Nr_wolontariusza in table Wolontariusze ---------
CREATE OR REPLACE TRIGGER ts_Wolontariusze_wolontariusze_seq BEFORE INSERT
ON Wolontariusze FOR EACH ROW
BEGIN
  :new.Nr_wolontariusza := wolontariusze_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Wolontariusze_wolontariusze_seq AFTER UPDATE OF Nr_wolontariusza
ON Wolontariusze FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_wolontariusza in table Wolontariusze as it uses sequence.');
END;
/

-- Trigger for sequence wplaty_seq for column Nr_wplaty in table Wplaty ---------
CREATE OR REPLACE TRIGGER ts_Wplaty_wplaty_seq BEFORE INSERT
ON Wplaty FOR EACH ROW
BEGIN
  :new.Nr_wplaty := wplaty_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Wplaty_wplaty_seq AFTER UPDATE OF Nr_wplaty
ON Wplaty FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_wplaty in table Wplaty as it uses sequence.');
END;
/

-- Trigger for sequence adresy_seq for column Nr_adresu in table Adresy ---------
CREATE OR REPLACE TRIGGER ts_Adresy_adresy_seq BEFORE INSERT
ON Adresy FOR EACH ROW
BEGIN
  :new.Nr_adresu := adresy_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Adresy_adresy_seq AFTER UPDATE OF Nr_adresu
ON Adresy FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_adresu in table Adresy as it uses sequence.');
END;
/

-- Trigger for sequence poczty_seq for column nr_poczty in table Poczty ---------
CREATE OR REPLACE TRIGGER ts_Poczty_poczty_seq BEFORE INSERT
ON Poczty FOR EACH ROW
BEGIN
  :new.nr_poczty := poczty_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Poczty_poczty_seq AFTER UPDATE OF nr_poczty
ON Poczty FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column nr_poczty in table Poczty as it uses sequence.');
END;
/

-- Trigger for sequence poczty_seq for column Kod_pocztowy in table Poczty ---------
CREATE OR REPLACE TRIGGER ts_Poczty_poczty_seq BEFORE INSERT
ON Poczty FOR EACH ROW
BEGIN
  :new.Kod_pocztowy := poczty_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Poczty_poczty_seq AFTER UPDATE OF Kod_pocztowy
ON Poczty FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Kod_pocztowy in table Poczty as it uses sequence.');
END;
/

-- Trigger for sequence stanowiska_seq for column Nr_stanowiska in table Stanowiska ---------
CREATE OR REPLACE TRIGGER ts_Stanowiska_stanowiska_seq BEFORE INSERT
ON Stanowiska FOR EACH ROW
BEGIN
  :new.Nr_stanowiska := stanowiska_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Stanowiska_stanowiska_seq AFTER UPDATE OF Nr_stanowiska
ON Stanowiska FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_stanowiska in table Stanowiska as it uses sequence.');
END;
/

-- Trigger for sequence gatunki_seq for column Nr_gatunku in table Gatunki ---------
CREATE OR REPLACE TRIGGER ts_Gatunki_gatunki_seq BEFORE INSERT
ON Gatunki FOR EACH ROW
BEGIN
  :new.Nr_gatunku := gatunki_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Gatunki_gatunki_seq AFTER UPDATE OF Nr_gatunku
ON Gatunki FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_gatunku in table Gatunki as it uses sequence.');
END;
/

-- Trigger for sequence rasa_seq for column Nr_rasy in table Rasa ---------
CREATE OR REPLACE TRIGGER ts_Rasa_rasa_seq BEFORE INSERT
ON Rasa FOR EACH ROW
BEGIN
  :new.Nr_rasy := rasa_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Rasa_rasa_seq AFTER UPDATE OF Nr_rasy
ON Rasa FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_rasy in table Rasa as it uses sequence.');
END;
/

-- Trigger for sequence zajecia_seq for column Nr_zajecia in table Zajecia ---------
CREATE OR REPLACE TRIGGER ts_Zajecia_zajecia_seq BEFORE INSERT
ON Zajecia FOR EACH ROW
BEGIN
  :new.Nr_zajecia := zajecia_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Zajecia_zajecia_seq AFTER UPDATE OF Nr_zajecia
ON Zajecia FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_zajecia in table Zajecia as it uses sequence.');
END;
/

-- Trigger for sequence zadania_seq for column Nr_zadania in table Zadania ---------
CREATE OR REPLACE TRIGGER ts_Zadania_zadania_seq BEFORE INSERT
ON Zadania FOR EACH ROW
BEGIN
  :new.Nr_zadania := zadania_seq.nextval;
END;
/
CREATE OR REPLACE TRIGGER tsu_Zadania_zadania_seq AFTER UPDATE OF Nr_zadania
ON Zadania FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column Nr_zadania in table Zadania as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE Zwierzęta ADD CONSTRAINT Zajmuje_sie FOREIGN KEY (Nr_schroniska) REFERENCES Schroniska (Nr_schroniska)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Zatrudnia FOREIGN KEY (Nr_schroniska) REFERENCES Schroniska (Nr_schroniska)
/



ALTER TABLE Zwierzęta ADD CONSTRAINT Adoptuje FOREIGN KEY (Nr_adoptujacego) REFERENCES Adoptujacy (Nr_adoptujacego)
/



ALTER TABLE Wolontariusze ADD CONSTRAINT Koordynuje FOREIGN KEY (Nr_schroniska) REFERENCES Schroniska (Nr_schroniska)
/



ALTER TABLE Wplaty ADD CONSTRAINT Otrzymuje FOREIGN KEY (Nr_schroniska) REFERENCES Schroniska (Nr_schroniska)
/



ALTER TABLE Zwierzęta ADD CONSTRAINT Mieszka_w FOREIGN KEY (Nr_kojca) REFERENCES Kojce (Nr_kojca)
/



ALTER TABLE Adresy ADD CONSTRAINT posiada_poczte FOREIGN KEY (Attribute1) REFERENCES Poczty (nr_poczty)
/



ALTER TABLE Pracownicy ADD CONSTRAINT ma_stanowisko FOREIGN KEY (Nr_stanowiska) REFERENCES Stanowiska (Nr_stanowiska)
/



ALTER TABLE Schroniska ADD CONSTRAINT ma_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Wolontariusze ADD CONSTRAINT Posiada_adres FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Rasa ADD CONSTRAINT jest_gatunku FOREIGN KEY (Nr_gatunku) REFERENCES Gatunki (Nr_gatunku)
/



ALTER TABLE Adoptujacy ADD CONSTRAINT Relationship10 FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Pracownicy ADD CONSTRAINT Mieszka FOREIGN KEY (Nr_adresu) REFERENCES Adresy (Nr_adresu)
/



ALTER TABLE Opieki ADD CONSTRAINT obejmuje FOREIGN KEY (Nr_zajecia) REFERENCES Zajecia (Nr_zajecia)
/



ALTER TABLE Wolontariusze ADD CONSTRAINT Wykonuje_zadania FOREIGN KEY (Nr_zadania) REFERENCES Zadania (Nr_zadania)
/



ALTER TABLE Zwierzęta ADD CONSTRAINT ma_rase FOREIGN KEY (Nr_rasy) REFERENCES Rasa (Nr_rasy)
/



ALTER TABLE Weterynarz ADD CONSTRAINT Pracownik_weterynarz FOREIGN KEY (Nr_pracownika) REFERENCES Pracownicy (Nr_pracownika)
/



ALTER TABLE Adoptujacy ADD CONSTRAINT Wdraza FOREIGN KEY (Nr_schroniska) REFERENCES Schroniska (Nr_schroniska)
/

    
