BEGIN;

-- Create the Authors table
CREATE TABLE IF NOT EXISTS public."Authors"
(
    "Authors_id" character varying(10),
    "Name" character varying(20),
    "Surname" character varying(20),
    "Bio" character varying(20),
    CONSTRAINT "Authors_pkey" PRIMARY KEY ("Authors_id")
);

-- Create the Books table
CREATE TABLE IF NOT EXISTS public."Books"
(
    "Authors_id" character varying(10),
    "Book_id" character varying(10),
    "Title" character varying(20),
    "Genre" character varying(20),
    CONSTRAINT "Books_pkey" PRIMARY KEY ("Book_id"),
    CONSTRAINT "fk_Author_id" FOREIGN KEY ("Authors_id") REFERENCES public."Authors" ("Authors_id")
);

-- Create the Borrowers table
CREATE TABLE IF NOT EXISTS public."Borrowers"
(
    "Borrower_id" character varying(10),
    "Name" character varying(20),
    "Surname" character varying(20),
    "Phone" character varying(10),
    "Email" character varying(25),
    "Address" character varying(30),
    CONSTRAINT "Borrowers_pkey" PRIMARY KEY ("Borrower_id")
);

-- Create the Loans table
CREATE TABLE IF NOT EXISTS public."Loans"
(
    "Loan_id" character varying(10),
    "Book_id" character varying(10),
    "Borrower_id" character varying(10),
    "LoanDate" date NOT NULL,
    "ReturnDate" date NOT NULL,
    "Returned" smallint NOT NULL DEFAULT 0,
    CONSTRAINT "Loans_pkey" PRIMARY KEY ("Loan_id"),
    CONSTRAINT "fk_Book_id" FOREIGN KEY ("Book_id") REFERENCES public."Books" ("Book_id"),
    CONSTRAINT "fk_Borrower_id" FOREIGN KEY ("Borrower_id") REFERENCES public."Borrowers" ("Borrower_id")
);

-- Create the Users table
CREATE TABLE IF NOT EXISTS public."Users"
(
    "User_id" character varying(10),
    "Username" character varying(20),
    "Password" character varying(20),
    "Name" character varying(20),
    "Surname" character varying(20),
    "Phone" character varying(10),
    "Email" character varying(25),
    CONSTRAINT "Users_pkey" PRIMARY KEY ("User_id")
);

-- Insert sample data into Authors
INSERT INTO public."Authors" ("Authors_id", "Name", "Surname", "Bio") VALUES
('A001', 'Author1', 'Surname1', 'Bio1'),
('A002', 'Author2', 'Surname2', 'Bio2'),
('A003', 'Author3', 'Surname3', 'Bio3'),
('A004', 'Author4', 'Surname4', 'Bio4'),
('A005', 'Author5', 'Surname5', 'Bio5');

-- Insert sample data into Books
INSERT INTO public."Books" ("Authors_id", "Book_id", "Title", "Genre") VALUES
('A001', 'B001', 'Title1', 'Genre1'),
('A002', 'B002', 'Title2', 'Genre2'),
('A003', 'B003', 'Title3', 'Genre3'),
('A004', 'B004', 'Title4', 'Genre4'),
('A005', 'B005', 'Title5', 'Genre5');

-- Insert sample data into Borrowers
INSERT INTO public."Borrowers" ("Borrower_id", "Name", "Surname", "Phone", "Email", "Address") VALUES
('BR001', 'Name1', 'Surname1', '1234567890', 'email1@ex.com', 'Addr1'),
('BR002', 'Name2', 'Surname2', '2345678901', 'email2@ex.com', 'Addr2'),
('BR003', 'Name3', 'Surname3', '3456789012', 'email3@ex.com', 'Addr3'),
('BR004', 'Name4', 'Surname4', '4567890123', 'email4@ex.com', 'Addr4'),
('BR005', 'Name5', 'Surname5', '5678901234', 'email5@ex.com', 'Addr5');

-- Insert sample data into Users
INSERT INTO public."Users" ("User_id", "Username", "Password", "Name", "Surname", "Phone", "Email") VALUES
('U001', 'user1', 'pass1', 'Name1', 'Surname1', '6789012345', 'user1@ex.com'),
('U002', 'user2', 'pass2', 'Name2', 'Surname2', '7890123456', 'user2@ex.com'),
('U003', 'user3', 'pass3', 'Name3', 'Surname3', '8901234567', 'user3@ex.com'),
('U004', 'user4', 'pass4', 'Name4', 'Surname4', '9012345678', 'user4@ex.com'),
('U005', 'user5', 'pass5', 'Name5', 'Surname5', '0123456789', 'user5@ex.com');

-- Insert sample data into Loans
INSERT INTO public."Loans" ("Loan_id", "Book_id", "Borrower_id", "LoanDate", "ReturnDate", "Returned") VALUES
('L001', 'B001', 'BR001', '2024-01-01', '2024-02-01', 0),
('L002', 'B002', 'BR002', '2024-02-01', '2024-03-01', 0),
('L003', 'B003', 'BR003', '2024-03-01', '2024-04-01', 0),
('L004', 'B004', 'BR004', '2024-04-01', '2024-05-01', 0),
('L005', 'B005', 'BR005', '2024-05-01', '2024-06-01', 0),
('L006', 'B001', 'BR002', '2024-01-05', '2024-02-05', 0),
('L007', 'B002', 'BR003', '2024-02-05', '2024-03-05', 0),
('L008', 'B003', 'BR004', '2024-03-05', '2024-04-05', 0),
('L009', 'B004', 'BR005', '2024-04-05', '2024-05-05', 0),
('L010', 'B005', 'BR001', '2024-05-05', '2024-06-05', 0),
('L011', 'B001', 'BR003', '2024-01-10', '2024-02-10', 0),
('L012', 'B002', 'BR004', '2024-02-10', '2024-03-10', 0),
('L013', 'B003', 'BR005', '2024-03-10', '2024-04-10', 0),
('L014', 'B004', 'BR001', '2024-04-10', '2024-05-10', 0),
('L015', 'B005', 'BR002', '2024-05-10', '2024-06-10', 0),
('L016', 'B001', 'BR004', '2024-01-15', '2024-02-15', 1),
('L017', 'B002', 'BR005', '2024-02-15', '2024-03-15', 0),
('L018', 'B003', 'BR001', '2024-03-15', '2024-04-15', 1),
('L019', 'B004', 'BR002', '2024-04-15', '2024-05-15', 1),
('L020', 'B005', 'BR003', '2024-05-15', '2024-06-15', 1);


-- Create Views
CREATE VIEW public."BooksAndAuthorsView" AS
SELECT b."Book_id", b."Title", a."Name" AS "AuthorName", a."Surname" AS "AuthorSurname"
FROM public."Books" b
JOIN public."Authors" a ON b."Authors_id" = a."Authors_id";

CREATE VIEW public."BooksAndBorrowersView" AS
SELECT b."Book_id", b."Title", br."Name" AS "BorrowerName", br."Surname" AS "BorrowerSurname"
FROM public."Books" b
JOIN public."Loans" l ON b."Book_id" = l."Book_id"
JOIN public."Borrowers" br ON l."Borrower_id" = br."Borrower_id";

BEGIN;

-- Create Loans View
CREATE VIEW public."LoansView" AS
SELECT 
    l."Loan_id",
    br."Name" AS "BorrowerName",
    br."Surname" AS "BorrowerSurname",
    b."Title" AS "BookTitle",
    l."LoanDate",
    l."ReturnDate",
    CASE 
        WHEN l."Returned" = 0 THEN 'Not Returned'
        WHEN l."Returned" = 1 THEN 'Returned'
    END AS "ReturnStatus"
FROM 
    public."Loans" l
JOIN 
    public."Borrowers" br ON l."Borrower_id" = br."Borrower_id"
JOIN 
    public."Books" b ON l."Book_id" = b."Book_id";

-- Procedures for Author
CREATE OR REPLACE PROCEDURE public."CreateAuthor"(
    p_Author_id VARCHAR(10),
    p_Name VARCHAR(20),
    p_Surname VARCHAR(20),
    p_Bio VARCHAR(20)
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO public."Authors" ("Authors_id", "Name", "Surname", "Bio")
    VALUES (p_Author_id, p_Name, p_Surname, p_Bio);
END;
$$;

-- Convert ReadAuthor to a Function
CREATE OR REPLACE FUNCTION public."ReadAuthor"(
    p_Author_id VARCHAR(10)
)
RETURNS TABLE ("Authors_id" VARCHAR(10), "Name" VARCHAR(20), "Surname" VARCHAR(20), "Bio" VARCHAR(20))
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY 
    SELECT "Authors_id", "Name", "Surname", "Bio"
    FROM public."Authors"
    WHERE "Authors_id" = p_Author_id;
END;
$$;

CREATE OR REPLACE PROCEDURE public."UpdateAuthor"(
    p_Author_id VARCHAR(10),
    p_Name VARCHAR(20),
    p_Surname VARCHAR(20),
    p_Bio VARCHAR(20)
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE public."Authors"
    SET "Name" = p_Name,
        "Surname" = p_Surname,
        "Bio" = p_Bio
    WHERE "Authors_id" = p_Author_id;
END;
$$;

CREATE OR REPLACE PROCEDURE public."DeleteAuthor"(
    p_Author_id VARCHAR(10)
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM public."Authors"
    WHERE "Authors_id" = p_Author_id;
END;
$$;

-- Procedures for Books
CREATE OR REPLACE PROCEDURE public."CreateBook"(
    p_Authors_id VARCHAR(10),
    p_Book_id VARCHAR(10),
    p_Title VARCHAR(20),
    p_Genre VARCHAR(20)
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO public."Books" ("Authors_id", "Book_id", "Title", "Genre")
    VALUES (p_Authors_id, p_Book_id, p_Title, p_Genre);
END;
$$;

-- Convert ReadBook to a Function
CREATE OR REPLACE FUNCTION public."ReadBook"(
    p_Book_id VARCHAR(10)
)
RETURNS TABLE ("Authors_id" VARCHAR(10), "Book_id" VARCHAR(10), "Title" VARCHAR(20), "Genre" VARCHAR(20))
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY 
    SELECT "Authors_id", "Book_id", "Title", "Genre"
    FROM public."Books"
    WHERE "Book_id" = p_Book_id;
END;
$$;

CREATE OR REPLACE PROCEDURE public."UpdateBook"(
    p_Authors_id VARCHAR(10),
    p_Book_id VARCHAR(10),
    p_Title VARCHAR(20),
    p_Genre VARCHAR(20)
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE public."Books"
    SET "Authors_id" = p_Authors_id,
        "Title" = p_Title,
        "Genre" = p_Genre
    WHERE "Book_id" = p_Book_id;
END;
$$;

CREATE OR REPLACE PROCEDURE public."DeleteBook"(
    p_Book_id VARCHAR(10)
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM public."Books"
    WHERE "Book_id" = p_Book_id;
END;
$$;

-- Procedures for Borrower
CREATE OR REPLACE PROCEDURE public."CreateBorrower"(
    p_Borrower_id VARCHAR(10),
    p_Name VARCHAR(20),
    p_Surname VARCHAR(20),
    p_Phone VARCHAR(10),
    p_Email VARCHAR(25),
    p_Address VARCHAR(30)
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO public."Borrowers" ("Borrower_id", "Name", "Surname", "Phone", "Email", "Address")
    VALUES (p_Borrower_id, p_Name, p_Surname, p_Phone, p_Email, p_Address);
END;
$$;

-- Convert ReadBorrower to a Function
CREATE OR REPLACE FUNCTION public."ReadBorrower"(
    p_Borrower_id VARCHAR(10)
)
RETURNS TABLE ("Borrower_id" VARCHAR(10), "Name" VARCHAR(20), "Surname" VARCHAR(20), "Phone" VARCHAR(10), "Email" VARCHAR(25), "Address" VARCHAR(30))
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY 
    SELECT "Borrower_id", "Name", "Surname", "Phone", "Email", "Address"
    FROM public."Borrowers"
    WHERE "Borrower_id" = p_Borrower_id;
END;
$$;

CREATE OR REPLACE PROCEDURE public."UpdateBorrower"(
    p_Borrower_id VARCHAR(10),
    p_Name VARCHAR(20),
    p_Surname VARCHAR(20),
    p_Phone VARCHAR(10),
    p_Email VARCHAR(25),
    p_Address VARCHAR(30)
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE public."Borrowers"
    SET "Name" = p_Name,
        "Surname" = p_Surname,
        "Phone" = p_Phone,
        "Email" = p_Email,
        "Address" = p_Address
    WHERE "Borrower_id" = p_Borrower_id;
END;
$$;

CREATE OR REPLACE PROCEDURE public."DeleteBorrower"(
    p_Borrower_id VARCHAR(10)
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM public."Borrowers"
    WHERE "Borrower_id" = p_Borrower_id;
END;
$$;

-- Procedures for Loans
CREATE OR REPLACE PROCEDURE public."CreateLoan"(
    p_Loan_id VARCHAR(10),
    p_Book_id VARCHAR(10),
    p_Borrower_id VARCHAR(10),
    p_LoanDate DATE,
    p_ReturnDate DATE,
    p_Returned SMALLINT
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO public."Loans" ("Loan_id", "Book_id", "Borrower_id", "LoanDate", "ReturnDate", "Returned")
    VALUES (p_Loan_id, p_Book_id, p_Borrower_id, p_LoanDate, p_ReturnDate, p_Returned);
END;
$$;

-- Convert ReadLoan to a Function
CREATE OR REPLACE FUNCTION public."ReadLoan"(
    p_Loan_id VARCHAR(10)
)
RETURNS TABLE ("Loan_id" VARCHAR(10), "Book_id" VARCHAR(10), "Borrower_id" VARCHAR(10), "LoanDate" DATE, "ReturnDate" DATE, "Returned" SMALLINT)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY 
    SELECT "Loan_id", "Book_id", "Borrower_id", "LoanDate", "ReturnDate", "Returned"
    FROM public."Loans"
    WHERE "Loan_id" = p_Loan_id;
END;
$$;

CREATE OR REPLACE PROCEDURE public."UpdateLoan"(
    p_Loan_id VARCHAR(10),
    p_Book_id VARCHAR(10),
    p_Borrower_id VARCHAR(10),
    p_LoanDate DATE,
    p_ReturnDate DATE,
    p_Returned SMALLINT
)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE public."Loans"
    SET "Book_id" = p_Book_id,
        "Borrower_id" = p_Borrower_id,
        "LoanDate" = p_LoanDate,
        "ReturnDate" = p_ReturnDate,
        "Returned" = p_Returned
    WHERE "Loan_id" = p_Loan_id;
END;
$$;

CREATE OR REPLACE PROCEDURE public."DeleteLoan"(
    p_Loan_id VARCHAR(10)
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM public."Loans"
    WHERE "Loan_id" = p_Loan_id;
END;
$$;

COMMIT;
