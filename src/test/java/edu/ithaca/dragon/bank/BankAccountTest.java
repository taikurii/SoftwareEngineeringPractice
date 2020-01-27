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
        //valid entry (less than balance) equivalence class, not a a border case
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //invalid entry (greater than balance) equivalence class, not a border case
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(300));

        //equivalence class, invalid entry (negative amount), not a border case
        BankAccount bankAccount3 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount3.withdraw(-50));

        //border cases not present might be -1, 0, 200, 201 (given that balance is 200)

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
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}