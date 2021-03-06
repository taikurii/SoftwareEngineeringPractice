package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        //equivalence class - valid entry (not a a border case)
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //equivalence class - greater than balance (not a border case)
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(300));
        assertEquals(200, bankAccount2.getBalance());

        //equivalence class - negative amount (not a border case)
        BankAccount bankAccount3 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount3.withdraw(-50));
        assertEquals(200, bankAccount3.getBalance());

        //equivalence class - negative amount (border case)
        BankAccount bankAccount4 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.withdraw(-1));
        assertEquals(200, bankAccount4.getBalance());

        //equivalence class - greater than balance (border case)
        BankAccount bankAccount5 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount5.withdraw(201));
        assertEquals(200, bankAccount5.getBalance());

        //equivalence class - valid entry (border case)
        BankAccount bankAccount7 = new BankAccount("a@b", 200);
        bankAccount7.withdraw(200);
        assertEquals(0, bankAccount7.getBalance());

        //equivalence class - valid entry (border case)
        BankAccount bankAccount8 = new BankAccount("a@b", 200);
        bankAccount8.withdraw(199);
        assertEquals(1, bankAccount8.getBalance());

        BankAccount bankAccount9 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount9.withdraw(0));
        assertEquals(100, bankAccount9.getBalance());

        BankAccount bankAccount10 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount10.withdraw(-1));
        assertEquals(100, bankAccount10.getBalance());

        BankAccount bankAccount11 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount11.withdraw(1.111));
        assertEquals(100, bankAccount11.getBalance());
    }

    @Test
    void depositTest() {
        //equivalence class - valid amount (border case)
        BankAccount bankAccount1 = new BankAccount("a@b.com", 100);
        bankAccount1.deposit(.01);
        assertEquals(100.01, bankAccount1.getBalance());

        //equivalence class - valid amount (middle case)
        BankAccount bankAccount2 = new BankAccount("a@b.com", 100);
        bankAccount2.deposit(100);
        assertEquals(200, bankAccount2.getBalance());

        BankAccount bankAccount8 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount8.deposit(0));
        assertEquals(100, bankAccount8.getBalance());

        //equivalence class - negative amount (border case)
        BankAccount bankAccount3 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount3.deposit(-.01));
        assertEquals(100, bankAccount3.getBalance());

        //equivalence class - negative amount (middle case)
        BankAccount bankAccount4 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.deposit(-100));
        assertEquals(100, bankAccount4.getBalance());

        //equivalence class - more than two decimal places (border case)
        BankAccount bankAccount5 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount5.deposit(1.111));
        assertEquals(100, bankAccount5.getBalance());

        //equivalence class - more than two decimal places (middle case)
        BankAccount bankAccount6 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount6.deposit(.9999999));
        assertEquals(100, bankAccount6.getBalance());

        //equivalence class - more than two decimal places and negative
        BankAccount bankAccount7 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount7.deposit(-0.111));
        assertEquals(100, bankAccount7.getBalance());
    }

    @Test
    void transferTest() {
        //equivalence class - valid amount (middle case)
        BankAccount accFrom1 = new BankAccount("a@b.com", 100);
        BankAccount accTo1 = new BankAccount("a@b.com", 100);
        BankAccount.transfer(100, accFrom1, accTo1);
        assertEquals(0, accFrom1.getBalance());
        assertEquals(200, accTo1.getBalance());

        //equivalence class - valid amount (border case)
        BankAccount accFrom2 = new BankAccount("a@b.com", 100);
        BankAccount accTo2 = new BankAccount("a@b.com", 100);
        BankAccount.transfer(.01, accFrom2, accTo2);
        assertEquals(99.99, accFrom2.getBalance());
        assertEquals(100.01, accTo2.getBalance());

        //equivalence class - amount greater than accFrom balance (border case)
        BankAccount accFrom3 = new BankAccount("a@b.com", 100);
        BankAccount accTo3 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> BankAccount.transfer(101, accFrom3, accTo3));
        assertEquals(100, accFrom3.getBalance());
        assertEquals(100, accTo3.getBalance());

        //equivalence class - amount greater than accFrom balance (middle case)
        BankAccount accFrom4 = new BankAccount("a@b.com", 100);
        BankAccount accTo4 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> BankAccount.transfer(500, accFrom4, accTo4));
        assertEquals(100, accFrom4.getBalance());
        assertEquals(100, accTo4.getBalance());

        //equivalence class - negative amount (border case)
        BankAccount accFrom5 = new BankAccount("a@b.com", 100);
        BankAccount accTo5 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> BankAccount.transfer(-.01, accFrom5, accTo5));
        assertEquals(100, accFrom5.getBalance());
        assertEquals(100, accTo5.getBalance());

        //equivalence class - negative amount (middle case)
        BankAccount accFrom6 = new BankAccount("a@b.com", 100);
        BankAccount accTo6 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> BankAccount.transfer(-100, accFrom6, accTo6));
        assertEquals(100, accFrom6.getBalance());
        assertEquals(100, accTo6.getBalance());

        //equivalence class - more than two decimal cases (border case)
        BankAccount accFrom7 = new BankAccount("a@b.com", 100);
        BankAccount accTo7 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> BankAccount.transfer(1.111, accFrom7, accTo7));
        assertEquals(100, accFrom7.getBalance());
        assertEquals(100, accTo7.getBalance());

        //equivalence class - more than two decimal cases (middle case)
        BankAccount accFrom8 = new BankAccount("a@b.com", 100);
        BankAccount accTo8 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> BankAccount.transfer(.999999, accFrom8, accTo8));
        assertEquals(100, accFrom8.getBalance());
        assertEquals(100, accTo8.getBalance());

        //equivalence class - negative and more than two decimal cases
        BankAccount accFrom9 = new BankAccount("a@b.com", 100);
        BankAccount accTo9 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> BankAccount.transfer(1.111, accFrom9, accTo9));
        assertEquals(100, accFrom9.getBalance());
        assertEquals(100, accTo9.getBalance());
    }

    @Test
    void isEmailValidTest(){
        //valid entry equivalence class
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        //invalid entry
        assertFalse( BankAccount.isEmailValid(""));
        //invalid entry, one '@' equivalence class, border case
        assertFalse(BankAccount.isEmailValid("a@@b.com"));
        //invalid entry, valid symbol (hyphen) before '@' equivalence class, border case
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        //invalid entry, more than one consecutive valid symbol (period) equivalence class, border case
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        //invalid entry, invalid symbol equivalence class, border case
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        //invalid entry, invalid symbol equivalence class, not a border case
        assertFalse(BankAccount.isEmailValid("abc##def@mail.com"));
        //invalid entry, valid (period) symbol but at start of email equivalence class, border case
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        //valid entry
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        //valid entry
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        //valid entry
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        //valid entry, border case because 2 characters after suffix "."
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));
        //invalid entry, one letter after domain '.' equivalence class, border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        //invalid entry, one letter after domain '.' equivalence class, border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));

        //anything with a ".", "_", or "-" could technically be considered a border case because just one character
        // away from a double special character, but given that a suffix must have a ".", wouldn't they all be
        //border cases????

    }

    @Test
    void isAmountValidTest() {
        //equivalence class - valid amount (border case)
        assertTrue(BankAccount.isAmountValid(0.01));
        //equivalence class - valid amount (middle case)
        assertTrue(BankAccount.isAmountValid(100));
        //equivalence class - decimal places
        assertTrue(BankAccount.isAmountValid(1.1));
        //equivalence class - zero amount
        assertFalse(BankAccount.isAmountValid(0));
        //equivalence class - negative amount(border case)
        assertFalse(BankAccount.isAmountValid(-.01));
        //equivalence class - negative amount(middle case)
        assertFalse(BankAccount.isAmountValid(-100));
        //equivalence class - three or more decimal places (border case)
        assertFalse(BankAccount.isAmountValid(.001));
        //equivalence class - three or more decimal places (middle case)
        assertFalse(BankAccount.isAmountValid(.999999));
        //equivalence class - negative amount and three or more decimal places
        assertFalse(BankAccount.isAmountValid(-.001));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -1));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 1.111));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 0));
    }

}