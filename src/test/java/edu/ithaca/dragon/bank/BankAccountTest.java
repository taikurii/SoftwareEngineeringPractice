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
        BankAccount bankAccount6 = new BankAccount("a@b", 200);
        bankAccount6.withdraw(0);
        assertEquals(200, bankAccount6.getBalance());


        //equivalence class - valid entry (border case)
        BankAccount bankAccount7 = new BankAccount("a@b", 200);
        bankAccount7.withdraw(200);
        assertEquals(0, bankAccount7.getBalance());

        //equivalence class - valid entry (border case)
        BankAccount bankAccount8 = new BankAccount("a@b", 200);
        bankAccount8.withdraw(199);
        assertEquals(1, bankAccount8.getBalance());

    }

    @Test
    void isEmailValidTest(){
        //all are equivalence classes
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
    }

}